package com.cms.cn.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysMenu implements Serializable {
    private Integer id;

    private Integer parentMenuId;

    private Integer menuId;

    private String menuName;

    private String permission;

    private Integer sort;

    private String href;

    private String icon;

    private Integer isShow;

    private String menuType;

    private Integer useable;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private String remarks;

    private Integer delFlag;


}