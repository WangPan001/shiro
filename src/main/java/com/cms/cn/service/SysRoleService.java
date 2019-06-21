package com.cms.cn.service;

import com.cms.cn.model.Request.RoleRequest;
import com.cms.cn.model.Response.BaseResponse;

import java.util.List;

/**
 * @ClassName RoleService
 * @Description Todo
 * @Author wangpan
 * @Date 2019/5/22 14:49
 * @Version 1.0
 **/

public interface SysRoleService {

    public BaseResponse findByUserid(RoleRequest roleRequest);

}
