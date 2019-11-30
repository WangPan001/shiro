package com.cms.cn.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cms.cn.constant.ResultStatusCode;
import com.cms.cn.dao.SysRoleMapper;
import com.cms.cn.model.request.RoleRequest;
import com.cms.cn.model.response.BaseResponse;
import com.cms.cn.model.response.MenusResponse;
import com.cms.cn.model.response.RoleResponse;
import com.cms.cn.service.SysRoleService;
import com.cms.cn.utils.PageUtils;
import com.github.pagehelper.PageHelper;
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
        PageHelper.startPage(roleRequest.getPageNum(), roleRequest.getPageSize());
        List<RoleResponse> roleResponses = sysRoleMapper.selectRole(roleRequest);
        logger.info("角色列表param={}, result={]", JSONObject.toJSONString(roleRequest), JSONObject.toJSONString(roleResponses));
        if (roleResponses != null && roleResponses.size() > 0){
            return new BaseResponse(ResultStatusCode.OK.getCode(),
                    ResultStatusCode.OK.getMsg(), new PageUtils<RoleResponse>(roleResponses));
        }else{
            return new BaseResponse(ResultStatusCode.UNAUTHO_ERROR);
        }
    }

    @Override
    public BaseResponse queryMenuByRoleId(RoleRequest roleRequest) {
        BaseResponse baseResponse = null;
        List<MenusResponse> roleResponses = sysRoleMapper.queryMenuByRoleId(roleRequest);
        if (roleResponses != null && roleResponses.size() > 0){
            baseResponse = new BaseResponse(ResultStatusCode.OK.getCode(),
                    ResultStatusCode.OK.getMsg(), roleResponses);
        }else{
            baseResponse = new BaseResponse(ResultStatusCode.UNAUTHO_ERROR);
        }
        logger.info("角色菜单列表param={}, result={]", JSONObject.toJSONString(roleRequest), JSONObject.toJSONString(roleResponses));
        return baseResponse;
    }

    @Override
    public BaseResponse addRole(RoleRequest roleRequest) {
        BaseResponse baseResponse = null;
        int num = sysRoleMapper.addRole(roleRequest);
        if (num > 0){
            baseResponse = new BaseResponse(ResultStatusCode.OK.getCode(),
                    ResultStatusCode.OK.getMsg(), num);
        }else{
            baseResponse = new BaseResponse(ResultStatusCode.OPRATE_FAILD);
        }
        logger.info("添加角色列表param={}, result={]", JSONObject.toJSONString(roleRequest), JSONObject.toJSONString(num));
        return baseResponse;
    }

    @Override
    public BaseResponse updateRole(RoleRequest roleRequest) {
        BaseResponse baseResponse = null;
        int num = sysRoleMapper.updateRole(roleRequest);
        if (num > 0){
            baseResponse = new BaseResponse(ResultStatusCode.OK.getCode(),
                    ResultStatusCode.OK.getMsg(), num);
        }else{
            baseResponse = new BaseResponse(ResultStatusCode.OPRATE_FAILD);
        }
        logger.info("修改角色菜单param={}, result={]", JSONObject.toJSONString(roleRequest), JSONObject.toJSONString(num));
        return baseResponse;
    }

    @Override
    public BaseResponse deleteRole(RoleRequest roleRequest) {
        BaseResponse baseResponse = null;
        roleRequest.setDel_flag("1");
        int num = sysRoleMapper.updateRole(roleRequest);
        if (num > 0){
            baseResponse = new BaseResponse(ResultStatusCode.OK.getCode(),
                    ResultStatusCode.OK.getMsg(), num);
        }else{
            baseResponse = new BaseResponse(ResultStatusCode.OPRATE_FAILD);
        }
        logger.info("删除角色菜单param={}, result={]", JSONObject.toJSONString(roleRequest), JSONObject.toJSONString(num));
        return baseResponse;
    }


}
