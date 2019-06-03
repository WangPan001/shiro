package com.cms.cn.service;

import com.cms.cn.model.entity.SysMenu;

import java.util.List;

/**
 * @ClassName MenuService
 * @Description Todo
 * @Author wangpan
 * @Date 2019/5/22 14:46
 * @Version 1.0
 **/
public interface SysMenuService {

    public List<SysMenu> getAllMenuByRoleId(Integer roleId);

}
