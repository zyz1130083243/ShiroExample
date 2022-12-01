package com.zyz.example.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysUser {

    /**
     * 系统用户ID
     */
    private Long id;
    /**
     * 用户登录名
     */
    private String userName;
    /**
     * 登录密码
     */
    private String passWord;
    /**
     * 真实名字
     */
    private String realName;
    /**
     * 用户状态；0禁用，1启用
     */
    private Integer userState;
    /**
     *
     */
    private Date createTime;
    /**
     *
     */
    private Long createBy;
    /**
     *
     */
    private Date updateTime;


    private Long updateBy;

}
