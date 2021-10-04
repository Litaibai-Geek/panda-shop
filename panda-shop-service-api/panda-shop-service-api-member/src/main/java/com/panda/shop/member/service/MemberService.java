package com.panda.shop.member.service;

import com.panda.shop.base.BaseResponse;
import com.panda.shop.entity.AppEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;


@Api(tags = "会员服务实现接口")
public interface MemberService {
    /**
     * 会员调用微信
     * @return
     */
    @ApiOperation(value = "会员服务调用微信服务")
    @GetMapping("/memberToWeiXin")
    public BaseResponse<AppEntity> memberToWeiXin();
}
