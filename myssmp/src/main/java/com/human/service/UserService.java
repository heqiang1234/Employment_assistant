package com.human.service;

import com.human.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    public User getUserById(int userId);

    public int deleteUserById(int userId);

    void save(User user);

    void update(User user);
}
