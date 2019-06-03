package com.human.service;

import com.human.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> getAllUser();

    public User getUserById(int user_Id);

    public User getUserByName(String user_Name);

    public User getUserByNameNoPassword(String user_Name);

    public int deleteUserById(int user_Id);

    void save(User user);

    void updateUser(User user);

    void saveImg(String url,String userName);

    Set<String> findPermissionsByUserId(int user_Id);

    void updateUserPassword(User user);

    void updateEmploymentId(User user);
}
