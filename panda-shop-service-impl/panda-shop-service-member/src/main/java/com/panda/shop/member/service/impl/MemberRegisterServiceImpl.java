package com.panda.shop.member.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.panda.shop.base.BaseApiService;
import com.panda.shop.base.BaseResponse;
import com.panda.shop.constants.Constants;
import com.panda.shop.core.bean.PandaBeanUtils;
import com.panda.shop.core.utils.MD5Util;
import com.panda.shop.member.feign.VerificaCodeServiceFeign;
import com.panda.shop.member.input.dto.UserInpDTO;
import com.panda.shop.member.mapper.UserMapper;
import com.panda.shop.member.mapper.entity.UserDo;
import com.panda.shop.member.output.dto.UserOutDTO;
import com.panda.shop.member.service.MemberRegisterService;
import com.panda.shop.member.service.MemberService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description:
 * @author: 李太白
 * @date: 2021-10-04 23:16
 **/
@RestController
public class MemberRegisterServiceImpl extends BaseApiService<JSONObject> implements MemberRegisterService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VerificaCodeServiceFeign verificaCodeServiceFeign;
    @Autowired
    private MemberService memberService;

    @Transactional
    public BaseResponse<JSONObject> register(@RequestBody UserInpDTO userInpDTO, String registCode) {
        // 1.参数验证
        String userName = userInpDTO.getUserName();
        if (StringUtils.isEmpty(userName)) {
            return setResultError("用户名称不能为空!");
        }
        String mobile = userInpDTO.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }
        String password = userInpDTO.getPassword();
        if (StringUtils.isEmpty(password)) {
            return setResultError("密码不能为空!");
        }
        // 2.验证码注册码是否正确 暂时省略 会员调用微信接口实现注册码验证
        BaseResponse<JSONObject> verificaWeixinCode = verificaCodeServiceFeign.verificaWeixinCode(mobile, registCode);
        if (!verificaWeixinCode.getCode().equals(Constants.HTTP_RES_CODE_200)) {
            return setResultError(verificaWeixinCode.getMsg());
        }
        // 3.对用户的密码进行加密 // MD5 可以解密 暴力破解
        String newPassword = MD5Util.MD5(password);
        userInpDTO.setPassword(newPassword);
        // 4.注册时判断数据库是否存在此手机号码
        BaseResponse<UserOutDTO> reusltUserInfo = memberService.existMobile(mobile);
        if (reusltUserInfo.getCode().equals(Constants.HTTP_RES_CODE_200)) {
            return setResultError(mobile+"已注册，注册失败!");
        }

        // DTO转DO
        /*UserDo userDo = new UserDo();
        BeanUtils.copyProperties(userInpDTO,userDo);*/
        UserDo userDo = PandaBeanUtils.dtoToDo(userInpDTO, UserDo.class);

        // 5.调用数据库插入数据
        return userMapper.register(userDo) > 0 ? setResultSuccess("注册成功") : setResultError("注册失败!");
    }

}
