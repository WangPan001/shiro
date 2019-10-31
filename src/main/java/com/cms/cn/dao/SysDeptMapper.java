package com.cms.cn.dao;

import com.cms.cn.model.request.DeptRequest;
import com.cms.cn.model.response.DeptResponse;

import java.util.List;

public interface SysDeptMapper {

    List<DeptResponse> queryDepts(DeptRequest deptRequest);

    List<DeptResponse> queryDeptsById(DeptRequest deptRequest);

    int addDept(DeptRequest deptRequest);

    int updateDepts(DeptRequest deptRequest);

    int deleteDept(List<DeptRequest> deptRequests);
}