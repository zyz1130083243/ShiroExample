package com.zyz.example.entity;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

@Data
public class SysRole {

    /**
     * 主键
     */
    private Long id;

    /**
     *
     */
    private Long parentId;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色编码
     */
    private String enName;

    private Integer sort;

    private String usable;

    private String hidden;

    private Long createBy;


    private Date createTime;

    private List<SysRole> children;

}
