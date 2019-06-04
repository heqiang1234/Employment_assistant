package com.human.dao;

import com.human.model.Employment;
import com.human.model.Position;

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
     * 查询数据记录总数根据条件
     * @return
     */
    int selectCountById(HashMap<String,Object> map);

    /**
     * 搜索框查询数据记录总数根据条件
     * @return
     */
    int selectAllCountById(HashMap<String,Object> map);

    /**
     * 分页操作，调用findByPage limit分页方法
     * @param map
     * @return
     */
    List<Employment> findByPage(HashMap<String,Object> map);

    /**
     * 根据类型来查找宣讲会信息
     * @param map
     * @return
     */
    List<Employment> selectEmploymentByType(HashMap<String,Object> map);

    /**
     * 搜索框根据类型来查找宣讲会信息
     * @param map
     * @return
     */
    List<Employment> selectAllEmploymentByType(HashMap<String,Object> map);


    /**
     * 根据宣讲会ID进入查询详细信息
     * @param
     * @return
     */
    List<Employment> selectEmploymentById(HashMap<String,Object> map);

//    /**
//     * c查找发送邮件的宣讲会ID信息
//     * @param
//     * @return
//     */
//    List<Employment> selectEmploymentId(HashMap<String,Object> map);

}
