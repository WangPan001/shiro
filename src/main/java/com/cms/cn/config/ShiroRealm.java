package com.cms.cn.config;

import com.cms.cn.dao.SysMenuMapper;
import com.cms.cn.dao.SysUserMapper;
import com.cms.cn.dao.SysUserRoleMapper;
import com.cms.cn.model.Request.MenusRequest;
import com.cms.cn.model.Request.UserRoleRequest;
import com.cms.cn.model.Response.MenusResponse;
import com.cms.cn.model.Response.UserRoleResponse;
import com.cms.cn.model.entity.SysMenu;
import com.cms.cn.model.entity.SysRole;
import com.cms.cn.model.entity.SysUser;
import com.cms.cn.model.entity.SysUserRole;
import com.cms.cn.service.SysMenuService;
import com.cms.cn.service.SysRoleService;
import com.cms.cn.service.SysUserService;
import com.cms.cn.utils.MD5Utils;
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
import org.springframework.beans.factory.annotation.Autowired;

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
        // 从数据库获取对应用户名密码的用户
        SysUser sysUser = new SysUser();
        sysUser.setLoginName(name);
        sysUser.setPassword(password);
        SysUser user = sysUserMapper.selectUser(sysUser);
        if (user != null) {
            // 用户为禁用状态
            if (user.getLoginFlag() != 1) {
                throw new DisabledAccountException();
            }
            log.info("---------------- Shiro 凭证认证成功 ----------------------");
            //单用户登陆
            //处理session
            DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager)SecurityUtils.getSecurityManager();
            DefaultWebSessionManager sessionManager = (DefaultWebSessionManager)securityManager.getSessionManager();
            //获取当前已登录的用户session列表
            Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();
            SysUser temp;
            for(Session session : sessions){
                //清除该用户以前登录时保存的session，强制退出
                Object attribute = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                if (attribute == null) {
                    continue;
                }

                temp = (SysUser) ((SimplePrincipalCollection) attribute).getPrimaryPrincipal();
                if(name.equals(temp.getLoginName())) {
                    sessionManager.getSessionDAO().delete(session);
                }
            }
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    user, //用户
                    MD5Utils.encrypt(user.getPassword()), //密码
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
        if (principal instanceof SysUser) {
            SysUser userLogin = (SysUser) principal;
            if(userLogin != null){
                UserRoleRequest roleUserRequest = new UserRoleRequest();
                roleUserRequest.setUserId(userLogin.getUserId());
                List<UserRoleResponse> userRoleResponses = sysUserRoleMapper.selectUserRoles(roleUserRequest);
                if(CollectionUtils.isNotEmpty(userRoleResponses)){
                    for(UserRoleResponse userRoleResponse : userRoleResponses){
                        info.addRole(userRoleResponse.getRoleName());
                        MenusRequest menusRequest = new MenusRequest();
                        menusRequest.setRoleId(userRoleResponse.getRoleId());
                        List<MenusResponse> menuList = sysMenuMapper.getAllMenuByRoleId(menusRequest);
                        if(CollectionUtils.isNotEmpty(menuList)){
                            for (MenusResponse menu : menuList){
                                if(StringUtils.isNoneBlank(menu.getPermission())){
                                    info.addStringPermission(menu.getPermission());
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