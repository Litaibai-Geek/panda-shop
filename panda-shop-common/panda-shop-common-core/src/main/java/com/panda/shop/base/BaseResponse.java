package com.panda.shop.base;

import lombok.Data;

/**
 * @description: 接口统一返回状态码
 * @author: 李太白
 * @date: 2021-10-04 17:53
 **/
@Data
public class BaseResponse<T> {

    /**
     * 返回码
     */
    private Integer code;
    /**
     * 消息
     */
    private String msg;
    /**
     * 返回
     */
    private T data;
    // 分页

    public BaseResponse() {

    }

    public BaseResponse(Integer code, String msg, T data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}