package com.cms.cn.service;

import com.cms.cn.model.entity.SysUser;


/**
 * @ClassName UserService
 * @Description Todo
 * @Author wangpan
 * @Date 2019/5/22 14:50
 * @Version 1.0
 **/
public interface SysUserService {

    public SysUser getUserByName(String name);


    public SysUser getByOpenid(String openid);

    public SysUser getByPhone(String phone);

}
