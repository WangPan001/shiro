package com.cms.cn.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cms.cn.constant.ResultStatusCode;
import com.cms.cn.dao.SysMenuMapper;
import com.cms.cn.dao.SysRoleMapper;
import com.cms.cn.model.request.MenusRequest;
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

    @Resource
    private SysMenuMapper sysMenuMapper;

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
        List<MenusResponse> menusResponses = sysRoleMapper.queryMenuByRoleId(roleRequest);
        if (menusResponses != null && menusResponses.size() > 0){
            for (MenusResponse menusResponse : menusResponses){
                RoleRequest request = new RoleRequest();
                request.setParentId(menusResponse.getMenuId());
                request.setType("1");
                request.setRoleId(roleRequest.getRoleId());
                List<MenusResponse> list = sysRoleMapper.queryMenuByRoleId(request);
                menusResponse.setChildren(list);
                for (MenusResponse response : list){
                    request.setParentId(response.getMenuId());
                    request.setType("2");
                    List<MenusResponse> responses = sysRoleMapper.queryMenuByRoleId(request);
                    response.setPermissions(responses);
                }
            }
            baseResponse = new BaseResponse(ResultStatusCode.OK.getCode(),
                    ResultStatusCode.OK.getMsg(), menusResponses);
        }else{
            return new BaseResponse(ResultStatusCode.UNAUTHO_ERROR);
        }
        logger.info("角色菜单列表param={}, result={]", JSONObject.toJSONString(roleRequest), JSONObject.toJSONString(menusResponses));
        return baseResponse;
    }
}
