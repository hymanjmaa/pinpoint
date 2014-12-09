package com.navercorp.pinpoint.profiler.modifier.connector.jdkhttpconnector;

import java.security.ProtectionDomain;

import com.navercorp.pinpoint.bootstrap.Agent;
import com.navercorp.pinpoint.bootstrap.instrument.ByteCodeInstrumentor;
import com.navercorp.pinpoint.bootstrap.instrument.InstrumentClass;
import com.navercorp.pinpoint.bootstrap.instrument.InstrumentException;
import com.navercorp.pinpoint.profiler.modifier.AbstractModifier;
import com.navercorp.pinpoint.profiler.modifier.connector.jdkhttpconnector.interceptor.ConnectMethodInterceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO classloader문제 있음.
 * @author netspider
 * 
 */
public class HttpURLConnectionModifier extends AbstractModifier {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public HttpURLConnectionModifier(ByteCodeInstrumentor byteCodeInstrumentor, Agent agent) {
		super(byteCodeInstrumentor, agent);
	}

	public String getTargetClass() {
		return "sun/net/www/protocol/http/HttpURLConnection";
	}

	public byte[] modify(ClassLoader classLoader, String javassistClassName, ProtectionDomain protectedDomain, byte[] classFileBuffer) {
		if (logger.isInfoEnabled()) {
			logger.info("Modifing. {}", javassistClassName);
		}

		try {
			InstrumentClass aClass = byteCodeInstrumentor.getClass(classLoader, javassistClassName, classFileBuffer);
            ConnectMethodInterceptor connectMethodInterceptor = new ConnectMethodInterceptor();
            aClass.addInterceptorCallByContextClassLoader("connect", null, connectMethodInterceptor);

			return aClass.toBytecode();
		} catch (InstrumentException e) {
			logger.warn("HttpURLConnectionModifier fail. Caused:", e.getMessage(), e);
			return null;
		}
	}
}