package com.cms.cn.dao;

import com.cms.cn.model.request.MenuRequest;
import com.cms.cn.model.request.MenusRequest;
import com.cms.cn.model.response.MenusResponse;

import java.util.List;

public interface SysMenuMapper {
    List<MenusResponse> getAllMenuByRoleId(MenusRequest menusRequest);

    List<MenusResponse> getAllMenuByParentId(MenusRequest menusRequest);

    int addMenu(MenuRequest menuRequest);
}