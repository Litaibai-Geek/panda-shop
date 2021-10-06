package com.panda.shop.member.mapper.entity;

import com.panda.shop.base.BaseDo;
import lombok.Data;

/**
 * @description:
 * @author: 李太白
 * @date: 2021-10-05 23:05
 **/
@Data
public class UserTokenDo extends BaseDo {
    /**
     * id
     */
    private Long id;
    /**
     * 用户token
     */
    private String token;
    /**
     * 登陆类型
     */
    private String loginType;

    /**
     * 设备信息
     */
    private String deviceInfor;
    /**
     * 用户userId
     */
    private Long userId;

}