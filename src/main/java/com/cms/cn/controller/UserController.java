package com.cms.cn.controller;

import com.cms.cn.model.Request.UserRequest;
import com.cms.cn.service.SysUserService;
import com.cms.cn.model.Response.BaseResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequiresPermissions("sys:menu")
    @RequestMapping("/findList")
    public BaseResponse findList(@RequestBody UserRequest userRequest){
        return sysUserService.findList(userRequest);
    }
}
