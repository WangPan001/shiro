package com.cms.cn.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName DeptRequest
 * @Description Todo
 * @Author wangpan
 * @Date 2019/10/28 16:35
 * @Version 1.0
 **/
@Data
public class DeptRequest implements Serializable {

    private String id;

    private String orderNum;

    private String parentId;

    private String name;

    private String createBy;

    private String lastUpdateBy;

}
