package com.cms.cn.model.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName RoleResponse
 * @Description Todo
 * @Author wangpan
 * @Date 2019/8/27 10:04
 * @Version 1.0
 **/
@Data
public class RoleResponse implements Serializable {

    private String id;

    private String name;

    private String remark;

    private String create_by;

    private String create_time;

    private String last_update_by;

    private String last_update_time;

    private String del_flag;
}
