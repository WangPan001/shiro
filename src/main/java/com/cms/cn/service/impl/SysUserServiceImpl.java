package com.cms.cn.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cms.cn.constant.ResultStatusCode;
import com.cms.cn.dao.SysUserMapper;
import com.cms.cn.model.Request.UserRequest;
import com.cms.cn.model.Response.UserResponse;
import com.cms.cn.model.entity.SysUser;
import com.cms.cn.service.SysUserService;
import com.cms.cn.model.Response.BaseResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SysUserServiceImpl
 * @Description Todo
 * @Author wangpan
 * @Date 2019/6/3 11:53
 * @Version 1.0
 **/
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public BaseResponse getUserByName(UserRequest userRequest) {
        if (userRequest != null){
            UserResponse sysUser = sysUserMapper.selectUser(JSONObject.parseObject(JSONObject.toJSONString(userRequest), SysUser.class));
            if (sysUser != null){
                UserResponse userResponse = JSONObject.parseObject(JSONObject.toJSONString(sysUser), UserResponse.class);
                BaseResponse resultUtils = new BaseResponse(ResultStatusCode.OK.getCode(),
                        ResultStatusCode.OK.getMsg(), userResponse);
                return resultUtils;
            }
        }
        return null;
    }

    @Override
    public BaseResponse findList(UserRequest userRequest) {
        if (userRequest.getPageSize() == 0){
            userRequest.setPageSize(10);
        }
        if (userRequest.getPageNum() == 0){
            userRequest.setPageNum(10);
        }
        PageHelper.startPage(userRequest.getPageNum(), userRequest.getPageSize());
        List<SysUser> list = sysUserMapper.findList(userRequest);
        if (list != null && list.size() > 0){
            PageInfo<SysUser> page = new PageInfo<>(list);
            BaseResponse resultUtils = new BaseResponse(ResultStatusCode.OK.getCode(),
                    ResultStatusCode.OK.getMsg(), page);
            return resultUtils;
        }
        return null;
    }
}
