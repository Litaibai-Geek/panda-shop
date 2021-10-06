package com.panda.shop.base;

import lombok.Data;

import java.util.Date;

/**
 * @description: BaseDo
 * @author: 李太白
 * @date: 2021-10-05 23:06
 **/
@Data
public class BaseDo {
    /**
     * 注册时间
     */
    private Date createTime;
    /**
     * 修改时间
     *
     */
    private Date updateTime;
    /**
     * id
     */
    private Long id;

    /**
     * 是否可用 0可用 1不可用
     */
    private Long isAvailability;
}

