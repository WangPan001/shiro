package com.cms.cn.service;

import com.cms.cn.model.request.RoleRequest;
import com.cms.cn.model.response.BaseResponse;

/**
 * @ClassName RoleService
 * @Description Todo
 * @Author wangpan
 * @Date 2019/5/22 14:49
 * @Version 1.0
 **/

public interface SysRoleService {

    BaseResponse findRoles(RoleRequest roleRequest);

}
