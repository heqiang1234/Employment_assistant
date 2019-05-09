package com.human.service.impl;

import com.human.dao.UserDao;
import com.human.model.User;
import com.human.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
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

    public int deleteUserById(int userId){
        return  userDao.deleteUserById(userId);
    }

    public void save(User user) {
        userDao.save(user);
    }

    public void update(User user) {

        userDao.update(user);
    }
}









