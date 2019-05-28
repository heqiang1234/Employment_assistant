package com.human.service.impl;

import com.human.dao.ShiroDao;
import com.human.model.Permission;
import com.human.model.User;
import com.human.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/10.
 */
@Service
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private ShiroDao shiroDao;

    public User getUserByUserName(String username) {
        //根据账号获取账号密码
        User userByUserName = shiroDao.getUserByUserName(username);
        return userByUserName;
    }

    public List<Permission> getPermissionsByUser(User user) {
        //获取到用户角色userRole
        System.out.println("12155");
        List<Integer> roleId = shiroDao.getUserRoleByUserId(user.getUser_Id());

        System.out.println(user.getUser_Id());
        List<Permission> perArrary = new ArrayList<>();
        System.out.println("HHHHH");
        if (roleId!=null&&roleId.size()!=0) {
            //根据roleid获取peimission
            for (Integer i : roleId) {
                perArrary.addAll(shiroDao.getPermissionsByRoleId(i));
            }
        }

        System.out.println(perArrary);
        return perArrary;
    }


}
