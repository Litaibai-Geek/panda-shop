package com.panda.shop.weixin.service.impl;

import com.panda.shop.entity.AppEntity;
import com.panda.shop.weixin.service.WeiXinService;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WeiXinServiceImpl implements WeiXinService {

    public AppEntity getApp() {
        return new AppEntity("panda","123456");
    }
}
