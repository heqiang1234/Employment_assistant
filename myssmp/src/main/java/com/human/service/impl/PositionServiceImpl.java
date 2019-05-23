package com.human.service.impl;

import com.human.dao.PositionDao;
import com.human.model.Employment;
import com.human.model.PageBean;
import com.human.model.Position;
import com.human.service.PositionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Resource
    private PositionDao positionDao;

    /**
     *
     * @param currentPage
     * @param pagesize
     * @param position_name
     * @return
     */

    @Override
    public PageBean<Position> selectPositionToTald_Id(int currentPage, int pagesize , String position_name) {

        HashMap<String,Object> map = new HashMap<String,Object>();
        PageBean<Position> pageBean = new PageBean<Position>();

        //封装当前页数
        pageBean.setCurrPage(currentPage);

        //判断是否有前一页,然后进行赋值操作，回显前台
        if(currentPage>1)
            pageBean.setPreviousPage(true);
        else
            pageBean.setPreviousPage(false);

        //每页显示的数据
        int pageSize=pagesize;
        pageBean.setPageSize(pageSize);

        //封装总记录数
        int totalCount = selectCountBytype("position_name",position_name);
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        map.put("start",(currentPage-1)*pageSize);
        map.put("size", pageBean.getPageSize());
        map.put("position_name", position_name);

        //封装每页显示的数据
        List<Position> lists =positionDao.selectPositionToTald_Id(map);
        pageBean.setLists(lists);
        return pageBean;



    }

    /**
     * 根据岗位对应宣讲会ID查询宣讲会
     * @param currentPage
     * @param pagesize
     * @param career_Id
     * @return
     */
    @Override
    public PageBean<Position> selectPositionIdToCareerd(int currentPage, int pagesize, String career_Id) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        PageBean<Position> pageBean = new PageBean<Position>();

        //封装当前页数
        pageBean.setCurrPage(currentPage);

        //判断是否有前一页,然后进行赋值操作，回显前台
        if(currentPage>1)
            pageBean.setPreviousPage(true);
        else
            pageBean.setPreviousPage(false);

        //每页显示的数据
        int pageSize=pagesize;
        pageBean.setPageSize(pageSize);

        //封装总记录数
        int totalCount = selectCountBytype("career_talk_id",career_Id);
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        map.put("start",(currentPage-1)*pageSize);
        map.put("size", pageBean.getPageSize());
        map.put("career_Id", career_Id);

        //封装每页显示的数据
        List<Position> lists =positionDao.selectPositionIdToCareerd(map);
        pageBean.setLists(lists);
        return pageBean;
    }

    @Override
    public int selectCountBytype(String S_ID,String S_Name) {
        HashMap<String,Object> map_count = new HashMap<String,Object>();
        map_count.put("S_ID",S_ID);
        map_count.put("S_Name",S_Name);
        int Count = positionDao.selectCountBytype(map_count);
        return  Count;
    }

    /**
     * 展示粗略的岗位信息
     * @param currentPage
     * @param pagesize
     * @return
     */
    @Override
    public PageBean<Position> selectAllPosition(int currentPage, int pagesize) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        PageBean<Position> pageBean = new PageBean<Position>();

        //封装当前页数
        pageBean.setCurrPage(currentPage);

        //判断是否有前一页,然后进行赋值操作，回显前台
        if(currentPage>1)
            pageBean.setPreviousPage(true);
        else
            pageBean.setPreviousPage(false);

        //每页显示的数据
        int pageSize=pagesize;
        pageBean.setPageSize(pageSize);

        //封装总记录数
        int totalCount = selectCountBytype("ALL","");
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        map.put("start",(currentPage-1)*pageSize);
        map.put("size", pageBean.getPageSize());
        //封装每页显示的数据
        List<Position> lists =positionDao.selectAllPosition(map);
        pageBean.setLists(lists);
        return pageBean;
    }

    @Override
    public List<Position> selectPositionById(String position_Id) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("position_Id",position_Id);
        return positionDao.selectPositionById(map);
    }

    @Override
    public PageBean<Position> selectPositionByType(int currentPage, int pagesize, String S_id, String S_name) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        PageBean<Position> pageBean = new PageBean<Position>();

        //封装当前页数
        pageBean.setCurrPage(currentPage);

        //判断是否有前一页,然后进行赋值操作，回显前台
        if(currentPage>1)
            pageBean.setPreviousPage(true);
        else
            pageBean.setPreviousPage(false);

        //每页显示的数据
        int pageSize=pagesize;
        pageBean.setPageSize(pageSize);

        //封装总记录数
        int totalCount = selectCountBytype(S_id,S_name);
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        map.put("start",(currentPage-1)*pageSize);
        map.put("size", pageBean.getPageSize());
        map.put("S_Id", S_id);
        map.put("S_Name", S_name);
        //封装每页显示的数据
        List<Position> lists =positionDao.selectPositionByType(map);
        pageBean.setLists(lists);
        return pageBean;
    }
}
