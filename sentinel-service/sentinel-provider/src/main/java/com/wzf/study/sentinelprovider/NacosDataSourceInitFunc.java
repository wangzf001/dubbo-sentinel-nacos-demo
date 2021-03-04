package com.wzf.study.sentinelprovider;

import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientAssignConfig;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientConfig;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientConfigManager;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;

public class NacosDataSourceInitFunc implements InitFunc {

    private final String CLUSTER_SERVER_HOST="localhost";//token server IP
    private final int CLUSTER_SERVER_PORT=9999;
    private final int REQUEST_TIME_OUT=200000;//请求超时时间

    private final String APP_NAME="App-Wzf";

    private final String remoteAddress="192.168.1.106";//Nacos配置中心
    private final String groupId="SENTINEL_GROUP";
    private final String FLOW_POSTFIX="-flow-rules";//dataId(name + postfix)

    //从token-server上获取令牌
    @Override
    public void init() throws Exception {
        //加载集群信息
        loadClusterClientConfig();
        registryClusterFlowRuleProperty();
    }

    //从token-server上获取令牌
    private void loadClusterClientConfig(){
        ClusterClientAssignConfig assignConfig = new ClusterClientAssignConfig();
        assignConfig.setServerHost(CLUSTER_SERVER_HOST);
        assignConfig.setServerPort(CLUSTER_SERVER_PORT);
        ClusterClientConfigManager.applyNewAssignConfig(assignConfig);

        ClusterClientConfig clientConfig = new ClusterClientConfig();
        clientConfig.setRequestTimeout(REQUEST_TIME_OUT);
        ClusterClientConfigManager.applyNewConfig(clientConfig);
    }

    //高可用方案，从nacos直接获取配置信息
    private void registryClusterFlowRuleProperty(){
        ReadableDataSource<String,List<FlowRule>> rds = new NacosDataSource<List<FlowRule>>(remoteAddress,groupId,APP_NAME+FLOW_POSTFIX,
                source -> JSON.parseObject(source,new TypeReference<List<FlowRule>>(){}));
        FlowRuleManager.register2Property(rds.getProperty());
    }

}
