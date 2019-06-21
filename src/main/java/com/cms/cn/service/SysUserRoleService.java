package com.cms.cn.service;


import com.cms.cn.model.Request.UserRoleRequest;
import com.cms.cn.model.Response.BaseResponse;

public interface SysUserRoleService {

    BaseResponse selectUserRoles(UserRoleRequest roleUserRequest);
}
