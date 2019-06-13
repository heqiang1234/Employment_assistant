package com.human.service;

import com.human.model.Employment;
import com.human.model.PageBean;
import com.human.model.Position;

import java.util.HashMap;
import java.util.List;

public interface EmploymentService {

    int selectCount();


    /**
     * 查询数据记录总数根据条件
     * @return
     */
    int selectCountById(String S_ID,String S_Name);

    /**
     * 搜索框查询数据记录总数根据条件
     * @return
     */
    int selectAllCountById(String S_Search_Id,String S_Name);

    PageBean<Employment> findByPage(int currentPage,int  pagesize);


    /**
     *
     * 根据条件查询岗位
     */
    PageBean<Employment> selectEmploymentByType(int currentPage, int pagesize, String S_id, String S_name);
    /**
     *
     * 根据条件查询岗位
     */
    PageBean<Employment> selectAllEmploymentByType(int currentPage, int pagesize, String S_Search_Id, String S_name);

    /**
     * 根据宣讲会ID进入查询详细信息
     * @param
     * @return
     */
    List<Employment> selectEmploymentById(String career_talk_id);


    /**
     * 返回宣讲会图片和信息
     * @param
     * @return
     */
    List<Employment> selectEmploymentPhoto();


//    /**
//     * 根据宣讲会ID进入查询详细信息
//     * @param career_talk_id
//     * @return
//     */
//    List<Employment> selectEmploymentId(String career_talk_id);

}
