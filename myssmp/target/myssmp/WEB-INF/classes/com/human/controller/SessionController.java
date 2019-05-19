package com.human.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.human.shiro.SessionDao;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.Session;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 创建日期:2017/12/21<br/>
 * 创建时间:11:05:18<br/>
 * 创建用户:yellowcong<br/>
 * 机能概要:
 */
@Controller
@RequestMapping("/session")
@Scope("prototype")
public class SessionController {

    @Autowired
    private SessionDao sessionDao;

    @Autowired
    private EhCacheManager cacheManager;

    //cacheManager
    /**
     * 创建日期:2017/12/21<br/>
     * 创建时间:11:06:17<br/>
     * 创建用户:yellowcong<br/>
     * 机能概要:删除session
     * @param username
     */
    @RequestMapping(value="/del/{username}",method=RequestMethod.GET)
    public void delSession(@PathVariable String username,HttpServletResponse response) {

        //获取到当前登录用户的session
        List<Session> sessions = this.sessionDao.loadByUserName(username);
        if(sessions != null && sessions.size() >0) {

            //遍历删除到session
            for(Session session :sessions) {
                this.removeSession(session);
            }
        }

        //写数据到浏览器
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("result", true);
        map.put("username", username);
        map.put("cnt", sessions.size());
        this.writeJSON(response, map);
    }

    /**
     * 创建日期:2017/12/21<br/>
     * 创建时间:13:59:03<br/>
     * 创建用户:yellowcong<br/>
     * 机能概要:删除session中的数据
     * @param session
     */
    private  void removeSession(Session session) {
        //session id
        String sessionid = session.getId().toString();
        //删除缓存中的session
        this.cacheManager.getCache("shiro-activeSessionCache").remove(sessionid);

        //删除数据库中的session
        this.sessionDao.delete(session);
    }


    /**
     * 在SpringMvc中获取到Session
     * @return
     */
    public void writeJSON(HttpServletResponse response,Object object){
        try {
            //设定编码
            response.setCharacterEncoding("UTF-8");
            //表示是json类型的数据
            response.setContentType("application/json");
            //获取PrintWriter 往浏览器端写数据
            PrintWriter writer = response.getWriter();

            ObjectMapper mapper = new ObjectMapper(); //转换器
            //获取到转化后的JSON 数据
            String json = mapper.writeValueAsString(object);
            //写数据到浏览器
            writer.write(json);
            //刷新，表示全部写完，把缓存数据都刷出去
            writer.flush();

            //关闭writer
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
