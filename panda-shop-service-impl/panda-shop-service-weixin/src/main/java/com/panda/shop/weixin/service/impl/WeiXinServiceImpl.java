package com.panda.shop.weixin.service.impl;


import com.panda.shop.base.BaseApiService;
import com.panda.shop.base.BaseResponse;
import com.panda.shop.entity.AppEntity;
import com.panda.shop.weixin.service.WeiXinService;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WeiXinServiceImpl extends BaseApiService<AppEntity> implements WeiXinService {

    public BaseResponse<AppEntity> getApp() {
        return setResultSuccess(new AppEntity("panda","123456"));
    }
}
