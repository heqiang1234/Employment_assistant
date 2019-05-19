package com.human.controller;

import com.human.model.User;
import com.human.service.ShiroService;
import com.human.service.UserService;
import com.human.service.impl.SessionServiceImpl;
import com.human.util.JsonMsg;
import com.human.util.Md5Utils;
import org.apache.shiro.SecurityUtils;

import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * create on hq 2019/5/7;
 */

@Controller
@RequestMapping(value="/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Resource
    @Autowired
    private UserService userService;
         private ShiroService shiroService;


    @RequestMapping(value = "/showuser")
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

    @RequestMapping(value = "/login")
    @ResponseBody
    public  JsonMsg login(HttpServletRequest request, Model model){

        return JsonMsg.fail().addInfo("login_error", "没有登陆，请重新输入！");
    }


    /**
     * 对登录页面输入的用户名和密码进行验证
     * @param
     * @return
     */
    @RequestMapping(value="/dologin")
    @ResponseBody
    public JsonMsg  dologin(HttpServletRequest request) {

           try {
               //主体,当前状态为没有认证的状态“未认证”
               Subject currentUser = SecurityUtils.getSubject();
               // 登录后存放进shiro token
               if (!currentUser.isAuthenticated()) {
                   String user_name = request.getParameter("username");
                   String passWord = request.getParameter("password");
                   if (user_name == null || user_name.trim().length() == 0 || passWord == null || passWord.trim().length() == 0) {
                       return JsonMsg.fail().addInfo("login_error", "登陆失败，请重新输入！");
                   }
                   UsernamePasswordToken upToken = new UsernamePasswordToken(user_name, passWord);
                   //保存session
                   upToken.setRememberMe(true);

                   try {
                       currentUser.login(upToken);
                       User user;
                       user = userService.getUserByName(user_name);
                       return JsonMsg.success().addInfo("person", user);

                   } catch (IncorrectCredentialsException ice) {
                       System.out.println("邮箱/密码不匹配！");
                       return JsonMsg.fail().addInfo("login_error", "登陆失败，请重新输入！");
                   } catch (LockedAccountException lae) {
                       System.out.println("账户已被冻结！");
                       return JsonMsg.fail().addInfo("login_error", "登陆失败，请重新输入！");
                   } catch (AuthenticationException ae) {
                       System.out.println(ae.getMessage());
                       return JsonMsg.fail().addInfo("login_error", "登陆失败，请重新输入！");

                   }
               }
               User user;
               user=userService.getUserByName(currentUser.getPrincipal().toString());
               return  JsonMsg.success().addInfo("person", user);
           }
        catch (AuthenticationException qqe){
                   return JsonMsg.fail().addInfo("login_error", "登陆失败，请重新输入！");
               }

    }
//        //登录方法（认证是否通过）
//        //使用subject调用securityManager,安全管理器调用Realm
//        try {
//            //利用异常操作
//            //需要开始调用到Realm中
//            System.out.println("========================================");
//            System.out.println("1、进入认证方法");
//            subject.login(token);
//            user = (User) subject.getPrincipal();
//            session.setAttribute("user", subject);
//          //  model.addAttribute("message", "登录完成");
//            System.out.println("登录完成");
//            return  JsonMsg.success().addInfo("person",user);
//        } catch (UnknownAccountException e) {
//            return JsonMsg.fail().addInfo("login_error", "登陆失败，请重新输入！");
//        }
//    }

    @RequestMapping("/userinfo/{id}")
    //查找其中一个用户，路径带参数
    public String getUserList(@PathVariable int id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user",user);
        return  "detail";
    }

    /**
     * 注册添加用户
     * @param request
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/save")
    @ResponseBody
    public JsonMsg save(HttpServletRequest request) {
        log.info("注册用户");
        String user_name = request.getParameter("username");
        String passWord = request.getParameter("password");
        System.out.println(user_name+" "+passWord);
       String newPs= Md5Utils.encrypt(user_name,passWord);
        User user=new User();
        user.setUser_Name(user_name);
        user.setUser_Password(newPs);
        log.info(newPs);
        System.out.println(user.getUser_Name()+" "+user.getUser_Password());
        User user_temp=userService.getUserByName(user_name);
                if(user_temp!=null)
                {
                    return JsonMsg.fail().addInfo("login_error", "注册失败，请重新输入！");
                }
        userService.save(user);
        return  JsonMsg.success().addInfo("person",user);
    }

    /**
     * 更改用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateuser", method = RequestMethod.POST)
    public String update(User user) {
//        log.info("更改用户");
//        Date date = new Date();
//        user.setModifyTime(date);
        userService.update(user);
        return "redirect:showuser";
    }


    @ResponseBody
    @RequestMapping(value="/deleteuser/{id}",produces = "application/json; charset=utf-8")
    public String deleteUser(@PathVariable int id, Model model){
        int did = userService.deleteUserById(id);
        String s;
        if(did==1){
            s="成功删除";
        }else {
            s="删除失败";
        }
        return  s;
    }
}
