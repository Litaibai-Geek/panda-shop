package com.panda.shop.weixin.service;

import com.panda.shop.entity.AppEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface WeiXinService {

    @GetMapping("/getApp")
    public AppEntity getApp();
}
