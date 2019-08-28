package com.cms.cn.service.impl;

import com.cms.cn.constant.ResultStatusCode;
import com.cms.cn.dao.SysMenuMapper;
import com.cms.cn.model.Request.MenusRequest;
import com.cms.cn.model.Response.MenusResponse;
import com.cms.cn.service.SysMenuService;
import com.cms.cn.utils.ParamUtils;
import com.cms.cn.model.Response.BaseResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SysMenuServiceImpl
 * @Description Todo
 * @Author wangpan
 * @Date 2019/6/3 11:53
 * @Version 1.0
 **/
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public BaseResponse getAllMenuByRoleId(MenusRequest menusRequest) throws Exception {
        ParamUtils.checkParam(menusRequest);
        List<MenusResponse> menusResponses = sysMenuMapper.getAllMenuByRoleId(menusRequest);
        if (menusResponses != null && menusResponses.size() > 0){
            for (MenusResponse menusResponse : menusResponses){
                MenusRequest request = new MenusRequest();
                request.setParentMenuId(menusResponse.getMenuId());
                request.setType("1");
                List<MenusResponse> list = sysMenuMapper.getAllMenuByParentId(request);
                menusResponse.setChildren(list);
                for (MenusResponse response : list){
                    request.setParentMenuId(response.getMenuId());
                    request.setType("2");
                    List<MenusResponse> responses = sysMenuMapper.getAllMenuByParentId(request);
                    response.setPermissions(responses);
                }
            }
            BaseResponse resultUtils = new BaseResponse(ResultStatusCode.OK.getCode(),
                    ResultStatusCode.OK.getMsg(), menusResponses);
            return resultUtils;
        }else{
            return new BaseResponse(ResultStatusCode.UNAUTHO_ERROR);
        }
    }
}
