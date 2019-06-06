package com.cms.cn.controller;

import com.cms.cn.constant.ResultStatusCode;
import com.cms.cn.model.Response.BaseResponse;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CommonController
 * @Description Todo
 * @Author wangpan
 * @Date 2019/6/4 15:49
 * @Version 1.0
 **/
@RequestMapping("/common")
@RestController
public class CommonController {

    /**
     * 未授权跳转方法
     * @return
     */
    @RequestMapping("/unauth")
    public BaseResponse unauth(){
        SecurityUtils.getSubject().logout();
        return new BaseResponse(ResultStatusCode.UNAUTHO_ERROR);
    }

    /**
     * 被踢出后跳转方法
     * @return
     */
    @RequestMapping("/kickout")
    public BaseResponse kickout(){
        return new BaseResponse(ResultStatusCode.INVALID_TOKEN);
    }
}
