package com.cms.cn.dao;

import com.cms.cn.model.Request.MenuRequest;
import com.cms.cn.model.Request.MenusRequest;
import com.cms.cn.model.Response.MenusResponse;

import java.util.List;

public interface SysMenuMapper {
    List<MenusResponse> getAllMenuByRoleId(MenusRequest menusRequest);

    List<MenusResponse> getAllMenuByParentId(MenusRequest menusRequest);

    int addMenu(MenuRequest menuRequest);
}