package com.navercorp.pinpoint.test.junit4;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TBase;
import org.junit.runner.RunWith;

import com.navercorp.pinpoint.bootstrap.context.ServerMetaData;
import com.navercorp.pinpoint.bootstrap.context.ServerMetaDataHolder;
import com.navercorp.pinpoint.common.bo.SpanBo;
import com.navercorp.pinpoint.common.bo.SpanEventBo;
import com.navercorp.pinpoint.profiler.context.Span;
import com.navercorp.pinpoint.profiler.context.SpanEvent;
import com.navercorp.pinpoint.test.PeekableDataSender;

/**
 * @author hyungil.jeong
 */
@RunWith(value = PinpointJUnit4ClassRunner.class)
public abstract class BasePinpointTest {
    private ThreadLocal<PeekableDataSender<? extends TBase<?, ?>>> traceHolder = new ThreadLocal<PeekableDataSender<? extends TBase<?, ?>>>();
    private ThreadLocal<ServerMetaDataHolder> serverMetaDataHolder = new ThreadLocal<ServerMetaDataHolder>();

    protected final List<SpanEventBo> getCurrentSpanEvents() {
        List<SpanEventBo> spanEvents = new ArrayList<SpanEventBo>();
        for (TBase<?, ?> span : this.traceHolder.get()) {
            if (span instanceof SpanEvent) {
                SpanEvent spanEvent = (SpanEvent)span;
                spanEvents.add(new SpanEventBo(spanEvent.getSpan(), spanEvent));
            }
        }
        return spanEvents;
    }

    protected final List<SpanBo> getCurrentRootSpans() {
        List<SpanBo> rootSpans = new ArrayList<SpanBo>();
        for (TBase<?, ?> span : this.traceHolder.get()) {
            if (span instanceof Span) {
                rootSpans.add(new SpanBo((Span)span));
            }
        }
        return rootSpans;
    }
    
    protected final ServerMetaData getServerMetaData() {
        return this.serverMetaDataHolder.get().getServerMetaData();
    }

    final void setCurrentHolder(PeekableDataSender<? extends TBase<?, ?>> dataSender) {
        traceHolder.set(dataSender);
    }
    
    final void setServerMetaDataHolder(ServerMetaDataHolder metaDataHolder) {
        this.serverMetaDataHolder.set(metaDataHolder);
    }
}
