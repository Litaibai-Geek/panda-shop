package com.panda.shop.member.mapper;

import com.panda.shop.member.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: 李太白
 * @date: 2021-10-04 23:25
 **/
public interface UserMapper {

    @Insert("INSERT INTO `panda_user` VALUES (null,#{mobile}, #{email}, #{password}, #{userName}, null, null, null, '1', null, null, null);")
    int register(UserEntity userEntity);

    @Select("SELECT * FROM panda_user WHERE MOBILE=#{mobile};")
    UserEntity existMobile(@Param("mobile") String mobile);
}
