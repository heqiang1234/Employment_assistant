package com.human.model;

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
    private String logo;
    private String setTime;
    private String companyType;
    private String companyTrade;
    private String companyProfile;

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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
