package com.cms.cn.service;

import com.cms.cn.model.Request.MenusRequest;
import com.cms.cn.model.Response.BaseResponse;

/**
 * @ClassName MenuService
 * @Description Todo
 * @Author wangpan
 * @Date 2019/5/22 14:46
 * @Version 1.0
 **/
public interface SysMenuService {

    public BaseResponse getAllMenuByRoleId(MenusRequest menusRequest) throws Exception;

}
