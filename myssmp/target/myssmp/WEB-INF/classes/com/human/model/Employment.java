package com.human.model;

/**
 *  create by hq on 2019/5/14;
 * 宣讲会信息表
 */
public class Employment {
    private static final long serialVersionUID = 8430941165882152228L;
    private int employment_id;
    private String school_id;
    private String career_talk_id;
    private String company_id;
    private String company_name;
    private String logo;
    private String hitCount;
    private String recruitPost;
    private String professionals;
    private String meet_name;
    private String meet_time;
    private String meet_day;
    private String address;
    private String presentLink;

    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getEmployment_id() {
        return employment_id;
    }

    public String getSchool_id() {
        return school_id;
    }

    public String getCareer_talk_id() {
        return career_talk_id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getProfessionals() {
        return professionals;
    }

    public String getMeet_name() {
        return meet_name;
    }

    public String getMeet_time() {
        return meet_time;
    }

    public String getMeet_day() {
        return meet_day;
    }

    public String getAddress() {
        return address;
    }

    public String getPresentLink() {
        return presentLink;
    }

    public String getHitCount() {
        return hitCount;
    }

    public String getRecruitPost() {
        return recruitPost;
    }

    public void setEmployment_id(int employment_id) {
        this.employment_id = employment_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    public void setCareer_talk_id(String career_talk_id) {
        this.career_talk_id = career_talk_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public void setProfessionals(String professionals) {
        this.professionals = professionals;
    }

    public void setMeet_name(String meet_name) {
        this.meet_name = meet_name;
    }

    public void setMeet_time(String meet_time) {
        this.meet_time = meet_time;
    }

    public void setMeet_day(String meet_day) {
        this.meet_day = meet_day;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPresentLink(String presentLink) {
        this.presentLink = presentLink;
    }

    public void setHitCount(String hitCount) {
        this.hitCount = hitCount;
    }

    public void setRecruitPost(String recruitPost) {
        this.recruitPost = recruitPost;
    }
}
