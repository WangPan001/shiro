package com.cms.cn.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName RoleRequest
 * @Description Todo
 * @Author wangpan
 * @Date 2019/6/11 9:57
 * @Version 1.0
 **/
@Data
public class RoleRequest implements Serializable {

    private String roleId;

    private Integer pageSize = 0;

    private Integer pageNum = 0;
}
