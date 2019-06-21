package com.cms.cn.model.Response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName UserResponse
 * @Description Todo
 * @Author wangpan
 * @Date 2019/6/4 10:29
 * @Version 1.0
 **/
@Data
public class UserResponse implements Serializable {
    private Integer id;

    private String userId;

    private String loginName;

    private String password;

    private String roleId;

    private String certificatesNo;

    private String name;

    private String email;

    private String phone;

    private String photo;

    private String loginIp;

    private Date loginDate;

    private Integer loginChannel;

    private Integer loginFlag;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private String remarks;

    private Integer delFlag;

    private String token;
}
