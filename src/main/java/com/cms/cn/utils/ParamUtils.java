package com.cms.cn.utils;

import com.alibaba.fastjson.JSONObject;
import com.cms.cn.model.Request.MenusRequest;
import com.cms.cn.model.Response.MenusResponse;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName checkParamUtils
 * @Description Todo
 * @Author wangpan
 * @Date 2019/6/3 14:39
 * @Version 1.0
 **/
public class ParamUtils<T>  {

    public static<T> String checkParam(T t) throws Exception {
        if (t == null){
            throw new IllegalArgumentException("This object cann't be empty!");
        }
        Class<? extends Object> clazz = t.getClass();
        // 获取实体类的所有属性，返回Field数组
        Field[] fields = clazz.getDeclaredFields();
        String result = "";
        for (Field field : fields){
            Object object = getGetMethod(t, field.getName());
            if (object == null){
                result = result + field.getName() + " cann't be null.\n";
            }
        }
        return result;
    }

    // 把一个字符串的第一个字母大写、效率是最高的、
    public static String getMethodName(String fildeName) throws Exception{
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

    /**
     * 根据属性，获取get方法
     * @param ob 对象
     * @param name 属性名
     * @return
     * @throws Exception
     */
    public static Object getGetMethod(Object ob , String name)throws Exception{
        Method[] m = ob.getClass().getMethods();
        for(int i = 0;i < m.length;i++){
            if(("get"+name).toLowerCase().equals(m[i].getName().toLowerCase())){
                return m[i].invoke(ob);
            }
        }
        return null;
    }

//    public static void main(String[] args) throws Exception {
//        MenusResponse menusResponse = new MenusResponse();
//        menusResponse.setIsShow("1");
//        System.out.println(checkParam(menusResponse));
//    }
}
