package com.cms.cn.service.impl;

import com.cms.cn.constant.ResultStatusCode;
import com.cms.cn.dao.SysUserRoleMapper;
import com.cms.cn.model.request.UserRoleRequest;
import com.cms.cn.model.response.BaseResponse;
import com.cms.cn.model.response.UserRoleResponse;
import com.cms.cn.service.SysUserRoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SysUserRoleServiceImpl
 * @Description Todo
 * @Author wangpan
 * @Date 2019/6/11 10:34
 * @Version 1.0
 **/
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public BaseResponse selectUserRoles(UserRoleRequest roleUserRequest) {
        List<UserRoleResponse> userRoleResponses = sysUserRoleMapper.selectUserRoles(roleUserRequest);
        if (CollectionUtils.isNotEmpty(userRoleResponses)){
            return new BaseResponse(ResultStatusCode.OK, userRoleResponses);
        }
        return null;
    }
}
