package com.panda.shop.weixin.service;

import com.panda.shop.entity.AppEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

@Api(tags = "微信服务接口")
public interface WeiXinService {

    @ApiOperation(value = "微信应用服务接口")
    @GetMapping("/getApp")
    public AppEntity getApp();
}
