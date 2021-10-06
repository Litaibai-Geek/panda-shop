package com.panda.shop.member.feign;

import com.panda.shop.member.service.MemberService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description:
 * @author: 李太白
 * @date: 2021-10-06 22:46
 **/
@FeignClient("app-panda-shop-member")
public interface MemberServiceFeign extends MemberService {

}

