package com.navercorp.pinpoint.web.service;

/**
 * 
 * @author netspider
 * 
 */
public interface AdminService {
	void removeApplicationName(String applicationName);
	
	void removeAgentId(String applicationName, String agentId);
}
