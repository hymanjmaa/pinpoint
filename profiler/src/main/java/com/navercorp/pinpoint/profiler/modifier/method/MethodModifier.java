package com.navercorp.pinpoint.profiler.modifier.method;

import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.List;

import com.navercorp.pinpoint.bootstrap.Agent;
import com.navercorp.pinpoint.bootstrap.instrument.ByteCodeInstrumentor;
import com.navercorp.pinpoint.bootstrap.instrument.InstrumentClass;
import com.navercorp.pinpoint.bootstrap.instrument.MethodInfo;
import com.navercorp.pinpoint.bootstrap.interceptor.Interceptor;
import com.navercorp.pinpoint.profiler.modifier.AbstractModifier;
import com.navercorp.pinpoint.profiler.modifier.method.interceptor.MethodInterceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author netspider
 * @author emeroad
 * 
 */
public class MethodModifier extends AbstractModifier {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public MethodModifier(ByteCodeInstrumentor byteCodeInstrumentor, Agent agent) {
		super(byteCodeInstrumentor, agent);
	}

	public String getTargetClass() {
		return "*";
	}

	public byte[] modify(ClassLoader classLoader, String javassistClassName, ProtectionDomain protectedDomain, byte[] classFileBuffer) {
		if (logger.isInfoEnabled()) {
			logger.info("Modifing. {}", javassistClassName);
		}

		try {
			InstrumentClass clazz = byteCodeInstrumentor.getClass(classLoader, javassistClassName, classFileBuffer);

			if (!clazz.isInterceptable()) {
				return null;
			}

			List<MethodInfo> methodList = clazz.getDeclaredMethods(EmptyMethodFilter.FILTER);
			for (MethodInfo method : methodList) {
				final Interceptor interceptor = new MethodInterceptor();
                if (logger.isTraceEnabled()) {
                    logger.trace("### c={}, m={}, params={}", javassistClassName, method.getName(), Arrays.toString(method.getParameterTypes()));
                }
				clazz.addInterceptor(method.getName(), method.getParameterTypes(), interceptor);
			}

			return clazz.toBytecode();
		} catch (Exception e) {
			logger.warn("modify fail. Cause:{}", e.getMessage(), e);
			return null;
		}
	}
}