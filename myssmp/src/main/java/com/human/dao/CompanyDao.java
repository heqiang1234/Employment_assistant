package com.human.dao;

import com.human.model.Company;
import com.human.model.Employment;

import java.util.HashMap;
import java.util.List;

/**
 *
 * 2019/5/20 0:37 create by HQ
 * 公司DAO
 *
 */
public interface CompanyDao {

    //查询所有公司信息
     List<Company> selectAllInfo();

     //查询公司数量
     int CompanyCountAll();

     //查询某地区公司数量
     int CompanyCount(String  CompanyAddress);



    /**
     * 分页操作，调用findByPage limit分页方法
     * @param map
     * @return
     */
    List<Company> findByPage(HashMap<String,Object> map);

    /**
     * 分页操作，findByPageBySchool limit分页方法
     * @param map
     * @return
     */
    List<Company> findByPageBySchool(HashMap<String,Object> map);

    //查询某学校宣讲会公司信息
    List<Company> selectBySchool(String  SchoolAddress);

    //根据行业查询宣讲会公司信息
    List<Company> selectByCompanyType(String  CompanyType);

    //根据行业查询宣讲会公司信息
    List<Company> selectByCompanyId(HashMap<String,Object> map);

}
