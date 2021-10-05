package com.panda.shop.member.mapper;

import com.panda.shop.member.mapper.entity.UserDo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @description:
 * @author: 李太白
 * @date: 2021-10-04 23:25
 **/
public interface UserMapper {

    @Insert("INSERT INTO `panda_user` VALUES (null,#{mobile}, #{email}, #{password}, #{userName}, null, null, null, '1', null, null, null);")
    int register(UserDo userEntity);

    @Select("SELECT * FROM panda_user WHERE MOBILE=#{mobile};")
    UserDo existMobile(@Param("mobile") String mobile);
}
