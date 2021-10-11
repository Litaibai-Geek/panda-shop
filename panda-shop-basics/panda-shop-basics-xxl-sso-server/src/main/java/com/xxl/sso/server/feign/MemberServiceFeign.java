package com.xxl.sso.server.feign;

import com.panda.shop.member.service.MemberService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-panda-shop-member")
public interface MemberServiceFeign extends MemberService {

}
