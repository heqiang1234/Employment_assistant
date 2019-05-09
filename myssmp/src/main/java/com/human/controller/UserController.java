package com.human.controller;

import com.human.model.User;
import com.human.service.UserService;
import com.human.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/")
public class UserController {

    /**
     * create on hq 2019/5/7;
     */

    @Resource
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/showuser")
    public String showUser(HttpServletRequest request, Model model){

              System.out.println("showuser");
       //log.info("查询所有用户信息");
        List<User> userList = userService.getAllUser();
        model.addAttribute("userList",userList);
        System.out.println("ss");
        String code="100";
        return "showUser";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }


    /**
     * 对登录页面输入的用户名和密码做简单的判断
     * @param request
     * @return
     */
    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg dologin(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + password);
        User user = userService.getUserById(6);
        if(username!=user.getUsername())
        if (!"admin1234".equals(username + password)){
            return JsonMsg.fail().addInfo("login_error", "输入账号用户名与密码不匹配，请重新输入！");
        }
        return JsonMsg.success();
    }


    @RequestMapping("/userinfo/{id}")
    //查找其中一个用户，路径带参数
    public String getUserList(@PathVariable int id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user",user);
        return  "detail";
    }


    @RequestMapping(value = "/add")
    public String toAdd(HttpServletRequest request) {
        return "adduser";

    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public User save(User user) {
        userService.save(user);
        return user;
    }

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
