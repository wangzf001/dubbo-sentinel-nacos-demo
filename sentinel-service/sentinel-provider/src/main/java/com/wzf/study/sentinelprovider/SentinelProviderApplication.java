package com.wzf.study.sentinelprovider;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class SentinelProviderApplication {

    public static void main(String[] args) {
        initFlowRules();
        SpringApplication.run(SentinelProviderApplication.class, args);
    }

    public static void initFlowRules(){
        FlowRule flowRule = new FlowRule();
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setCount(20);
        flowRule.setResource("com.wzf.study.ISentinelService:sayHello()");
        flowRule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_WARM_UP);
        FlowRuleManager.loadRules(Collections.singletonList(flowRule));
    }
}
