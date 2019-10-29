package com.cms.cn.dao;

import com.cms.cn.model.request.UserRequest;
import com.cms.cn.model.response.UserResponse;
import com.cms.cn.model.entity.SysUser;

import java.util.List;

public interface SysUserMapper {

    UserResponse selectUser(SysUser record);

    List<UserResponse> findList(UserRequest userRequest);

    int batchUpdateUserById(List<UserRequest> userRequests);
}