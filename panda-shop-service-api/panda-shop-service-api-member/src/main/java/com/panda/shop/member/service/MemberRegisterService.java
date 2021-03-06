package com.panda.shop.member.service;

import com.panda.shop.member.input.dto.UserInpDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.alibaba.fastjson.JSONObject;
import com.panda.shop.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @description: 会员注册接口
 * @author: 李太白
 * @date: 2021-10-04 21:54
 **/
@Api(tags = "会员注册接口")
public interface MemberRegisterService {
    /**
     * 用户注册接口
     *
     * @param userEntity
     * @return
     */
    @PostMapping("/register")
    @ApiOperation(value = "会员用户注册信息接口")
    BaseResponse<JSONObject> register(@RequestBody UserInpDTO userInpDTO,
                                      @RequestParam("registCode") String registCode);

}