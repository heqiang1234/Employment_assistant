package com.human.model;

public class Position {
    private static final long serialVersionUID = 8430941165882152228L;
    private  int positionID;
    private String school_id;
    private String career_talk_id;
    private String position_name;
    private String professionals;
    private String jobDescription;
    private String positionWage;
//    private String company_logo;
//    private String companyType;
//    private String company_size;
    private String num;
    private String workPlace;
    private String positionTemptation;
    private String salaryWelfare;

    private Company company;

//    private Employment employment;
//
//    public Employment getEmployment() {
//        return employment;
//    }
//
//    public void setEmployment(Employment employment) {
//        this.employment = employment;
//    }

    public String getPositionTemptation() {
        return positionTemptation;
    }

    public void setPositionTemptation(String positionTemptation) {
        this.positionTemptation = positionTemptation;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getPositionID() {
        return positionID;
    }

    public void setPositionID(int positionID) {
        this.positionID = positionID;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    public String getCareer_talk_id() {
        return career_talk_id;
    }

    public void setCareer_talk_id(String career_talk_id) {
        this.career_talk_id = career_talk_id;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    public String getProfessionals() {
        return professionals;
    }

    public void setProfessionals(String professionals) {
        this.professionals = professionals;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getPositionWage() {
        return positionWage;
    }

    public void setPositionWage(String positionWage) {
        this.positionWage = positionWage;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getSalaryWelfare() {
        return salaryWelfare;
    }

    public void setSalaryWelfare(String salaryWelfare) {
        this.salaryWelfare = salaryWelfare;
    }
}
