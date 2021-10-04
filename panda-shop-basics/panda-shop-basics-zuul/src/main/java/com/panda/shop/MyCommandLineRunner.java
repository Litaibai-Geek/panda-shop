package com.panda.shop;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @description:监听阿波罗配置中心（每一次修改发布都会走run方法）
 * @author: 李太白
 * @date: 2021-10-04 13:45
 **/
@Component
@Slf4j
public class MyCommandLineRunner implements CommandLineRunner {
    @ApolloConfig
    private Config config;
    @Override
    public void run(String... args) throws Exception {
        config.addChangeListener(new ConfigChangeListener() {
            @Override
            public void onChange(ConfigChangeEvent configChangeEvent) {
                log.info("===========阿波罗配置中心监听===========" + configChangeEvent.changedKeys().toString());
            }
        });
    }
}
