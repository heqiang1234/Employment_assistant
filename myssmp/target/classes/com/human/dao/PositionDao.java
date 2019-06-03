package com.human.dao;

import com.human.model.Employment;
import com.human.model.Position;

import java.util.HashMap;
import java.util.List;

public interface PositionDao {
//    /**
//     * 查询到岗位所对应的宣讲会ID
//     * @param
//     * @return
//     */
//    List<Position> selectPositionToTald_Id(HashMap<String,Object> map);

    /**
     * 查询到岗位ID所对应的宣讲会ID
     * @param
     * @return
     */
    List<Position> selectPositionIdToCareerd(HashMap<String,Object> map);

    /**
     * 展示所有岗位的粗略信息
     * @param
     * @return
     */
    List<Position> selectAllPosition(HashMap<String,Object> map);

    /**
     * 根据岗位ID进入查询详细信息
     * @param
     * @return
     */
    List<Position> selectPositionById(HashMap<String,Object> map);

    /**
     * 根据岗位Z中的宣讲会ID 进入宣讲会
     * @param
     * @return
     */
    List<Position> selectEmploymentByCareerId(HashMap<String,Object> map);

    /**
     * 返回相应的数量
     * @param map
     * @return
     */
    int selectCountBytype(HashMap<String,Object> map);

    /**
     * 搜索框返回相应的数量
     * @param map
     * @return
     */
    int selectAllCountBytype(HashMap<String,Object> map);

    /**
     *
     * 根据条件查询岗位
     */
    List<Position> selectPositionByType(HashMap<String,Object> map);

    /**
     *
     * 搜索框查询的总接口
     */
    List<Position> selectAllPositionByType(HashMap<String,Object> map);

    /**
     *
     * 根据position——name和professionals查询ID返回到Employmrnt
     */
    List<Position> selectPositionToEmail(HashMap<String,Object> map);

}
