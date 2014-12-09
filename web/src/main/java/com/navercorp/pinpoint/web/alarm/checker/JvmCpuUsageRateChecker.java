package com.navercorp.pinpoint.web.alarm.checker;

import java.util.Map;

import com.navercorp.pinpoint.web.alarm.collector.AgentStatDataCollector;
import com.navercorp.pinpoint.web.alarm.vo.Rule;

public class JvmCpuUsageRateChecker extends AgentChecker {
    
    public JvmCpuUsageRateChecker(AgentStatDataCollector dataCollector, Rule rule) {
        super(rule, "%", dataCollector);
    }

    @Override
    protected Map<String, Long> getAgentValues() {
        return ((AgentStatDataCollector)dataCollector).getJvmCpuUsageRate();
    }
    
}
