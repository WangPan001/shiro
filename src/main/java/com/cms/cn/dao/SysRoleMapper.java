package com.cms.cn.dao;

import com.cms.cn.model.Request.RoleRequest;
import com.cms.cn.model.Response.PerResponse;
import com.cms.cn.model.Response.RoleResponse;
import com.cms.cn.model.entity.SysRole;

import java.util.List;

public interface SysRoleMapper {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(SysRole record);
//
//    int insertSelective(SysRole record);

    List<RoleResponse> selectRole(RoleRequest roleRequest);

    List<PerResponse> queryPerByRoleId(RoleRequest roleRequest);

//    int updateByPrimaryKeySelective(SysRole record);
//
//    int updateByPrimaryKey(SysRole record);
}