package com.cms.cn.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cms.cn.constant.ResultStatusCode;
import com.cms.cn.dao.SysUserMapper;
import com.cms.cn.dao.SysUserRoleMapper;
import com.cms.cn.model.request.LoginRequest;
import com.cms.cn.model.request.UserRequest;
import com.cms.cn.model.request.UserRoleRequest;
import com.cms.cn.model.response.UserResponse;
import com.cms.cn.model.response.UserRoleResponse;
import com.cms.cn.model.entity.SysUser;
import com.cms.cn.service.SysUserService;
import com.cms.cn.model.response.BaseResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

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
        List<UserResponse> list = sysUserMapper.findList(userRequest);

        if (list != null && list.size() > 0){
            for (UserResponse userResponse : list){
                UserRoleRequest roleUserRequest = new UserRoleRequest();
                roleUserRequest.setUserId(userResponse.getId());
                List<UserRoleResponse> userRoleResponses = sysUserRoleMapper.selectUserRoles(roleUserRequest);
                userResponse.setUserRoleResponses(userRoleResponses);
            }
            PageInfo<UserResponse> page = new PageInfo<>(list);
            BaseResponse resultUtils = new BaseResponse(ResultStatusCode.OK.getCode(),
                    ResultStatusCode.OK.getMsg(), page);
            return resultUtils;
        }
        return null;
    }

    @Override
    public BaseResponse batchUpdateUserById(List<UserRequest> userRequests) {
        int num = sysUserMapper.batchUpdateUserById(userRequests);
        BaseResponse resultUtils = null;
        if (num > 0){
            resultUtils = new BaseResponse(ResultStatusCode.OK.getCode(),
                    ResultStatusCode.OK.getMsg(), num);

        }else{
            resultUtils = new BaseResponse(ResultStatusCode.OPRATE_FAILD.getCode(),
                    ResultStatusCode.OPRATE_FAILD.getMsg(), num);
        }
        return resultUtils;
    }

    @Override
    public BaseResponse addUser(UserRequest userRequest) {
        int num = sysUserMapper.addUser(userRequest);
        if (userRequest.getUserRoleRequestList() != null && userRequest.getUserRoleRequestList().size() > 0){
            int count = sysUserRoleMapper.insertUserRoles(userRequest.getUserRoleRequestList());
        }
        BaseResponse resultUtils = null;
        if (num > 0){
            resultUtils = new BaseResponse(ResultStatusCode.OK.getCode(),
                    ResultStatusCode.OK.getMsg(), num);
        }else{
            resultUtils = new BaseResponse(ResultStatusCode.OPRATE_FAILD.getCode(),
                    ResultStatusCode.OPRATE_FAILD.getMsg(), num);
        }
        logger.info("添加用户,param={},result={}", JSONObject.toJSONString(userRequest), resultUtils);
        return resultUtils;
    }

    @Override
    public BaseResponse updateUserById(UserRequest userRequest) {
        int num = sysUserMapper.updateUserById(userRequest);
        if (userRequest.getUserRoleRequestList().size() > 0){
            UserRoleRequest roleRequest = new UserRoleRequest();
            roleRequest.setUserId(userRequest.getId());
            sysUserRoleMapper.deleteUserRoles(roleRequest);
            sysUserRoleMapper.insertUserRoles(userRequest.getUserRoleRequestList());
        }
        BaseResponse resultUtils = null;
        if (num > 0){
            resultUtils = new BaseResponse(ResultStatusCode.OK.getCode(),
                    ResultStatusCode.OK.getMsg(), num);
        }else{
            resultUtils = new BaseResponse(ResultStatusCode.OPRATE_FAILD.getCode(),
                    ResultStatusCode.OPRATE_FAILD.getMsg(), num);
        }
        logger.info("修改用户,param={},result={}", JSONObject.toJSONString(userRequest), resultUtils);
        return resultUtils;
    }
}
