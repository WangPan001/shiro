package com.cms.cn.dao;

import com.cms.cn.model.Request.UserRoleRequest;
import com.cms.cn.model.Response.UserRoleResponse;

import java.util.List;

public interface SysUserRoleMapper {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(SysUserRole record);
//
//    int insertSelective(SysUserRole record);

    List<UserRoleResponse> selectUserRoles(UserRoleRequest roleUserRequest);

//    int updateByPrimaryKeySelective(SysUserRole record);
//
//    int updateByPrimaryKey(SysUserRole record);
}