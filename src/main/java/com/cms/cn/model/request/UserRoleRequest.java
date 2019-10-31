package com.cms.cn.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName RoleUserRequest
 * @Description Todo
 * @Author wangpan
 * @Date 2019/6/11 10:25
 * @Version 1.0
 **/
@Data
public class UserRoleRequest implements Serializable {

    private String userId;

    private String roleId;

    private String create_by;

    private String create_time;

    private String last_update_by;

    private String last_update_time;
}
