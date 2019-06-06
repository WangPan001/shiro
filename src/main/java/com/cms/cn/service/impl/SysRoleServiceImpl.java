package com.cms.cn.service.impl;

import com.cms.cn.model.entity.SysRole;
import com.cms.cn.service.SysRoleService;
import org.springframework.stereotype.Service;

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
    @Override
    public List<SysRole> findByUserid(Integer userId) {
        return null;
    }
}
