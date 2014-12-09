package com.navercorp.pinpoint.profiler.interceptor;

import com.navercorp.pinpoint.bootstrap.interceptor.StaticAroundInterceptor;
import com.navercorp.pinpoint.bootstrap.interceptor.TargetClassLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author emeroad
 */
public class TestAfterInterceptor implements StaticAroundInterceptor, TargetClassLoader {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public int call = 0;
    public Object target;
    public String className;
    public String methodName;
    public Object[] args;
    public Object result;

    @Override
    public void before(Object target, String className, String methodName, String parameterDescription, Object[] args) {

    }

    @Override
    public void after(Object target, String className, String methodName, String parameterDescription, Object[] args, Object result, Throwable throwable) {
        logger.info("after target:" + target  + " " + className + "." + methodName + parameterDescription + " args:" + Arrays.toString(args));
        this.target = target;
        this.className = className;
        this.methodName = methodName;
        this.args = args;
        call++;
        this.result = result;
    }
}
