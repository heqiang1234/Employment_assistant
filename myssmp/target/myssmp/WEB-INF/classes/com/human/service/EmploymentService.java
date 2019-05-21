package com.human.service;

import com.human.model.Employment;
import com.human.model.PageBean;

import java.util.HashMap;
import java.util.List;

public interface EmploymentService {
    List<Employment> selectAllEmployment();

    int selectCount();

    PageBean<Employment> findByPage(int currentPage,int  pagesize);
    /**
     *
     * 根据地址查询宣讲会
     */
    PageBean<Employment> selectEmploymentByAddress(int currentPage,int pagesize,String address);
    /**
     *
     * 根据专业查询宣讲会
     */
    PageBean<Employment> selectEmploymentByPro(int currentPage,int pagesize,String address);
    /**
     *
     * 根据学校查询宣讲会
     */
    PageBean<Employment> selectEmploymentBySchool(int currentPage,int pagesize,String School);
    /**
     *
     * 根据公司名查询宣讲会
     */
    PageBean<Employment> selectEmploymentByCompanyName(int currentPage,int pagesize,String CompanyName);
}
