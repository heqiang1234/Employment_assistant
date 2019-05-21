package com.human.dao;

import com.human.model.Employment;

import java.util.HashMap;
import java.util.List;

public interface EmploymentDao {

    /**
     * 查询所有数据
     * @return
     */
    List<Employment> selectAllEmployment();

    /**
     * 查询数据记录总数
     * @return
     */
    int selectCount();
    /**
     * 分页操作，调用findByPage limit分页方法
     * @param map
     * @return
     */
    List<Employment> findByPage(HashMap<String,Object> map);
    /**
     *
     * 根据地址查询宣讲会
     */
    List<Employment> selectEmploymentByAddress(HashMap<String,Object> map);
    /**
     *
     * 根据专业查询宣讲会
     */
    List<Employment> selectEmploymentByPro(HashMap<String,Object> map);
    /**
     *
     * 根据学校查询宣讲会
     */
    List<Employment> selectEmploymentBySchool(HashMap<String,Object> map);
    /**
     *
     * 根据公司名查询宣讲会
     */
    List<Employment> selectEmploymentByCompanyName(HashMap<String,Object> map);
}
