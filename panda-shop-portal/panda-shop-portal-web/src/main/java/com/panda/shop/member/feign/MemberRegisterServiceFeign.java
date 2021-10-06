package com.panda.shop.member.feign;

import com.panda.shop.member.service.MemberRegisterService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description:
 * @author: 李太白
 * @date: 2021-10-06 18:37
 **/
@FeignClient("app-panda-shop-member")
public interface MemberRegisterServiceFeign extends MemberRegisterService {

}
