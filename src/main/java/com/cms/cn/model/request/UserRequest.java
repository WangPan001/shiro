package com.cms.cn.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName UserRequest
 * @Description Todo
 * @Author wangpan
 * @Date 2019/6/4 10:10
 * @Version 1.0
 **/
@Data
public class UserRequest implements Serializable {

    private String loginName;

    private String phone;

    private String password;

    private String captcha;

    private String id;

    private Integer pageSize = 0;

    private Integer pageNum = 0;
}
