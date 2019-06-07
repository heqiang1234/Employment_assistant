package com.human.controller;

import com.google.code.kaptcha.Constants;
import com.human.model.User;
import com.human.service.ShiroService;
import com.human.service.UserService;
import com.human.service.impl.SessionServiceImpl;
import com.human.shiro.SessionDao;
import com.human.util.JsonMsg;
import com.human.util.Md5Utils;
import org.apache.shiro.SecurityUtils;

import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * create on hq 2019/5/7;
 */

@Controller
@RequestMapping(value="/User")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Resource
    @Autowired
    private UserService userService;
    @Autowired
    private SessionDao sessionDao;



    @RequestMapping(value = "/ShowUser")
    @ResponseBody
    public  List<User> showUser(HttpServletRequest request, Model model){
       log.info("查询所有用户信息");
        List<User> userList = userService.getAllUser();
        return userList;
    }

    @RequestMapping(value = "/NoPermission")
    @ResponseBody
    public  JsonMsg permission(HttpServletRequest request, Model model){

        return JsonMsg.Lose().addInfo("login_error", "session失效，请重新输入！");
    }

    @RequestMapping(value = "/Login")
    @ResponseBody
    public  JsonMsg login(HttpServletRequest request, Model model){

        return JsonMsg.fail().addInfo("login_error", "没有登陆，请登陆！");
    }


    /**
     * 对登录页面输入的用户名和密码进行验证
     * @param
     * @return
     */
    @RequestMapping(value="/DoLogin")
    @ResponseBody
    public JsonMsg  dologin(HttpServletRequest request) {
           try {
               log.info("登陆验证");
               //主体,当前状态为没有认证的状态“未认证”
               Subject currentUser = SecurityUtils.getSubject();
               // 登录后存放进shiro token
//               Session session = currentUser.getSession(false);
//               SessionKey sessionKey = new DefaultSessionKey(session.getId());
               if (!currentUser.isAuthenticated()) {
                   String verifyCode=request.getParameter("verifyCode").toUpperCase();
                   String user_name = request.getParameter("UserName");
                   String passWord = request.getParameter("Password");
                   if(verifyCode.equals(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY))) {
                       if (user_name == null || user_name.trim().length() == 0 || passWord == null || passWord.trim().length() == 0) {
                           return JsonMsg.fail().addInfo("login_error", "登陆失败，请重新输入！");
                       }
                       UsernamePasswordToken upToken = new UsernamePasswordToken(user_name, passWord);
                       //保存session
                       upToken.setRememberMe(true);
                       try {
                           System.out.println(upToken);
                           currentUser.login(upToken);
                           User user;
                           user = userService.getUserByNameNoPassword(user_name);
                           return JsonMsg.success().addInfo("person", user);

                       } catch (IncorrectCredentialsException ice) {
                           System.out.println("邮箱/密码不匹配！");
                           return JsonMsg.fail().addInfo("login_error", "邮箱/密码不匹配！登陆失败，请重新输入！");
                       } catch (LockedAccountException lae) {
                           System.out.println("账户已被冻结！");
                           return JsonMsg.fail().addInfo("login_error", "账户已被冻结登陆失败，请重新输入！");
                       } catch (AuthenticationException ae) {
                           System.out.println(ae.getMessage());
                           return JsonMsg.fail().addInfo("login_error", "查找不到用户，登陆失败，请重新输入！");

                       }
                   }
                   else
                   {
                       return JsonMsg.fail().addInfo("login_error", "验证码输入错误，请重新输入！");
                   }
               }
               User user;
               user=userService.getUserByNameNoPassword(currentUser.getPrincipal().toString());
               return  JsonMsg.success().addInfo("person", user);
           }
        catch (AuthenticationException qqe){
                   return JsonMsg.fail().addInfo("login_error", "登陆失败，请重新输入！");
               }

    }

    /**
     * 注册添加用户
     * @param request
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/Save")
    @ResponseBody
    public JsonMsg save(HttpServletRequest request) {
        try {
            log.info("注册用户");
            String user_name = request.getParameter("UserName");
            String passWord = request.getParameter("Password");
            if (user_name == null || user_name.trim().length() == 0 || passWord == null || passWord.trim().length() == 0) {
                return JsonMsg.fail().addInfo("login_error", "注册失败，请重新注册！");
            }
            System.out.println(user_name + " " + passWord);
            String newPs = Md5Utils.encrypt(user_name, passWord);
            User user = new User();
            user.setUser_Name(user_name);
            user.setUser_Password(newPs);
            log.info(newPs);
            System.out.println(user.getUser_Name() + " " + user.getUser_Password());
            User user_temp = userService.getUserByName(user_name);
            if (user_temp != null) {
                return JsonMsg.fail().addInfo("login_error", "用户已存在，请重新注册！");
            }
            userService.save(user);
            return JsonMsg.success().addInfo("person", user);
        }
        catch (Exception e)
        {
            return JsonMsg.fail().addInfo("login_error", "注册失败，请重新注册！");
        }
    }

    /**
     * 更改用户密码
     * @param request
     * @return
     */
    @RequestMapping(value = "/UpdateUserPassword")
    @ResponseBody
    public JsonMsg UpdateUserPassword(HttpServletRequest request) {
        try {
            log.info("修改用户密码");
            // Subject currentUser = SecurityUtils.getSubject();
            // 登录后存放进shiro token
            String user_name = request.getParameter("UserName");
            String passWord = request.getParameter("Password");
            if (user_name == null || user_name.trim().length() == 0 || passWord == null || passWord.trim().length() == 0) {
                return JsonMsg.fail().addInfo("login_error", "修改失败，请重新输入！");
            }
            String newPs = Md5Utils.encrypt(user_name, passWord);
            User user = new User();
            user.setUser_Name(user_name);
            user.setUser_Password(newPs);
            userService.updateUserPassword(user);
            return JsonMsg.success().addInfo("respn", "修改成功");
        }catch (Exception e)
        {
            return JsonMsg.fail().addInfo("log_error","修改失败");
        }
    }

    /**
     * 更改用户信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/UpdateUser")
    @ResponseBody
    public JsonMsg UpdateUser(HttpServletRequest request) {
        try {
            log.info("修改用户信息");
            // Subject currentUser = SecurityUtils.getSubject();
            // 登录后存放进shiro token
            Subject currentUser = SecurityUtils.getSubject();
            String  username=currentUser.getPrincipal().toString();
            log.info(username+"                                ");
            String UserName = username;
            String UserSex = request.getParameter("UserSex");
            String UserRealName = request.getParameter("UserRealName");
            String UserSchool = request.getParameter("UserSchool");
            String UserMajor = request.getParameter("UserMajor");
            String UserIntentionalPost = request.getParameter("UserIntentionalPost");
            String UserCity = request.getParameter("userCity");
            String UserMail = request.getParameter("UserMail");
            String UserImg = request.getParameter("UserImg");
            String UserStatus = request.getParameter("UserStatus");
            if (UserName == null || UserName.trim().length() == 0 ) {

                return JsonMsg.fail().addInfo("login_error", "修改失败，请重新输入！");

            }
            User user = new User();
            user.setUser_Name(UserName);
            user.setUser_Sex(UserSex);
            user.setUser_RealName(UserRealName);
            user.setUser_School(UserSchool);
            user.setUser_Major(UserMajor);
            user.setUser_IntentionalPost(UserIntentionalPost);
            user.setUser_City(UserCity);
            user.setUser_Email(UserMail);
            user.setUserImg(UserImg);
            user.setUser_Status(UserStatus);
            log.info(user.getUser_Status()+"     1");
            userService.updateUser(user);
            return JsonMsg.success().addInfo("respn", "修改成功");
        }catch (Exception e)
        {
            return JsonMsg.fail().addInfo("log_error",e+"修改失败");
        }
    }


    /**
     * 退出登录
     * @param request
     * @return
     */

    @RequestMapping(value = "/LoginOut")
    @ResponseBody
    public JsonMsg LoginOut(HttpServletRequest request) {
        try {
            log.info("用户退出登录");
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
             return  JsonMsg.success().addInfo("suc_ere","操作成功，欢迎下次登录");
        }catch (Exception e)
        {
            return JsonMsg.fail().addInfo("log_error","退出失败");
        }
    }


}
