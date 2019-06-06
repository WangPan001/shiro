package com.cms.cn.filter;

import com.alibaba.fastjson.JSONObject;
import com.cms.cn.constant.ResultStatusCode;
import com.cms.cn.model.Response.BaseResponse;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.springframework.http.HttpMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName CustomRolesAuthorizationFilter
 * @Description Todo
 * @Author wangpan
 * @Date 2019/6/6 10:12
 * @Version 1.0
 **/
public class CustomRolesAuthorizationFilter extends RolesAuthorizationFilter {

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        Subject subject = getSubject(request, response);
        String[] rolesArray = (String[]) mappedValue;
        //如果没有角色限制，直接放行
        if (rolesArray == null || rolesArray.length == 0) {
            return true;
        }
        for (int i = 0; i < rolesArray.length; i++) {
            //若当前用户是rolesArray中的任何一个，则有权限访问
            if (subject.hasRole(rolesArray[i])) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        //处理跨域问题，跨域的请求首先会发一个options类型的请求
        if (servletRequest.getMethod().equals(HttpMethod.OPTIONS.name())) {
            return false;
        }
        boolean isAccess = isAccessAllowed(request, response, mappedValue);
        if (isAccess) {
            return true;
        }
        servletResponse.setCharacterEncoding("UTF-8");
        Subject subject = getSubject(request, response);
        PrintWriter printWriter = servletResponse.getWriter();
        servletResponse.setContentType("application/json;charset=UTF-8");
        servletResponse.setHeader("Access-Control-Allow-Origin",
            servletRequest.getHeader("Origin") != null ? servletRequest.getHeader("Origin") : "http://172.24.3.88:8090");
        servletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
        servletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        servletResponse.setHeader("Vary", "Origin");
        String respStr;
        if (subject.getPrincipal() == null) {
            respStr = JSONObject.toJSONString(new BaseResponse(ResultStatusCode.UNLOGIN));
        } else {
            respStr = JSONObject.toJSONString(new BaseResponse(ResultStatusCode.UNAUTHO_ERROR));
        }
        printWriter.write(respStr);
        printWriter.flush();
        servletResponse.setHeader("content-Length", respStr.getBytes().length + "");
        return false;
    }
}
