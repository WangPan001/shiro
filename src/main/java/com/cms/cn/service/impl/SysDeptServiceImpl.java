package com.cms.cn.service.impl;

import com.cms.cn.constant.ResultStatusCode;
import com.cms.cn.dao.SysDeptMapper;
import com.cms.cn.model.request.DeptRequest;
import com.cms.cn.model.response.BaseResponse;
import com.cms.cn.model.response.DeptResponse;
import com.cms.cn.service.SysDeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SysDeptServiceImpl
 * @Description Todo
 * @Author wangpan
 * @Date 2019/10/28 17:01
 * @Version 1.0
 **/
@Service
public class SysDeptServiceImpl implements SysDeptService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SysDeptMapper sysDeptMapper;

    @Override
    public BaseResponse queryDepts(DeptRequest deptRequest) {
        List<DeptResponse> deptResponses = sysDeptMapper.queryDepts(deptRequest);
        if (deptResponses != null && deptResponses.size() > 0){
            for (DeptResponse deptResponse : deptResponses){
                DeptRequest deptRequest1 = new DeptRequest();
                deptRequest1.setParentId(deptResponse.getId());
                List<DeptResponse> responses = sysDeptMapper.queryDeptsById(deptRequest1);
                if(responses != null && responses.size() > 0){
                    List<DeptResponse> deptResponses2 = new ArrayList<>();
                    List<DeptResponse> deptResponses3 = new ArrayList<>();
                    for (DeptResponse dept : responses){
                        if ("2".equals(dept.getLevel())){
                            dept.setParentName(deptResponse.getName());
                            deptResponses2.add(dept);
                        }else if ("3".equals(dept.getLevel())){
                            deptResponses3.add(dept);
                        }
                    }
                    if (deptResponses2 != null && deptResponses2.size() > 0){
                        deptResponse.setChildren(deptResponses2);
                    }
                    for (DeptResponse deptResponse2 : deptResponses2){
                        List<DeptResponse> deptResponses4 = new ArrayList<>();
                        for (DeptResponse deptResponse3 : deptResponses3){
                            if (deptResponse2.getId().equals(deptResponse3.getParentId())){
                                deptResponse3.setParentName(deptResponse2.getName());
                                deptResponses4.add(deptResponse3);
                            }
                        }
                        deptResponse2.setChildren(deptResponses4);
                    }
                }
            }
        }
        return new BaseResponse(ResultStatusCode.OK.getCode(),
                ResultStatusCode.OK.getMsg(), deptResponses);
    }

    @Override
    public BaseResponse addDept(DeptRequest deptRequest) {
        BaseResponse response = null;
        int num = sysDeptMapper.addDept(deptRequest);
        if (num > 0){
            response = new BaseResponse(ResultStatusCode.OK.getCode(),
                    ResultStatusCode.OK.getMsg(), num);
        }else{
            response = new BaseResponse(ResultStatusCode.OPRATE_FAILD.getCode(),
                    ResultStatusCode.OPRATE_FAILD.getMsg());
        }
        return response;
    }

    @Override
    public BaseResponse updateDept(DeptRequest deptRequest) {
        BaseResponse response = null;
        int num = sysDeptMapper.updateDepts(deptRequest);
        if (num > 0){
            response = new BaseResponse(ResultStatusCode.OK.getCode(),
                    ResultStatusCode.OK.getMsg(), num);
        }else{
            response = new BaseResponse(ResultStatusCode.OPRATE_FAILD.getCode(),
                    ResultStatusCode.OPRATE_FAILD.getMsg());
        }
        return response;
    }
}
