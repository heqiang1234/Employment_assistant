package com.human.dao;

import com.human.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Zhangxq on 2016/7/15.
 */

@Repository
public interface UserDao {

    User selectUserById(@Param("user_Id") int user_Id);

    User selectUserByName(@Param("user_Name") String user_Name);

    User selectUserByPhoneOrEmail(@Param("emailOrPhone") String emailOrPhone, @Param("state") Short state);

    List<User> selectAllUser();

    int deleteUserById(@Param("user_Id") int user_Id);

    //添加用户保存数据
    void save(User user);

    void update(User user);
}
