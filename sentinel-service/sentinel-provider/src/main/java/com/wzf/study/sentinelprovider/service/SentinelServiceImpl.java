package com.wzf.study.sentinelprovider.service;

import com.wzf.study.ISentinelService;
import org.apache.dubbo.config.annotation.Service;

import java.time.LocalDateTime;

@Service
public class SentinelServiceImpl implements ISentinelService {
    @Override
    public String sayHello() {
        return "HELLO "+LocalDateTime.now();
    }
}
