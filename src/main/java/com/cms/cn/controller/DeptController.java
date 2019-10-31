package com.cms.cn.controller;

import com.cms.cn.model.request.DeptRequest;
import com.cms.cn.model.response.BaseResponse;
import com.cms.cn.service.SysDeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName DeptController
 * @Description Todo
 * @Author wangpan
 * @Date 2019/10/28 17:34
 * @Version 1.0
 **/
@Controller
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private SysDeptService sysDeptService;

    @RequiresPermissions("sys:dept:view")
    @RequestMapping(value = "/queryDepts")
    @ResponseBody
    public BaseResponse queryDepts(@RequestBody DeptRequest deptRequest){
        return sysDeptService.queryDepts(deptRequest);
    }

    @RequiresPermissions("sys:dept:add")
    @RequestMapping(value = "/add")
    @ResponseBody
    public BaseResponse addDept(@RequestBody DeptRequest deptRequest){
        return sysDeptService.addDept(deptRequest);
    }

    @RequiresPermissions("sys:dept:edit")
    @RequestMapping(value = "/edit")
    @ResponseBody
    public BaseResponse updateDept(@RequestBody DeptRequest deptRequest){
        return sysDeptService.updateDept(deptRequest);
    }

    @RequiresPermissions("sys:dept:delete")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public BaseResponse deleteDept(@RequestBody DeptRequest deptRequest){
        return sysDeptService.updateDept(deptRequest);
    }
}
