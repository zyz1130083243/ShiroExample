package com.zyz.example.mapper;

import com.zyz.example.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface SysUserMapper {

    @Select("select * from sys_user where user_name = #{userName} and user_state = 1 limit 1")
    SysUser getByUserName(@Param("userName") String userName);

    @Select("select * from sys_user where user_name = #{userName} and password = #{password} limit 1")
    SysUser findLoginUser(@Param("userName") String userName, @Param("password") String password);
}
