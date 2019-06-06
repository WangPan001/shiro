package com.cms.cn.dao;

import com.cms.cn.model.Request.MenusRequest;
import com.cms.cn.model.Response.MenusResponse;

import java.util.List;

public interface SysMenuMapper {
    public List<MenusResponse> getAllMenuByRoleId(MenusRequest menusRequest);
}