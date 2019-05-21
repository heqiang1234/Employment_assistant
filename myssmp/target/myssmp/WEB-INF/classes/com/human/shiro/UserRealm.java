package com.human.shiro;


import com.human.controller.EmploymentController;
import com.human.model.Permission;
import com.human.model.User;
import com.human.service.ShiroService;
import com.human.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SX-503 on 2018/11/2.
 */
public class UserRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(EmploymentController.class);

    @Resource
    private UserService userService;

    public UserService getUserService() {

        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Resource
    private ShiroService shiroService;


    /**
     * shiro授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /**
         *
         * 流程
         * 1.根据用户user->2.获取角色id->3.根据角色id获取权限permission
         */
        //方法一：获得user对象
        String  user_name=principals.getPrimaryPrincipal().toString();
        User user=userService.getUserByName(user_name);
        System.out.println("111111111111");
        System.out.println(user.getUser_Name());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        System.out.println("222");
        //获取permission
        if(user!=null) {
            System.out.println("333");
            List<Permission> permissionsByUser = shiroService.getPermissionsByUser(user);
            System.out.println("1132");
            System.out.println(permissionsByUser.size());
            if (permissionsByUser.size()!=0) {
                for (Permission p: permissionsByUser) {

                    info.addStringPermission(p.getUrl());
                    System.out.println(p.getUrl());
                }
                return info;
            }
        }

        //方法二： 从subject管理器里获取user
//      Subject subject = SecurityUtils.getSubject();
////      User _user = (User) subject.getPrincipal();
////      System.out.println("subject"+_user.getUsername());




        return null;
    }

    /**
     * shiro认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        if(token==null || StringUtils.isBlank((String)token.getPrincipal()))
        {
            return null;
        }
        String username = token.getPrincipal().toString();
        System.err.println("验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
            log.info("验证shiro 认证");
        User user = userService.getUserByName(username);

        if (user != null) {
           // ByteSource credentialsSalt = ByteSource.Util.bytes(user.getMobilePhone());

         //   this.setSession(UserManager.CURRENT_USER, user);
            ByteSource salt = ByteSource.Util.bytes(user.getUser_Name());
            SimpleAuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUser_Name(),user.getUser_Password(),salt,this.getClass().getName());
            return authcInfo;
        }
        return null;
    }

    private void setSession(Object key, Object value) {
        Subject cureentUser = SecurityUtils.getSubject();
        if (cureentUser != null) {
            Session session = cureentUser.getSession();
            System.out.println("Session默认超时时间为[" + ((org.apache.shiro.session.Session) session).getTimeout() + "]毫秒");
            if (session != null) {
                ((org.apache.shiro.session.Session) session).setAttribute(key, value);
            }
        }
    }

    // 清除缓存
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
