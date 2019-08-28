package com.cms.cn.config;

import com.cms.cn.utils.EncryptUtils;
import com.cms.cn.utils.PasswordEncoderUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class CredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;

        // 获得用户输入的密码:(可以采用加盐(salt)的方式去检验)
        String inPassword = new String(utoken.getPassword());
        //盐
        String salt = EncryptUtils.MD5(utoken.getUsername(), inPassword);
        PasswordEncoderUtils encoderMd5 = new PasswordEncoderUtils(salt);
        // 获得数据库中的密码
        String dbPassword = (String) info.getCredentials();
        // 进行密码的比对
        return this.equals(encoderMd5.encode(inPassword), dbPassword);
    }
}