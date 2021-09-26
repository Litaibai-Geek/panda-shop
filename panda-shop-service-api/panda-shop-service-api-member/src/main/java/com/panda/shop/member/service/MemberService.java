package com.panda.shop.member.service;

import com.panda.shop.entity.AppEntity;
import org.springframework.web.bind.annotation.GetMapping;


public interface MemberService {
    /**
     * 会员调用微信
     * @return
     */
    @GetMapping("/memberToWeiXin")
    public AppEntity memberToWeiXin();
}
