package com.panda.shop.member.mapper;

import com.panda.shop.member.mapper.entity.UserTokenDo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @description:
 * @author: 李太白
 * @date: 2021-10-05 23:11
 **/
public interface UserTokenMapper {

    /**
     * 根据userid+loginType +is_availability=0 进行查询
     *
     * @param userId
     * @param loginType
     * @return
     */
    @Select("SELECT id as id ,token as token ,login_type as LoginType, device_infor as deviceInfor ,is_availability as isAvailability,user_id as userId"
            + "" + ""
            + " , create_time as createTime,update_time as updateTime   FROM panda_user_token WHERE user_id=#{userId} AND login_type=#{loginType} and is_availability ='0'; ")
    UserTokenDo selectByUserIdAndLoginType(@Param("userId") Long userId, @Param("loginType") String loginType);

    /**
     * 根据userId+loginType token的状态修改为不可用
     *
     * @param userId
     * @param loginType
     * @return
     */
    // @Update(" update panda_user_token set is_availability
    // ='1',update_time=now() where user_id=#{userId} and login_type
    // =#{loginType} ")
    // int updateTokenAvailability(@Param("userId") Long userId,
    // @Param("loginType") String loginType);
    @Update(" update panda_user_token set is_availability  ='1', update_time=now() where token=#{token}")
    int updateTokenAvailability(@Param("token") String token);

    // INSERT INTO `panda_user_token` VALUES ('2', '1', 'PC', '苹果7p', '1', '1');

    /**
     * token记录表中插入一条记录
     *
     * @param userTokenDo
     * @return
     */
    @Insert("    INSERT INTO `panda_user_token` VALUES (null, #{token},#{loginType}, #{deviceInfor}, 0, #{userId} ,now(),null ); ")
    int insertUserToken(UserTokenDo userTokenDo);
}