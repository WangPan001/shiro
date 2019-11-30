package com.cms.cn.controller;

import com.cms.cn.model.request.RoleRequest;
import com.cms.cn.model.response.BaseResponse;
import com.cms.cn.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName RoleController
 * @Description Todo
 * @Author wangpan
 * @Date 2019/10/28 11:45
 * @Version 1.0
 **/
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @RequiresPermissions("sys:role:view")
    @RequestMapping("/queryRoles")
    @ResponseBody
    public BaseResponse queryRoles(@RequestBody RoleRequest roleRequest) {
        return sysRoleService.findRoles(roleRequest);
    }

    @RequiresPermissions("sys:role:view")
    @RequestMapping("/queryMenuByRoleId")
    @ResponseBody
    public BaseResponse queryMenuByRoleId(@RequestBody RoleRequest roleRequest) {
        return sysRoleService.queryMenuByRoleId(roleRequest);
    }

    @RequiresPermissions("sys:role:add")
    @RequestMapping("/add")
    @ResponseBody
    public BaseResponse addRole(@RequestBody RoleRequest roleRequest) {
        return sysRoleService.addRole(roleRequest);
    }

    @RequiresPermissions("sys:role:edit")
    @RequestMapping("/edit")
    @ResponseBody
    public BaseResponse updateRole(@RequestBody RoleRequest roleRequest) {
        return sysRoleService.updateRole(roleRequest);
    }

    @RequiresPermissions("sys:role:delete")
    @RequestMapping("/delete")
    @ResponseBody
    public BaseResponse deleteRole(@RequestBody RoleRequest roleRequest) {
        return sysRoleService.deleteRole(roleRequest);
    }
}
