package com.zyz.example.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SysResource {

    private Long id;

    private Long parentId;

    private String name;

    private String code;

    /**
     * 1=菜单，2=按钮，3=功能
     */
    private String type;

    private Integer sort;

    private String usable;

    private String hidden;

    private String remarks;

    private Long createBy;

    private Date createTime;

    private List<SysResource> children;

}
