package com.cms.cn.dao;

import com.cms.cn.model.Request.UserRequest;
import com.cms.cn.model.Response.UserResponse;
import com.cms.cn.model.entity.SysUser;

import java.util.List;

public interface SysUserMapper {

    UserResponse selectUser(SysUser record);

    List<UserResponse> findList(UserRequest userRequest);
}