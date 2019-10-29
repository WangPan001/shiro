package com.cms.cn.controller;

import com.cms.cn.model.request.UserRequest;
import com.cms.cn.service.SysUserService;
import com.cms.cn.model.response.BaseResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName UserController
 * @Description Todo
 * @Author wangpan
 * @Date 2019/6/4 15:53
 * @Version 1.0
 **/
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    @RequiresPermissions("sys:user:view")
    @RequestMapping("/findList")
    public BaseResponse findList(@RequestBody UserRequest userRequest){
        return sysUserService.findList(userRequest);
    }

    @RequiresPermissions("sys:user:delete")
    @RequestMapping("/delete")
    public BaseResponse batchUpdateUserById(@RequestBody List<UserRequest> userRequest){
        return sysUserService.batchUpdateUserById(userRequest);
    }

    @RequiresPermissions("sys:user:add")
    @RequestMapping("/add")
    public BaseResponse addUser(@RequestBody UserRequest userRequest){
        return sysUserService.addUser(userRequest);
    }

    @RequiresPermissions("sys:user:edit")
    @RequestMapping("/edit")
    public BaseResponse updateUserById(@RequestBody UserRequest userRequest){
        return sysUserService.updateUserById(userRequest);
    }
}
