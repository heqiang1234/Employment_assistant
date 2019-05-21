package com.human.service;

import com.human.model.Company;
import com.human.model.PageBean;

import java.util.List;

public interface CompanyService {
    //查询所有公司信息
     List<Company> getAllUser();

    //查询公司数量
     int CompanyCountAll();

    //查询某地区公司数量
     int CompanyCountByAddress(String  CompanyAddress);

    //查询某学校宣讲会公司信息
    List<Company> selectBySchool(String  SchoolAddress);

    //根据行业查询宣讲会公司信息
     List<Company> selectByCompanyType(String  CompanyType);

    PageBean<Company> findByPage(int currentPage, int  pagesize);

    PageBean<Company> findByPageBySchool(int currentPage, int  pagesize,String School_Id);

    //根据行业查询宣讲会公司信息
    List<Company> selectByCompanyId(String  CompanyId,String Career_Talk_Id);

}
