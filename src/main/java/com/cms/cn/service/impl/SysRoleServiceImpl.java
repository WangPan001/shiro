package com.cms.cn.service.impl;

import com.cms.cn.dao.SysRoleMapper;
import com.cms.cn.model.Request.RoleRequest;
import com.cms.cn.model.Response.BaseResponse;
import com.cms.cn.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SysRoleService
 * @Description Todo
 * @Author wangpan
 * @Date 2019/6/3 11:53
 * @Version 1.0
 **/
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public BaseResponse findByUserid(RoleRequest roleRequest) {

        sysRoleMapper.selectRole(roleRequest);
        return null;
    }
}
