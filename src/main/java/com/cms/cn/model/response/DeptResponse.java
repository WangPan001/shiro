package com.cms.cn.model.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName DeptResponse
 * @Description Todo
 * @Author wangpan
 * @Date 2019/10/28 16:35
 * @Version 1.0
 **/
@Data
public class DeptResponse implements Serializable {

    private String level;

    private String id;

    private String name;

    private String parentId;

    private String orderNum;

    private String createBy;

    private String createTime;

    private String lastUpdateBy;

    private String lastUpdateTime;

    private String delFlag;

    private String parentName;

    private List<DeptResponse> children;
}
