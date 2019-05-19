package com.human.controller;

import com.alibaba.fastjson.JSONObject;
import com.human.model.User;
import com.human.service.UserService;
import com.human.util.JsonMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * create on hq 2019/5/7;
 */

@Controller
@RequestMapping("/")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Resource
    @Autowired
    private UserService userService;



    @RequestMapping(value = "/showuser")
    public String showUser(HttpServletRequest request, Model model){

       log.info("查询所有用户信息");
        List<User> userList = userService.getAllUser();
        model.addAttribute("userList",userList);
        return "showUser";
    }

    /**
     * 对登录页面输入的用户名和密码进行验证
     * @param
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value="/dologin")
    @ResponseBody
    public JsonMsg  dologin(HttpServletRequest request){

        String user_name = request.getParameter("username");
        String passWord = request.getParameter("password");
        User user = userService.getUserByName(user_name);
        log.info("登陆验证");
        if(!user_name.equals(user.getUser_Name()))
        {
            return JsonMsg.fail().addInfo("login_error", "不存在该用户名，请重新输入！");
        }
        else
            if(!passWord.equals(user.getUser_Password()))
            {
                return JsonMsg.fail().addInfo("login_error", "输入账号用户名与密码不匹配，请重新输入！");
            }
            return  JsonMsg.success().addInfo("person",user);
    }


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
        User user=new User();
        user.setUser_Name(user_name);
        user.setUser_Password(passWord);
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
