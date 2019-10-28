package com.cms.cn.model.Response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName UserResponse
 * @Description Todo
 * @Author wangpan
 * @Date 2019/6/4 10:29
 * @Version 1.0
 **/
@Data
public class UserResponse implements Serializable {
    private String id;

    private String name;

    private String password;

    private String salt;

    private String email;

    private String mobile;

    private String status;

    private String dept_id;

    private String create_by;

    private String create_time;

    private String last_update_by;

    private String last_update_time;

    private String del_flag;

    private String dept_name;

    private String role_id;

    private String role_name;

    private String token;

    private List<UserRoleResponse> userRoleResponses;
}
