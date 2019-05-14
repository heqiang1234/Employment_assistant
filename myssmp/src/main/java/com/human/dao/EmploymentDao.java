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
     * 根据主键删除一条用户数据
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);


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
}
