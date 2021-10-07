package com.panda.shop.member.service;

import com.alibaba.fastjson.JSONObject;
import com.panda.shop.base.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: 用户授权接口
 * @author: 李太白
 * @date: 2021-10-07 18:29
 **/
public interface QQAuthoriService {
    /**
     * 根据 openid查询是否已经绑定,如果已经绑定，则直接实现自动登陆
     *
     * @param openid
     * @return
     */
    @RequestMapping("/findByOpenId")
    BaseResponse<JSONObject> findByOpenId(@RequestParam("qqOpenId") String qqOpenId);

}
