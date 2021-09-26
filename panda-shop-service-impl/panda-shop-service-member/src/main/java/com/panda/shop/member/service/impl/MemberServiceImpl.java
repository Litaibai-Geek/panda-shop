package com.panda.shop.member.service.impl;

import com.panda.shop.entity.AppEntity;
import com.panda.shop.member.feign.WeiXinServiceFeign;
import com.panda.shop.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: 李太白
 * @date: 2021-09-25 21:27
 **/
@RestController
public class MemberServiceImpl implements MemberService {

    @Autowired
    private WeiXinServiceFeign weiXinServiceFeign;

    public AppEntity memberToWeiXin() {
        return weiXinServiceFeign.getApp();
    }
}
