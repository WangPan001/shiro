package com.cms.cn.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName UserRequest
 * @Description Todo
 * @Author wangpan
 * @Date 2019/10/29 16:35
 * @Version 1.0
 **/
@Data
public class UserRequest implements Serializable {

    private String id;

    private String mobile;

    private String name;

    private String salt;

    private String password;

    private String email;

    private String dept_id;

    private String create_by;

    private String last_update_by;

    private Integer pageSize = 0;

    private Integer pageNum = 0;
}
