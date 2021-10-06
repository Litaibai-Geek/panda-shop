package com.panda.shop.member.feign;

import com.panda.shop.weixin.service.WeiXinService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description:
 * @author: 李太白
 * @date: 2021-09-25 21:58
 **/
@FeignClient("app-panda-shop-weixin")
public interface WeiXinServiceFeign extends WeiXinService {

    /*@GetMapping("/getApp")
    public AppEntity getApp();*/
}
