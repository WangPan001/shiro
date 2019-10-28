package com.cms.cn.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cms.cn.constant.ResultStatusCode;
import com.cms.cn.dao.SysRoleMapper;
import com.cms.cn.model.Request.RoleRequest;
import com.cms.cn.model.Response.BaseResponse;
import com.cms.cn.model.Response.RoleResponse;
import com.cms.cn.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public BaseResponse findRoles(RoleRequest roleRequest) {
        if (roleRequest.getPageSize() == 0){
            roleRequest.setPageSize(10);
        }
        if (roleRequest.getPageNum() == 0){
            roleRequest.setPageNum(10);
        }
        List<RoleResponse> roleResponses = sysRoleMapper.selectRole(roleRequest);
        logger.info("角色列表param={}, result={]", JSONObject.toJSONString(roleRequest), JSONObject.toJSONString(roleResponses));
        if (roleResponses != null && roleResponses.size() > 0){
            return new BaseResponse(ResultStatusCode.OK.getCode(),
                    ResultStatusCode.OK.getMsg(), roleResponses);
        }else{
            return new BaseResponse(ResultStatusCode.UNAUTHO_ERROR);
        }
    }
}
