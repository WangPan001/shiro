package com.cms.cn.config;

import com.cms.cn.dao.SysMenuMapper;
import com.cms.cn.dao.SysRoleMapper;
import com.cms.cn.dao.SysUserMapper;
import com.cms.cn.dao.SysUserRoleMapper;
import com.cms.cn.model.request.RoleRequest;
import com.cms.cn.model.request.UserRoleRequest;
import com.cms.cn.model.response.PerResponse;
import com.cms.cn.model.response.UserResponse;
import com.cms.cn.model.response.UserRoleResponse;
import com.cms.cn.model.entity.SysUser;
import com.cms.cn.utils.EncryptUtils;
import com.cms.cn.utils.PasswordEncoderUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {
    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Resource
    private SysRoleMapper sysRoleMapper;
    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     *
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        log.info("---------------- 执行 Shiro 凭证认证 ----------------------");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String name = token.getUsername();
        String password = String.valueOf(token.getPassword());
        String salt = EncryptUtils.MD5(name, password);
        PasswordEncoderUtils passwordEncoderUtils = new PasswordEncoderUtils(salt);
        // 从数据库获取对应用户名密码的用户
        SysUser sysUser = new SysUser();
        sysUser.setName(name);
        sysUser.setPassword(passwordEncoderUtils.encode(password));
        UserResponse user = sysUserMapper.selectUser(sysUser);
        if (user != null) {
            // 用户为禁用状态
            if ("0".equals(user.getStatus())){
                throw new DisabledAccountException();
            }
            log.info("---------------- Shiro 凭证认证成功 ----------------------");
            //单用户登陆
            //处理session
            DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager)SecurityUtils.getSecurityManager();
            DefaultWebSessionManager sessionManager = (DefaultWebSessionManager)securityManager.getSessionManager();
            //获取当前已登录的用户session列表
            Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();
            UserResponse temp;
            for(Session session : sessions){
                //清除该用户以前登录时保存的session，强制退出
                Object attribute = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                if (attribute == null) {
                    continue;
                }

                temp = (UserResponse) ((SimplePrincipalCollection) attribute).getPrimaryPrincipal();
                if(name.equals(temp.getName())) {
                    sessionManager.getSessionDAO().delete(session);
                }
            }
            PasswordEncoderUtils encoderMd5 = new PasswordEncoderUtils(user.getSalt());
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    user, //用户
                    user.getPassword(), //密码
                    getName()  //realm name
            );
            return authenticationInfo;
        }
        throw new UnknownAccountException();
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("---------------- 执行 Shiro 权限获取 ---------------------");
        Object principal = principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (principal instanceof UserResponse) {
            UserResponse userLogin = (UserResponse) principal;
            if(userLogin != null){
                UserRoleRequest roleUserRequest = new UserRoleRequest();
                roleUserRequest.setUserId(userLogin.getId());
                List<UserRoleResponse> userRoleResponses = sysUserRoleMapper.selectUserRoles(roleUserRequest);
                if(CollectionUtils.isNotEmpty(userRoleResponses)){
                    for(UserRoleResponse userRoleResponse : userRoleResponses){
                        info.addRole(userRoleResponse.getRoleName());
                        RoleRequest roleRequest = new RoleRequest();
                        roleRequest.setRoleId(userRoleResponse.getRoleId());
                        List<PerResponse> perResponses = sysRoleMapper.queryPerByRoleId(roleRequest);
                        if(CollectionUtils.isNotEmpty(perResponses)){
                            for (PerResponse perResponse : perResponses){
                                if(perResponse != null && StringUtils.isNotBlank(perResponse.getPerms())){
                                    info.addStringPermission(perResponse.getPerms());
                                }
                            }
                        }
                    }
                }
            }
        }
        log.info("---------------- 获取到以下权限 ----------------");
        log.info(info.getStringPermissions().toString());
        log.info("---------------- Shiro 权限获取成功 ----------------------");
        return info;
    }
}