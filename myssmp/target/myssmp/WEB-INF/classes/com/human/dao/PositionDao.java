package com.human.dao;

import com.human.model.Position;

import java.util.HashMap;
import java.util.List;

public interface PositionDao {
    /**
     * 查询到岗位所对应的宣讲会ID
     * @param
     * @return
     */
    List<Position> selectPositionToTald_Id(HashMap<String,Object> map);

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
     * 返回相应的数量
     * @param map
     * @return
     */
    int selectCountBytype(HashMap<String,Object> map);

}
