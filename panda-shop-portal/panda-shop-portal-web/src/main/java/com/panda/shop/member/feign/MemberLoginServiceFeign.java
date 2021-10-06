package com.panda.shop.member.feign;

import com.panda.shop.member.service.MemberLoginService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description:
 * @author: 李太白
 * @date: 2021-10-06 22:33
 **/
@FeignClient("app-panda-shop-member")
public interface MemberLoginServiceFeign extends MemberLoginService {

}
