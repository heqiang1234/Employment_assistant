package com.human.service;

import com.human.model.PageBean;
import com.human.model.Position;

import java.util.HashMap;
import java.util.List;

public interface PositionService {

    /**
     * 查询到岗位所对应的宣讲会ID
     * @param position_Id
     * @return
     */
    PageBean<Position> selectPositionIdToCareerd(int currentPage, int pagesize , String position_Id);

    /**
     * 返回相应的数量
     * @param
     * @return
     */
    int selectCountBytype(String S_ID,String S_Name);


    /**
     * 搜索框返回相应的数量
     * @param S_address_id
     * @param S_Name
     * @return
     */
    int selectAllCountBytype(String S_address_id,String S_Name);

    /**
     * 展示所有岗位的粗略信息
     * @param
     * @return
     */
    PageBean<Position> selectAllPosition(int currentPage, int pagesize,String S_ID);

    /**
     * 根据岗位ID进入查询详细信息
     * @param
     * @return
     */
    List<Position> selectPositionById(String position_Id);

    /**
     *
     * 根据条件查询岗位
     */
    PageBean<Position> selectPositionByType(int currentPage, int pagesize,String S_id,String S_name);

    /**
     *
     * 搜索框根据条件查询岗位
     */
    PageBean<Position> selectAllPositionByType(int currentPage, int pagesize,String address_id,String S_name);
}
