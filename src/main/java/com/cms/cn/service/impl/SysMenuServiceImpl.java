package com.cms.cn.service.impl;

import com.cms.cn.constant.ResultStatusCode;
import com.cms.cn.dao.SysMenuMapper;
import com.cms.cn.model.request.MenuRequest;
import com.cms.cn.model.request.MenusRequest;
import com.cms.cn.model.response.MenusResponse;
import com.cms.cn.service.SysMenuService;
import com.cms.cn.utils.ParamUtils;
import com.cms.cn.model.response.BaseResponse;
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

    @Override
    public BaseResponse getAllMenus(MenusRequest menusRequest) {
        menusRequest.setParentMenuId("0");
        List<MenusResponse> menusResponses = sysMenuMapper.getAllMenuByParentId(menusRequest);
        if (menusResponses != null && menusResponses.size() > 0){
            for (MenusResponse menusResponse : menusResponses){
                menusRequest.setParentMenuId(menusResponse.getMenuId());
                List<MenusResponse> menusResponsesTwo = sysMenuMapper.getAllMenuByParentId(menusRequest);
                if (menusResponsesTwo != null && menusResponsesTwo.size() > 0){
                    menusResponse.setChildren(menusResponsesTwo);
                    for (MenusResponse menusResponse1 : menusResponsesTwo){
                        menusResponse1.setParentName(menusResponse.getMenuName());
                        menusRequest.setParentMenuId(menusResponse1.getMenuId());
                        List<MenusResponse> menusResponsesThree = sysMenuMapper.getAllMenuByParentId(menusRequest);
                        if (menusResponsesThree != null && menusResponsesThree.size() > 0 ){
                            menusResponse1.setChildren(menusResponsesThree);
                            for (MenusResponse menusResponse2 : menusResponsesThree){
                                menusResponse2.setParentName(menusResponse1.getMenuName());
                            }
                        }
                    }
                }
            }
            BaseResponse resultUtils = new BaseResponse(ResultStatusCode.OK.getCode(),
                    ResultStatusCode.OK.getMsg(), menusResponses);
            return resultUtils;
        }else{
            return new BaseResponse(ResultStatusCode.UNAUTHO_ERROR);
        }
    }

    @Override
    public BaseResponse addMenu(MenuRequest menuRequest) {
        int num = sysMenuMapper.addMenu(menuRequest);
        if (num > 0){
            BaseResponse baseResponse = new BaseResponse(ResultStatusCode.OK.getCode(),
                    ResultStatusCode.OK.getMsg(), num);
            return baseResponse;
        }else{
            return new BaseResponse(ResultStatusCode.OPRATE_FAILD);
        }
    }
}
