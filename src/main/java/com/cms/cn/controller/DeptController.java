package com.cms.cn.controller;

import com.cms.cn.model.response.BaseResponse;
import com.cms.cn.service.SysDeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @RequestMapping(value = "/queryDepts", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse queryDepts(){
        return sysDeptService.queryDepts();
    }
}
