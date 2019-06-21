package com.cms.cn.model.Response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName MenusResponse
 * @Description Todo
 * @Author wangpan
 * @Date 2019/6/3 14:14
 * @Version 1.0
 **/
@Data
public class MenusResponse implements Serializable {
    private String parentMenuId;

    private String menuId;

    private String menuName;

    private String permission;

    private String sort;

    private String href;

    private String icon;

    private String isShow;

    private String menuType;

    private String useable;

    private String createBy;

    private String createDate;

    private String updateBy;

    private String updateDate;

    private String remarks;

    private String delFlag;

    private List<MenusResponse> children;
}
