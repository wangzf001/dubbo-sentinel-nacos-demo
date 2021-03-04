package com.wzf.study.sentinelweb;

import com.wzf.study.ISentinelService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Reference
    private ISentinelService sentinelService;

    @GetMapping("hello")
    public String hello(){
        return sentinelService.sayHello();
    }
}
