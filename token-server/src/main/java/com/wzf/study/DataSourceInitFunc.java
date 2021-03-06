package com.wzf.study;

import com.alibaba.csp.sentinel.cluster.flow.rule.ClusterFlowRuleManager;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;

public class DataSourceInitFunc implements InitFunc {

    private final String remoteAddress="192.168.1.106";

    private final String groupId="SENTINEL_GROUP";

    private final String FLOW_POSTFIX="-flow-rules";

    @Override
    public void init() throws Exception {

        ClusterFlowRuleManager.setPropertySupplier(namespace ->{
            ReadableDataSource<String,List<FlowRule>> rds = new NacosDataSource<List<FlowRule>>(remoteAddress,groupId,namespace+FLOW_POSTFIX,
                    source -> JSON.parseObject(source,new TypeReference<List<FlowRule>>(){}));
            return rds.getProperty();
        });

    }
}
