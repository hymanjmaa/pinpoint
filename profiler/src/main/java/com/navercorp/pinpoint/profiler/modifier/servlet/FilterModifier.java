package com.navercorp.pinpoint.profiler.modifier.servlet;

import java.security.ProtectionDomain;

import com.navercorp.pinpoint.bootstrap.Agent;
import com.navercorp.pinpoint.bootstrap.instrument.ByteCodeInstrumentor;
import com.navercorp.pinpoint.bootstrap.instrument.InstrumentClass;
import com.navercorp.pinpoint.bootstrap.instrument.InstrumentException;
import com.navercorp.pinpoint.bootstrap.interceptor.Interceptor;
import com.navercorp.pinpoint.profiler.modifier.AbstractModifier;
import com.navercorp.pinpoint.profiler.modifier.method.interceptor.MethodInterceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author netspider
 * 
 */
public class FilterModifier extends AbstractModifier {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public FilterModifier(ByteCodeInstrumentor byteCodeInstrumentor, Agent agent) {
		super(byteCodeInstrumentor, agent);
	}

	public String getTargetClass() {
		return "javax/servlet/Filter";
	}

	public byte[] modify(ClassLoader classLoader, String javassistClassName, ProtectionDomain protectedDomain, byte[] classFileBuffer) {
		if (logger.isInfoEnabled()) {
			logger.info("Modifing. {}", javassistClassName);
		}

		try {
			Interceptor doFilterInterceptor = new MethodInterceptor();

//			setTraceContext(doFilterInterceptor);

			InstrumentClass servlet = byteCodeInstrumentor.getClass(classLoader, javassistClassName, classFileBuffer);

			servlet.addInterceptor("doFilter", new String[] { "javax.servlet.ServletRequest", "javax.servlet.ServletResponse", "javax.servlet.FilterChain" }, doFilterInterceptor);

			return servlet.toBytecode();
		} catch (InstrumentException e) {
			logger.warn("modify fail. Cause:{}", e.getMessage(), e);
			return null;
		}
	}
}