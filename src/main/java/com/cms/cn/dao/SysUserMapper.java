package com.cms.cn.dao;

import com.cms.cn.model.Request.UserRequest;
import com.cms.cn.model.entity.SysUser;

import java.util.List;

public interface SysUserMapper {

    SysUser selectUser(SysUser record);

    List<SysUser> findList(UserRequest userRequest);
}