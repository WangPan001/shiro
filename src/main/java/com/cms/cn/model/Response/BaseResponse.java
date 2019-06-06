package com.cms.cn.model.Response;

import com.cms.cn.constant.ResultStatusCode;

/**
 * @ClassName Result
 * @Description Todo
 * @Author wangpan
 * @Date 2019/5/21 17:31
 * @Version 1.0
 **/
public class BaseResponse {

    private int code;		//返回的代码，0表示成功，其他表示失败
    private String msg;		//成功或失败时返回的错误信息
    private Object data;	//成功时返回的数据信息
    public BaseResponse(int code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResponse(ResultStatusCode resultStatusCode, Object data){
        this(resultStatusCode.getCode(), resultStatusCode.getMsg(), data);
    }

    public BaseResponse(int code, String msg){
        this(code, msg, null);
    }

    public BaseResponse(ResultStatusCode resultStatusCode){
        this(resultStatusCode, null);
    }


    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

}
