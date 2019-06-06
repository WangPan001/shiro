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
            BaseResponse resultUtils = new BaseResponse(ResultStatusCode.OK.getCode(),
                    ResultStatusCode.OK.getMsg(), menusResponses);
            return resultUtils;
        }
        return null;
    }
}
