package com.panda.shop.member.service;

import com.alibaba.fastjson.JSONObject;
import com.panda.shop.base.BaseResponse;
import com.panda.shop.member.input.dto.UserLoginInpDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description: 用户登录服务接口
 * @author: 李太白
 * @date: 2021-10-05 23:01
 **/
@Api(tags = "用户登录服务接口")
public interface MemberLoginService {
    /**
     * 用户登陆接口
     *
     * @param userEntity
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "会员用户登陆信息接口")
    BaseResponse<JSONObject> login(@RequestBody UserLoginInpDTO userLoginInpDTO);

}