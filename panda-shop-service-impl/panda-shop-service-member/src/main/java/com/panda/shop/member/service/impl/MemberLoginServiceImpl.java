package com.panda.shop.member.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.panda.shop.base.BaseApiService;
import com.panda.shop.base.BaseResponse;
import com.panda.shop.constants.Constants;
import com.panda.shop.core.token.GenerateToken;
import com.panda.shop.core.transaction.RedisDataSoureceTransaction;
import com.panda.shop.core.utils.MD5Util;
import com.panda.shop.member.input.dto.UserLoginInpDTO;
import com.panda.shop.member.mapper.UserMapper;
import com.panda.shop.member.mapper.UserTokenMapper;
import com.panda.shop.member.mapper.entity.UserDo;
import com.panda.shop.member.mapper.entity.UserTokenDo;
import com.panda.shop.member.service.MemberLoginService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: 李太白
 * @date: 2021-10-05 23:03
 **/
@RestController
public class MemberLoginServiceImpl extends BaseApiService<JSONObject> implements MemberLoginService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GenerateToken generateToken;
    @Autowired
    private UserTokenMapper userTokenMapper;
    /**
     * 手动事务工具类
     */
    @Autowired
    private RedisDataSoureceTransaction manualTransaction;

    @Override
    public BaseResponse<JSONObject> login(@RequestBody UserLoginInpDTO userLoginInpDTO) {
        // 1.验证参数
        String mobile = userLoginInpDTO.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }
        String password = userLoginInpDTO.getPassword();
        if (StringUtils.isEmpty(password)) {
            return setResultError("密码不能为空!");
        }
        // 判断登陆类型
        String loginType = userLoginInpDTO.getLoginType();
        if (StringUtils.isEmpty(loginType)) {
            return setResultError("登陆类型不能为空!");
        }
        // 目的是限制范围
        if (!(loginType.equals(Constants.MEMBER_LOGIN_TYPE_ANDROID) || loginType.equals(Constants.MEMBER_LOGIN_TYPE_IOS)
                || loginType.equals(Constants.MEMBER_LOGIN_TYPE_PC))) {
            return setResultError("登陆类型出现错误!");
        }

        // 设备信息
        String deviceInfor = userLoginInpDTO.getDeviceInfor();
        if (StringUtils.isEmpty(deviceInfor)) {
            return setResultError("设备信息不能为空!");
        }

        // 2.对登陆密码实现加密
        String newPassWord = MD5Util.MD5(password);
        // 3.使用手机号码+密码查询数据库 ，判断用户是否存在
        UserDo userDo = userMapper.login(mobile, newPassWord);
        if (userDo == null) {
            return setResultError("用户名称或者密码错误!");
        }
        TransactionStatus transactionStatus = null;
        try {

            // 1.获取用户UserId
            Long userId = userDo.getUserId();
            // 2.生成用户令牌Key
            String keyPrefix = Constants.MEMBER_TOKEN_KEYPREFIX + loginType;
            // 5.根据userId+loginType 查询当前登陆类型账号之前是否有登陆过，如果登陆过 清除之前redistoken
            UserTokenDo userTokenDo = userTokenMapper.selectByUserIdAndLoginType(userId, loginType);
            transactionStatus = manualTransaction.begin();
            // // ####开启手动事务
            if (userTokenDo != null) {
                // 如果登陆过 清除之前redistoken
                String oriToken = userTokenDo.getToken();
                // 移除Token
                generateToken.removeToken(oriToken);
                int updateTokenAvailability = userTokenMapper.updateTokenAvailability(oriToken);
                if (updateTokenAvailability < 0) {
                    manualTransaction.rollback(transactionStatus);
                    return setResultError("系统错误");
                }
            }

            // 4.将用户生成的令牌插入到Token记录表中
            UserTokenDo userToken = new UserTokenDo();
            userToken.setUserId(userId);
            userToken.setLoginType(userLoginInpDTO.getLoginType());
            String newToken = generateToken.createToken(keyPrefix, userId + "");
            userToken.setToken(newToken);
            userToken.setDeviceInfor(deviceInfor);
            int result = userTokenMapper.insertUserToken(userToken);
            if (result < 0) {
                manualTransaction.rollback(transactionStatus);
                return setResultError("系统错误!");
            }

            // #######提交事务
            JSONObject data = new JSONObject();
            data.put("token", newToken);
            manualTransaction.commit(transactionStatus);
            return setResultSuccess(data);
        } catch (Exception e) {
            try {
                // 回滚事务
                manualTransaction.rollback(transactionStatus);
            } catch (Exception e1) {
            }
            return setResultError("系统错误!");
        }

    }

}
