package com.cms.cn.dao;

import com.cms.cn.model.Request.RoleRequest;
import com.cms.cn.model.entity.SysRole;

public interface SysRoleMapper {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(SysRole record);
//
//    int insertSelective(SysRole record);

    SysRole selectRole(RoleRequest roleRequest);

//    int updateByPrimaryKeySelective(SysRole record);
//
//    int updateByPrimaryKey(SysRole record);
}