package com.human.controller;

import com.human.dao.UserDao;
import com.human.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class UserControllerTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Resource
    private UserService userService;

    @Test
    public void deleteUser() throws Exception {
        int did = userService.deleteUserById(4);
//        String s;
//        if(did==id){
//            s="成功删除";
//        }else {
//            s="删除失败";
//        }

        System.out.println(did);
    }

}