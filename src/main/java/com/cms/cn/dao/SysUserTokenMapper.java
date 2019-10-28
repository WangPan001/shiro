package com.cms.cn.dao;

import com.cms.cn.model.entity.SysUserToken;

public interface SysUserTokenMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUserToken record);

    int insertSelective(SysUserToken record);

    SysUserToken selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserToken record);

    int updateByPrimaryKey(SysUserToken record);
}