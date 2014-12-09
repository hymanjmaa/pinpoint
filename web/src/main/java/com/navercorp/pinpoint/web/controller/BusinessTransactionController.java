package com.navercorp.pinpoint.web.controller;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.navercorp.pinpoint.web.applicationmap.ApplicationMap;
import com.navercorp.pinpoint.web.calltree.span.SpanAlign;
import com.navercorp.pinpoint.web.filter.Filter;
import com.navercorp.pinpoint.web.filter.FilterBuilder;
import com.navercorp.pinpoint.web.service.FilteredMapService;
import com.navercorp.pinpoint.web.service.SpanResult;
import com.navercorp.pinpoint.web.service.SpanService;
import com.navercorp.pinpoint.web.service.TransactionInfoService;
import com.navercorp.pinpoint.web.util.LimitUtils;
import com.navercorp.pinpoint.web.util.TimeUtils;
import com.navercorp.pinpoint.web.vo.BusinessTransactions;
import com.navercorp.pinpoint.web.vo.LimitedScanResult;
import com.navercorp.pinpoint.web.vo.Range;
import com.navercorp.pinpoint.web.vo.TransactionId;
import com.navercorp.pinpoint.web.vo.callstacks.RecordSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author emeroad
 */
@Controller
public class BusinessTransactionController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SpanService spanService;

	@Autowired
	private TransactionInfoService transactionInfoService;

	@Autowired
	private FilteredMapService filteredMapService;

    @Autowired
    private FilterBuilder filterBuilder;

    /**
	 * applicationname에서 from ~ to 시간대에 수행된 URL을 조회한다.
	 * 
	 * @param model
	 * @param applicationName
	 * @param from
	 * @param to
	 * @return
	 */
    @Deprecated
	@RequestMapping(value = "/transactionList", method = RequestMethod.GET)
    @ResponseBody
	public Model getBusinessTransactionsData(Model model,
											@RequestParam("application") String applicationName,
											@RequestParam("from") long from, 
											@RequestParam("to") long to,
											@RequestParam(value = "filter", required = false) String filterText,
											@RequestParam(value = "limit", required = false, defaultValue = "10000") int limit) {
        limit = LimitUtils.checkRange(limit);
		Range range = new Range(from, to);
		// TOOD 구조개선을 위해 server map조회 로직 분리함, 임시로 분리한 상태이고 개선이 필요하다.
		LimitedScanResult<List<TransactionId>> traceIdList = filteredMapService.selectTraceIdsFromApplicationTraceIndex(applicationName, range, limit);

		Filter filter = filterBuilder.build(filterText);
		BusinessTransactions selectBusinessTransactions = transactionInfoService.selectBusinessTransactions(traceIdList.getScanData(), applicationName, range, filter);

		model.addAttribute("lastFetchedTimestamp", traceIdList.getLimitedTime());
		model.addAttribute("rpcList", selectBusinessTransactions.getBusinessTransaction());
		model.addAttribute("requestList", selectBusinessTransactions.getBusinessTransaction());
		model.addAttribute("scatterList", selectBusinessTransactions.getBusinessTransaction());
		model.addAttribute("applicationName", applicationName);
		model.addAttribute("from", new Date(from));
		model.addAttribute("to", new Date(to));
		model.addAttribute("urlCount", selectBusinessTransactions.getURLCount());
		model.addAttribute("totalCount", selectBusinessTransactions.getTotalCallCount());
		model.addAttribute("filterText", filterText);
		model.addAttribute("filter", filter);
        // Deprecated jsp -> need json dump
		return model;
	}

    @Deprecated
	@RequestMapping(value = "/lastTransactionList", method = RequestMethod.GET)
    @ResponseBody
	public Model getLastBusinessTransactionsData(Model model, HttpServletResponse response,
											@RequestParam("application") String applicationName, 
											@RequestParam("period") long period,
											@RequestParam(value = "filter", required = false) String filterText,
											@RequestParam(value = "limit", required = false, defaultValue = "10000") int limit) {
        limit = LimitUtils.checkRange(limit);
        long to = TimeUtils.getDelayLastTime();
		long from = to - period;
		return getBusinessTransactionsData(model, applicationName, from, to, filterText, limit);
	}

    /**
     * 선택한 하나의 Transaction 정보 조회.
     *
     * @param traceIdParam
     * @param focusTimestamp
     * @return
     */
    @RequestMapping(value = "/transactionInfo", method = RequestMethod.GET)
    public ModelAndView transactionInfo(@RequestParam("traceId") String traceIdParam,
                                        @RequestParam(value = "focusTimestamp", required = false, defaultValue = "0") long focusTimestamp,
                                        @RequestParam(value = "v", required = false, defaultValue = "0") int viewVersion,
                                        HttpServletResponse response) {
        logger.debug("traceId:{}", traceIdParam);

        final TransactionId traceId = new TransactionId(traceIdParam);

        // select spans
        final SpanResult spanResult = this.spanService.selectSpan(traceId, focusTimestamp);
        List<SpanAlign> spanAligns = spanResult.getSpanAlignList();

        if (spanAligns.isEmpty()) {
            // TODO fix error page.
            final ModelAndView error = new ModelAndView();
            // redefine errorCode.???
            error.addObject("errorCode", 9);
            error.addObject("message", "Trace not found. traceId:" + traceId);
            error.setViewName("error");
            return error;
        }

        final ModelAndView mv = new ModelAndView();
        // debug
        mv.addObject("spanList", spanAligns);

        mv.addObject("traceId", traceId);

        // application map
        ApplicationMap map = filteredMapService.selectApplicationMap(traceId);
        mv.addObject("nodes", map.getNodes());
        mv.addObject("links", map.getLinks());

        // call stacks
        RecordSet recordSet = this.transactionInfoService.createRecordSet(spanAligns, focusTimestamp);
        mv.addObject("recordSet", recordSet);

        mv.addObject("applicationName", recordSet.getApplicationName());
        mv.addObject("callstack", recordSet.getRecordList());
        mv.addObject("timeline", recordSet.getRecordList());
        mv.addObject("callstackStart", recordSet.getStartTime());
        mv.addObject("callstackEnd", recordSet.getEndTime());
        mv.addObject("completeState", spanResult.getCompleteTypeString());


        if (viewVersion == 2) {
            mv.setViewName("transactionInfoJsonHash");
        } else {
            mv.setViewName("transactionInfoJson");
        }
        return mv;
    }
}
