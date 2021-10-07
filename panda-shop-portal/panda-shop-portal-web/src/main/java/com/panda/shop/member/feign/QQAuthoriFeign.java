package com.panda.shop.member.feign;

import com.panda.shop.member.service.QQAuthoriService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description:
 * @author: 李太白
 * @date: 2021-10-07 18:27
 **/
@FeignClient("app-panda-shop-member")
public interface QQAuthoriFeign extends QQAuthoriService {

}