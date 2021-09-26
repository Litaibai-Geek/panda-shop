package com.panda.shop.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description: 会员服务
 * @author: 李太白
 * @date: 2021-09-25 22:02
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class AppMember {

    public static void main(String[] args) {
        SpringApplication.run(AppMember.class,args);
    }
}
