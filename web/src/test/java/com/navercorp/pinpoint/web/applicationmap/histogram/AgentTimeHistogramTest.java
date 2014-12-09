package com.navercorp.pinpoint.web.applicationmap.histogram;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.pinpoint.common.ServiceType;
import com.navercorp.pinpoint.web.applicationmap.histogram.AgentTimeHistogram;
import com.navercorp.pinpoint.web.applicationmap.histogram.AgentTimeHistogramBuilder;
import com.navercorp.pinpoint.web.view.AgentResponseTimeViewModel;
import com.navercorp.pinpoint.web.vo.Application;
import com.navercorp.pinpoint.web.vo.Range;
import com.navercorp.pinpoint.web.vo.ResponseTime;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author emeroad
 */
public class AgentTimeHistogramTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testViewModel() throws IOException {

        Application app = new Application("test", ServiceType.TOMCAT);
        AgentTimeHistogramBuilder builder = new AgentTimeHistogramBuilder(app, new Range(0, 1000*60));
        List<ResponseTime> responseHistogramList = createResponseTime(app, "test1", "test2");
        AgentTimeHistogram histogram = builder.build(responseHistogramList);

        List<AgentResponseTimeViewModel> viewModel = histogram.createViewModel();
        logger.debug("{}", viewModel);

        JsonFactory jsonFactory = mapper.getFactory();
        StringWriter stringWriter = new StringWriter();
        JsonGenerator jsonGenerator = jsonFactory.createGenerator(stringWriter);
        jsonGenerator.writeStartObject();
        for (AgentResponseTimeViewModel agentResponseTimeViewModel : viewModel) {
            jsonGenerator.writeObject(agentResponseTimeViewModel);
        }
        jsonGenerator.writeEndObject();
        jsonGenerator.flush();
        jsonGenerator.close();
        logger.debug(stringWriter.toString());

    }

    private List<ResponseTime> createResponseTime(Application app, String agentName1, String agentName2) {
        List<ResponseTime> responseTimeList = new ArrayList<ResponseTime>();

        ResponseTime one = new ResponseTime(app.getName(), app.getServiceTypeCode(), 0);
        one.addResponseTime(agentName1, (short) 1000, 1);
        responseTimeList.add(one);

        ResponseTime two = new ResponseTime(app.getName(), app.getServiceTypeCode(), 1000*60);
        two.addResponseTime(agentName1, (short) 3000, 1);
        responseTimeList.add(two);

        ResponseTime three = new ResponseTime(app.getName(), app.getServiceTypeCode(), 0);
        three.addResponseTime(agentName2, (short) 1000, 1);
        responseTimeList.add(three);

        ResponseTime four = new ResponseTime(app.getName(), app.getServiceTypeCode(), 1000*60);
        four.addResponseTime(agentName2, (short) 3000, 1);
        responseTimeList.add(four);
        return responseTimeList;
    }

}
