package com.navercorp.pinpoint.profiler.modifier.arcus.interceptor;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.Future;

import com.navercorp.pinpoint.bootstrap.context.RecordableTrace;
import com.navercorp.pinpoint.bootstrap.interceptor.*;
import com.navercorp.pinpoint.bootstrap.util.MetaObject;
import com.navercorp.pinpoint.common.ServiceType;

import net.spy.memcached.MemcachedNode;
import net.spy.memcached.ops.Operation;

/**
 * @author emeroad
 */
public class ApiInterceptor extends SpanEventSimpleAroundInterceptor implements ParameterExtractorSupport, TargetClassLoader {


    private MetaObject<Object> getOperation = new MetaObject<Object>("__getOperation");
    private MetaObject<Object> getServiceCode = new MetaObject<Object>("__getServiceCode");
    
    private ParameterExtractor parameterExtractor;

    public ApiInterceptor() {
        super(ApiInterceptor.class);
    }

    @Override
	public void doInBeforeTrace(RecordableTrace trace, final Object target, Object[] args) {
		trace.markBeforeTime();
	}

    @Override
    public void doInAfterTrace(RecordableTrace trace, Object target, Object[] args, Object result, Throwable throwable) {

        if (parameterExtractor != null) {
            final int index = parameterExtractor.getIndex();
            final Object recordObject = parameterExtractor.extractObject(args);
            trace.recordApi(getMethodDescriptor(), recordObject, index);
        } else {
            trace.recordApi(getMethodDescriptor());
        }

        // find the target node
        if (result instanceof Future) {
            Operation op = (Operation) getOperation.invoke(((Future<?>)result));
            if (op != null) {
                MemcachedNode handlingNode = op.getHandlingNode();
                SocketAddress socketAddress = handlingNode.getSocketAddress();
                if (socketAddress instanceof InetSocketAddress) {
                    InetSocketAddress address = (InetSocketAddress) socketAddress;
                    trace.recordEndPoint(address.getHostName() + ":" + address.getPort());
                }
            } else {
                logger.info("operation not found");
            }
        }

        // determine the service type
        String serviceCode = (String) getServiceCode.invoke(target);
        if (serviceCode != null) {
            trace.recordDestinationId(serviceCode);
            trace.recordServiceType(ServiceType.ARCUS);
        } else {
            trace.recordDestinationId("MEMCACHED");
            trace.recordServiceType(ServiceType.MEMCACHED);
        }

        trace.markAfterTime();
    }

    @Override
    public void setParameterExtractor(ParameterExtractor parameterExtractor) {
        this.parameterExtractor = parameterExtractor;
    }
}
