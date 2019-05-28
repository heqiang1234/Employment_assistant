package com.human.service.impl;

import com.human.dao.UserDao;
import com.human.model.User;
import com.human.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    public List<User> getAllUser() {

        return userDao.selectAllUser();
    }

    public User getUserById(int userId) {

        return userDao.selectUserById(userId);
    }

    public User getUserByName(String user_Name) {

        return userDao.selectUserByName(user_Name);
    }

    public User getUserByNameNoPassword(String user_Name) {

        return userDao.selectUserByNameNoPassword(user_Name);
    }

    public int deleteUserById(int userId){

        return  userDao.deleteUserById(userId);
    }

    public void save(User user) {

        userDao.save(user);
    }

    @Override
    public void updateUser(User user) {

        userDao.updateUser(user);
    }


    @Override
    public Set<String> findPermissionsByUserId(int user_Id) {

        return userDao.findPermissionsByUserId(user_Id);

    }

    @Override
    public void updateUserPassword(User user) {

         userDao.updateUserPassword(user);
    }


}









