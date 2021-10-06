package com.panda.shop.member.controller.req.vo;

import lombok.Data;

/**
 * @description:
 * @author: 李太白
 * @date: 2021-10-06 22:38
 **/
@Data
public class LoginVo {

    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 手机密码
     */
    private String password;
    /**
     * 图形验证码
     */
    private String graphicCode;

}
