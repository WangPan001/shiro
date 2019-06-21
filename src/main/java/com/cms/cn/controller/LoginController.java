package com.cms.cn.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cms.cn.constant.ResultStatusCode;
import com.cms.cn.model.Request.UserRequest;
import com.cms.cn.model.Response.BaseResponse;
import com.cms.cn.model.Response.UserResponse;
import com.cms.cn.model.entity.SysUser;
import com.cms.cn.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName LoginController
 * @Description Todo
 * @Author wangpan
 * @Date 2019/6/4 15:50
 * @Version 1.0
 **/
@Controller
@RequestMapping("/admin")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping("/login")
    @ResponseBody
    public BaseResponse login(HttpServletRequest request, @RequestBody UserRequest userRequest){
        try {
            // 验证验证码
            Session session = SecurityUtils.getSubject().getSession();
            String sessionCode = String.valueOf(session.getAttribute(VerificationCodeController.KEY_CAPTCHA));
            if (StringUtils.isNotBlank(userRequest.getCaptcha()) && StringUtils.isNotBlank(sessionCode)) {
                if (!userRequest.getCaptcha().equalsIgnoreCase(sessionCode)) {
                    return new BaseResponse(ResultStatusCode.INVALID_CAPTCHA);
                }
            } else {
                return new BaseResponse(ResultStatusCode.INVALID_CAPTCHA);
            }
            UsernamePasswordToken token = new UsernamePasswordToken(userRequest.getLoginName(), MD5Utils.encrypt(userRequest.getPassword()));
            //登录不在该处处理，交由shiro处理
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);

            SysUser user = (SysUser) subject.getPrincipal();

            if (subject.isAuthenticated()) {
                UserResponse userResponse = new UserResponse();
                userResponse.setToken(String.valueOf(subject.getSession().getId()));
                userResponse.setRoleId(user.getRoleId());
                return new BaseResponse(ResultStatusCode.OK, userResponse);
            }else{
                return new BaseResponse(ResultStatusCode.SHIRO_ERROR);
            }
        }catch (IncorrectCredentialsException | UnknownAccountException e){
            logger.error("错误e={}", e);
            return new BaseResponse(ResultStatusCode.NOT_EXIST_USER_OR_ERROR_PWD);
        }catch (LockedAccountException e){
            return new BaseResponse(ResultStatusCode.USER_FROZEN);
        }catch (Exception e){
            logger.error("错误e={}", e);
            return new BaseResponse(ResultStatusCode.SYSTEM_ERR);
        }
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping("/logout")
    public BaseResponse logout(){
        SecurityUtils.getSubject().logout();
        return new BaseResponse(ResultStatusCode.OK);
    }
}
