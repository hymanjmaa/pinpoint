package com.navercorp.pinpoint.profiler.receiver;

import org.apache.thrift.TBase;

/**
 * @author koo.taejin
 */
public interface ProfilerCommandService {

	Class<? extends TBase> getCommandClazz();
	
}
