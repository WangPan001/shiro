package com.cms.cn.model.Request;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName MenusRequest
 * @Description Todo
 * @Author wangpan
 * @Date 2019/6/3 14:01
 * @Version 1.0
 **/
@Data
public class MenusRequest implements Serializable {

    private String roleId;

    private String userName;

    private String parentMenuId;

}
