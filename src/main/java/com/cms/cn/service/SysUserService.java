package com.cms.cn.service;

import com.cms.cn.model.request.UserRequest;
import com.cms.cn.model.response.BaseResponse;

import java.util.List;


/**
 * @ClassName UserService
 * @Description Todo
 * @Author wangpan
 * @Date 2019/5/22 14:50
 * @Version 1.0
 **/
public interface SysUserService {

    BaseResponse getUserByName(UserRequest userRequest);

    BaseResponse findList(UserRequest userRequest);

    BaseResponse batchUpdateUserById(List<UserRequest> userRequests);
}
