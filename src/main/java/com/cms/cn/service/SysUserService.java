package com.cms.cn.service;

import com.cms.cn.model.Request.UserRequest;
import com.cms.cn.model.Response.BaseResponse;


/**
 * @ClassName UserService
 * @Description Todo
 * @Author wangpan
 * @Date 2019/5/22 14:50
 * @Version 1.0
 **/
public interface SysUserService {

    public BaseResponse getUserByName(UserRequest userRequest);

    public BaseResponse findList(UserRequest userRequest);
}
