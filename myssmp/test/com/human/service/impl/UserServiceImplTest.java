package com.human.service.impl;

import com.human.dao.UserDao;
import com.human.model.User;
import com.human.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


// 加载spring配置文件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class UserServiceImplTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllUser1() throws Exception {
    }

    @Autowired
    private UserService userService;

    @Test
    public void getAllUser() throws Exception {

        List<User> l=userService.getAllUser();
        User user = l.get(0);
        System.out.println(user.getUsername());

    }

}