package com.human.model;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 *  create by hq on 2019/5/9;
 * 用户表
 */

public class User implements Serializable {

    private static final long serialVersionUID = 8430941165882152228L;
    private Integer user_Id;
    private String user_Name;
    private String user_Password;
    private String user_RealName;
    private String user_Sex;
    private String user_City;
    private String user_School;
    private String user_Major;
    private String user_IntentionalPost;
    private String user_Email;
    private String user_Employment_Id;
    private String UserImg;
    private String user_Status;
    private String user_Resume;

    public String getUser_Resume() {
        return user_Resume;
    }

    public void setUser_Resume(String user_Resume) {
        this.user_Resume = user_Resume;
    }

    public String getUser_Status() {
        return user_Status;
    }

    public void setUser_Status(String user_Status) {
        this.user_Status = user_Status;
    }

    public String getUser_City() {
        return user_City;
    }

    public void setUser_City(String user_City) {
        this.user_City = user_City;
    }

    public String getUserImg() {
        return UserImg;
    }

    public void setUserImg(String userImg) {
        UserImg = userImg;
    }

    public String getUser_Sex() {
        return user_Sex;
    }

    public void setUser_Sex(String user_Sex) {
        this.user_Sex = user_Sex;
    }

    public String getUser_Employment_Id() {
        return user_Employment_Id;
    }

    public void setUser_Employment_Id(String user_Employment_Id) {
        this.user_Employment_Id = user_Employment_Id;
    }

    public Integer getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(Integer user_Id) {
        this.user_Id = user_Id;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    @JSONField(serialize=false)
    public String getUser_Password() {
        return user_Password;
    }

    public void setUser_Password(String user_Password) {
        this.user_Password = user_Password;
    }

    public String getUser_RealName() {
        return user_RealName;
    }

    public void setUser_RealName(String user_RealName) {
        this.user_RealName = user_RealName;
    }

    public String getUser_School() {
        return user_School;
    }

    public void setUser_School(String user_School) {
        this.user_School = user_School;
    }

    public String getUser_Major() {
        return user_Major;
    }

    public void setUser_Major(String user_Major) {
        this.user_Major = user_Major;
    }

    public String getUser_IntentionalPost() {
        return user_IntentionalPost;
    }

    public void setUser_IntentionalPost(String user_IntentionalPost) {
        this.user_IntentionalPost = user_IntentionalPost;
    }

    public String getUser_Email() {
        return user_Email;
    }

    public void setUser_Email(String user_Email) {
        this.user_Email = user_Email;
    }
}
