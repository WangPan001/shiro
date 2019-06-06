package com.cms.cn.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cms.cn.constant.ResultStatusCode;
import com.cms.cn.model.Response.BaseResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName LoginController
 * @Description Todo
 * @Author wangpan
 * @Date 2019/6/4 15:50
 * @Version 1.0
 **/
@RestController
@RequestMapping("/admin")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping("/login")
    public BaseResponse login(HttpServletRequest request, String account, String password, String captcha){
        try {
            String code = captcha;
            // 验证验证码
            Session session = SecurityUtils.getSubject().getSession();
            String sessionCode = String.valueOf(session.getAttribute(VerificationCodeController.KEY_CAPTCHA));
            System.out.println(sessionCode);

            if (code != null && !"".equals(code) && sessionCode != null && !"".equals(sessionCode)) {
                if (!code.equalsIgnoreCase(sessionCode)) {
                    return new BaseResponse(ResultStatusCode.INVALID_CAPTCHA);
                }
            } else {
                return new BaseResponse(ResultStatusCode.INVALID_CAPTCHA);
            }
            UsernamePasswordToken token = new UsernamePasswordToken(account, password);
            //登录不在该处处理，交由shiro处理
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);

            if (subject.isAuthenticated()) {
                JSON json = new JSONObject();
                ((JSONObject) json).put("token", subject.getSession().getId());

                return new BaseResponse(ResultStatusCode.OK, json);
            }else{
                return new BaseResponse(ResultStatusCode.SHIRO_ERROR);
            }
        }catch (IncorrectCredentialsException | UnknownAccountException e){
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
