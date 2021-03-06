package com.cms.cn.model.entity;

import com.cms.cn.model.response.UserRoleResponse;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SysUser implements Serializable {
    private String id;

    private String name;

    private String password;

    private String salt;

    private String email;

    private String mobile;

    private String status;

    private String dept_id;

    private String role_id;

    private String create_by;

    private String create_time;

    private String last_update_by;

    private String last_update_time;

    private String del_flag;

    private List<UserRoleResponse> userRoleResponses;

}