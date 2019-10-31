package com.cms.cn.dao;

import com.cms.cn.model.request.UserRoleRequest;
import com.cms.cn.model.response.UserRoleResponse;

import java.util.List;

public interface SysUserRoleMapper {

    List<UserRoleResponse> selectUserRoles(UserRoleRequest roleUserRequest);

    int deleteUserRoles(UserRoleRequest roleRequest);

    int insertUserRoles(List<UserRoleRequest> userRoleRequests);
}