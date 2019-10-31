package com.cms.cn.service;

import com.cms.cn.model.request.DeptRequest;
import com.cms.cn.model.response.BaseResponse;

import java.util.List;

/**
 * @ClassName SysDeptService
 * @Description Todo
 * @Author wangpan
 * @Date 2019/10/28 17:00
 * @Version 1.0
 **/
public interface SysDeptService {

    BaseResponse queryDepts(DeptRequest deptRequest);

    BaseResponse addDept(DeptRequest deptRequest);

    BaseResponse updateDept(DeptRequest deptRequest);

    BaseResponse deleteDept(List<DeptRequest> deptRequests);
}
