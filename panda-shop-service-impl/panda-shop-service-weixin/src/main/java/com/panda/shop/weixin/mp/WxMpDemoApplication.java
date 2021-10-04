package com.panda.shop.weixin.mp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@SpringBootApplication
//@ComponentScan(basePackages={"com.panda.shop"})
public class WxMpDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxMpDemoApplication.class, args);
    }
}
