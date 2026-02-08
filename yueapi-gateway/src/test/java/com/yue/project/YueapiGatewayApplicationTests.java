package com.yue.project;

import com.yue.project.rpc.RpcDemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class YueapiGatewayApplicationTests {

    @DubboReference
    private RpcDemoService rpcDemoService;

    @Test
    void testRpc() {
        System.out.println(rpcDemoService.sayHello("world"));// 打印验证

    }
}
