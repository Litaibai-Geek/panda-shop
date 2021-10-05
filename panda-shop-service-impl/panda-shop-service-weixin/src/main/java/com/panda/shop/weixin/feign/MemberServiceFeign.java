package com.panda.shop.weixin.feign;

import com.panda.shop.member.service.MemberService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description:
 * @author: 李太白
 * @date: 2021-10-05 15:08
 **/
@FeignClient("app-panda-shop-member")
public interface MemberServiceFeign extends MemberService {

}