package com.human.model;

import java.util.List;

/**
 *
 * 2019/5/20 0:37 create by HQ
 * 公司信息表
 *
 */
public class Company {
    private static final long serialVersionUID = 8430941165882152228L;
    private int id;
    private String career_talk_id;
    private String company_id;
    private String school_id;
    private String company_name;
    private String company_logo;
    private String company_size;
    private String company_address;
    private String map_link;
    private String image_link;
    private String setTime;
    private String companyType;
    private String companyTrade;
    private String companyProfile;
    private String companyScore;

    public String getCompany_logo() {
        return company_logo;
    }

    public void setCompany_logo(String company_logo) {
        this.company_logo = company_logo;
    }

    public String getCompany_size() {
        return company_size;
    }

    public void setCompany_size(String company_size) {
        this.company_size = company_size;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public String getMap_link() {
        return map_link;
    }

    public void setMap_link(String map_link) {
        this.map_link = map_link;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getCompanyScore() {
        return companyScore;
    }

    public void setCompanyScore(String companyScore) {
        this.companyScore = companyScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCareer_talk_id() {
        return career_talk_id;
    }

    public void setCareer_talk_id(String career_talk_id) {
        this.career_talk_id = career_talk_id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getSetTime() {
        return setTime;
    }

    public void setSetTime(String setTime) {
        this.setTime = setTime;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCompanyTrade() {
        return companyTrade;
    }

    public void setCompanyTrade(String companyTrade) {
        this.companyTrade = companyTrade;
    }

    public String getCompanyProfile() {
        return companyProfile;
    }

    public void setCompanyProfile(String companyProfile) {
        this.companyProfile = companyProfile;
    }
}
