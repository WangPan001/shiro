package com.cms.cn.dao;

import com.cms.cn.model.request.RoleRequest;
import com.cms.cn.model.response.MenusResponse;
import com.cms.cn.model.response.PerResponse;
import com.cms.cn.model.response.RoleResponse;

import java.util.List;

public interface SysRoleMapper {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(SysRole record);
//
//    int insertSelective(SysRole record);

    List<RoleResponse> selectRole(RoleRequest roleRequest);

    List<PerResponse> queryPerByRoleId(RoleRequest roleRequest);

    List<MenusResponse> queryMenuByRoleId(RoleRequest roleRequest);

//    int updateByPrimaryKeySelective(SysRole record);
//
//    int updateByPrimaryKey(SysRole record);
}