package com.cms.cn.model.Request;

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
}
