package com.cms.cn.model.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName UserRoleResponse
 * @Description Todo
 * @Author wangpan
 * @Date 2019/6/11 10:57
 * @Version 1.0
 **/
@Data
public class UserRoleResponse implements Serializable {

    private String userId;

    private String roleId;

    private String roleName;

    private String remark;
}
