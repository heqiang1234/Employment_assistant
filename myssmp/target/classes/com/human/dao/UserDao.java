package com.human.dao;

import com.human.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by Zhangxq on 2016/7/15.
 */

@Repository
public interface UserDao {

    User selectUserById(@Param("user_Id") int user_Id);

    User selectUserByName(@Param("user_Name") String user_Name);

    User selectUserByNameNoPassword(@Param("user_Name") String user_Name);

    User selectUserByPhoneOrEmail(@Param("emailOrPhone") String emailOrPhone, @Param("state") Short state);

    List<User> selectAllUser();

    Set<String> findPermissionsByUserId(int user_Id);

    int deleteUserById(@Param("user_Id") int user_Id);

    void updateEmploymentId(User user);

    //添加用户保存数据
    void save(User user);

    void updateUser(User user);

    void  saveImg(@Param("ImgUrl") String ImgUrl,@Param("userName") String userName);

    void  saveResume(@Param("ResumeUrl") String ResumeUrl,@Param("userName") String userName);

    void updateUserPassword(User user);
}
