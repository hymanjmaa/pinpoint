package com.navercorp.pinpoint.profiler.modifier.connector.httpclient4;

import java.security.ProtectionDomain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.navercorp.pinpoint.bootstrap.Agent;
import com.navercorp.pinpoint.bootstrap.instrument.ByteCodeInstrumentor;
import com.navercorp.pinpoint.bootstrap.instrument.InstrumentClass;
import com.navercorp.pinpoint.bootstrap.instrument.InstrumentException;
import com.navercorp.pinpoint.bootstrap.interceptor.Interceptor;
import com.navercorp.pinpoint.profiler.modifier.AbstractModifier;

/**
 * HTTP Client 4.3 이상 버전에 있는 클래스.
 * 
 * @author netspider
 * 
 */
public class ClosableHttpAsyncClientModifier extends AbstractModifier {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public ClosableHttpAsyncClientModifier(ByteCodeInstrumentor byteCodeInstrumentor, Agent agent) {
		super(byteCodeInstrumentor, agent);
	}

	@Override
	public String getTargetClass() {
		return "org/apache/http/impl/nio/client/CloseableHttpAsyncClient";
	}

	@Override
	public byte[] modify(ClassLoader classLoader, String javassistClassName, ProtectionDomain protectedDomain, byte[] classFileBuffer) {
		if (logger.isInfoEnabled()) {
			logger.info("Modifing. {} @ {}", javassistClassName, classLoader);
		}


		try {
			InstrumentClass aClass = byteCodeInstrumentor.getClass(classLoader, javassistClassName, classFileBuffer);
 
			/**
			 * 아래 두 메소드는 오버로드 되었으나 호출 관계가 없어 scope 없어도 됨.
			 */
			Interceptor executeInterceptor = byteCodeInstrumentor.newInterceptor(classLoader,
					protectedDomain,
					"com.navercorp.pinpoint.profiler.modifier.connector.httpclient4.interceptor.AsyncClientExecuteInterceptor");
			
			String[] executeParams = new String[] { 
					"org.apache.http.HttpHost", 
					"org.apache.http.HttpRequest", 
					"org.apache.http.protocol.HttpContext", 
					"org.apache.http.concurrent.FutureCallback"
					};
			aClass.addInterceptor("execute", executeParams, executeInterceptor);
			
			/**
			 * 
			 */
			Interceptor internalExecuteInterceptor = byteCodeInstrumentor.newInterceptor(classLoader,
					protectedDomain,
					"com.navercorp.pinpoint.profiler.modifier.connector.httpclient4.interceptor.AsyncInternalClientExecuteInterceptor");
			
			String[] internalExecuteParams = new String[] { 
					"org.apache.http.nio.protocol.HttpAsyncRequestProducer", 
					"org.apache.http.nio.protocol.HttpAsyncResponseConsumer", 
					"org.apache.http.concurrent.FutureCallback"
					};
			aClass.addInterceptor("execute", internalExecuteParams, internalExecuteInterceptor);
			
			return aClass.toBytecode();
		} catch (InstrumentException e) {
			logger.info("modify fail. Cause:{}", e.getMessage(), e);
			return null;
		}
	}
}
