package com.human.service;

import com.human.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    public User getUserById(int user_Id);

    public User getUserByName(String user_Name);

    public int deleteUserById(int user_Id);

    void save(User user);

    void update(User user);
}
