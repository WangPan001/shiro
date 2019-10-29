package com.cms.cn.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName MenuRequest
 * @Description Todo
 * @Author wangpan
 * @Date 2019/9/4 14:19
 * @Version 1.0
 **/
@Data
public class MenuRequest implements Serializable {

    private String name;

    private String parent_id;

    private String usr;

    private String perms;

    private String is_show;

    private String sort;

    private String type;

    private String icon;

    private String order_num;

    private String create_by;

    private String create_time;

    private String last_update_by;

    private String last_update_time;

    private String remarks;

    private String del_flag;
}
