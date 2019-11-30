package com.cms.cn.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName RoleRequest
 * @Description Todo
 * @Author wangpan
 * @Date 2019/6/11 9:57
 * @Version 1.0
 **/
@Data
public class RoleRequest implements Serializable {

    private String roleId;

    private String parentId;

    private String type;

    private String name;

    private String remark;

    private String create_by;

    private String last_update_by;

    private String del_flag;

    private Integer pageSize = 0;

    private Integer pageNum = 0;
}
