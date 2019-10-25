package com.cms.cn.controller;

import com.cms.cn.constant.ResultStatusCode;
import com.cms.cn.model.Request.MenuRequest;
import com.cms.cn.model.Request.MenusRequest;
import com.cms.cn.model.Response.BaseResponse;
import com.cms.cn.service.SysMenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName MenuController
 * @Description Todo
 * @Author wangpan
 * @Date 2019/6/10 13:39
 * @Version 1.0
 **/
@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @RequiresPermissions("sys:menu")
    @ResponseBody
    @RequestMapping("/findNavTree")
    public BaseResponse findNavTree(@RequestBody MenusRequest menusRequest){
        try {
            BaseResponse baseResponse = sysMenuService.getAllMenuByRoleId(menusRequest);
            return new BaseResponse(ResultStatusCode.OK, baseResponse);
        } catch (Exception e) {
            return new BaseResponse(ResultStatusCode.SYSTEM_ERR, e);
        }
    }

    @RequiresPermissions("sys:menu")
    @ResponseBody
    @RequestMapping("/findMenuTree")
    public BaseResponse findMenuTree(@RequestBody MenusRequest menusRequest){
        try {
            BaseResponse baseResponse = sysMenuService.getAllMenus(menusRequest);
            return new BaseResponse(ResultStatusCode.OK, baseResponse);
        } catch (Exception e) {
            return new BaseResponse(ResultStatusCode.SYSTEM_ERR, e);
        }
    }

    @RequiresPermissions("sys:menu:add")
    @ResponseBody
    @RequestMapping("/addMenu")
    public BaseResponse addMenu(@RequestBody MenuRequest menuRequest){
        try {
            BaseResponse baseResponse = sysMenuService.addMenu(menuRequest);
            return baseResponse;
        } catch (Exception e) {
            return new BaseResponse(ResultStatusCode.SYSTEM_ERR, e);
        }
    }
}
