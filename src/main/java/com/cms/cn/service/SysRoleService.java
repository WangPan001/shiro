package com.cms.cn.service;

import com.cms.cn.model.entity.SysRole;

import java.util.List;

/**
 * @ClassName RoleService
 * @Description Todo
 * @Author wangpan
 * @Date 2019/5/22 14:49
 * @Version 1.0
 **/

public interface SysRoleService {

    public List<SysRole> findByUserid(Integer userId);

}
