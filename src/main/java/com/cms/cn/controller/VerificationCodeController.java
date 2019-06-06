package com.cms.cn.controller;

import com.cms.cn.constant.ResultStatusCode;
import com.cms.cn.utils.CodeUtils;
import com.cms.cn.model.Response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * @ClassName VerificationCodeController
 * @Description Todo
 * @Author wangpan
 * @Date 2019/6/4 14:23
 * @Version 1.0
 **/
@RestController
public class VerificationCodeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String KEY_CAPTCHA = "KEY_CAPTCHA";

    @RequestMapping("/getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response){
        //接收结果集
        Map<String,Object> map =null;
        //创建输出流
        ServletOutputStream out=null;
        //这里我没有用到验证码的值 如果要用的话，创建HttpSession进行存储 这个看个人的使用方式了
        try{
            // 禁止图像缓存。
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            map=CodeUtils.generateCodeAndPic();
            //保存再session中
            HttpSession session = request.getSession(true);
            session.removeAttribute("KEY_CAPTCHA");
            session.setAttribute("KEY_CAPTCHA", map.get("code").toString());

            System.out.println(session.getAttribute("KEY_CAPTCHA"));

            out = response.getOutputStream();
            //获取图片存储对象
            BufferedImage img = (BufferedImage) map.get("codePic");
            //把图片写入到输出流
            ImageIO.write(img, "jpg", out);
            //关闭流
            out.close();
        }catch (Exception e){

        }
    }

    /**
     * 验证码
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/checkImageCode")
    public BaseResponse checkImageCode(HttpServletRequest request,
                                       HttpServletResponse response, String captcha) {
        // 验证验证码
        String sessionCode = request.getSession().getAttribute("code").toString();
        System.out.println(sessionCode);
        if (captcha != null && !"".equals(captcha) && sessionCode != null && !"".equals(sessionCode)) {
            if (!captcha.equalsIgnoreCase(sessionCode)) {
                return new BaseResponse(ResultStatusCode.INVALID_CAPTCHA);
            }else{
                return new BaseResponse(ResultStatusCode.OK);
            }
        } else {
            return new BaseResponse(ResultStatusCode.INVALID_CAPTCHA);
        }
    }

    @RequestMapping(value="/getSessionId")
    @ResponseBody
    public String getSessionId(HttpServletRequest request){
        request.getSession().removeAttribute("springboot");
        System.out.println(request.getSession().getMaxInactiveInterval());
        Object o = request.getSession().getAttribute("springboot");
        if(o == null){
            o = "spring boot 牛逼了!!!有端口"+request.getLocalPort()+"生成";
            request.getSession().setAttribute("springboot", o);
        }

        return "端口=" + request.getLocalPort() +  " sessionId=" + request.getSession().getId() +"<br/>"+o;
    }
}
