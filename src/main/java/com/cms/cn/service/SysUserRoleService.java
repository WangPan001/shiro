package com.cms.cn.service;


import com.cms.cn.model.request.UserRoleRequest;
import com.cms.cn.model.response.BaseResponse;

public interface SysUserRoleService {

    BaseResponse selectUserRoles(UserRoleRequest roleUserRequest);
}
