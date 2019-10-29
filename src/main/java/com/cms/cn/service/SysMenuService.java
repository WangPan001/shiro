package com.cms.cn.service;

import com.cms.cn.model.request.MenuRequest;
import com.cms.cn.model.request.MenusRequest;
import com.cms.cn.model.response.BaseResponse;

/**
 * @ClassName MenuService
 * @Description Todo
 * @Author wangpan
 * @Date 2019/5/22 14:46
 * @Version 1.0
 **/
public interface SysMenuService {

    BaseResponse getAllMenuByRoleId(MenusRequest menusRequest) throws Exception;

    BaseResponse getAllMenus(MenusRequest menusRequest);

    BaseResponse addMenu(MenuRequest menuRequest);
}
