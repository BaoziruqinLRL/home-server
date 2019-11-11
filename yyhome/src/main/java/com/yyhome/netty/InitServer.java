package com.yyhome.netty;

import com.baozi.business.BusinessExecutor;
import com.baozi.constructor.ServerConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author miluo
 * @date 2019-10-31
 */
@Component
public class InitServer {

    @PostConstruct
    private void init(){
        Map<String, BusinessExecutor> executorMap = new HashMap<>(2);
        ServerConstructor.setExecutorMap(executorMap);
        ServerConstructor.setPort(8891);
        ServerConstructor.setHeartbeatType("server-heartbeat");
        ServerConstructor.setHeartbeatReply("client-heartbeat");
        // 毫秒级时间
        ServerConstructor.setResendTimeWheel(new long[]{100,500,1000,5000});
        ServerConstructor.setServerHeartbeat(false);
        // ssl配置，可以不开启
        ServerConstructor.setSsl(false);
        // 启动
        ServerConstructor.start();
    }
}
