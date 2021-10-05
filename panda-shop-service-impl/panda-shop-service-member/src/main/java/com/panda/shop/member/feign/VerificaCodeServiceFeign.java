package com.panda.shop.member.feign;

import com.panda.shop.weixin.service.VerificaCodeService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description:
 * @author: 李太白
 * @date: 2021-10-05 19:05
 **/
@FeignClient("app-panda-shop-weixin")
public interface VerificaCodeServiceFeign extends VerificaCodeService {
}
