package com.human.service.impl;

import com.human.controller.EmploymentController;
import com.human.dao.EmploymentDao;
import com.human.model.Employment;
import com.human.model.PageBean;
import com.human.service.EmploymentService;
import com.human.util.JsonMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class EmploymentServiceImpl implements EmploymentService {


    private static final Logger log = LoggerFactory.getLogger(EmploymentServiceImpl.class);

    @Resource
    private EmploymentDao employmentDao;


    @Override
    public int selectCount() {
        return employmentDao.selectCount();
    }

    @Override
    public int selectCountById(String S_ID, String S_Name) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("S_ID",S_ID);
        map.put("S_Name",S_Name);
        return employmentDao.selectCountById(map);
    }

    @Override
    public int selectAllCountById(String S_ADDRESS_ID, String S_Name) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("S_ADDRESS_ID",S_ADDRESS_ID);
        map.put("S_Name",S_Name);
        log.info("selectAllCountById        "+S_ADDRESS_ID+"    "+S_Name);
        return employmentDao.selectAllCountById(map);
    }

    /**
     *分页的tenp类，减少代码冗余
     * @param currentPage
     * @param pagesize
     * @return
     */
    public PageBean<Employment> PageBean_temp(int currentPage,int pagesize,String temp_Id,String temp_Name)
    {
        HashMap<String,Object> map = new HashMap<String,Object>();
        PageBean<Employment> pageBean = new PageBean<Employment>();

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
        int totalCount = selectCountById(temp_Id,temp_Name);
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());


        if(currentPage<=num.intValue()-1)
            pageBean.setAfterPage(true);
        else
            pageBean.setAfterPage(false);
        map.put("start",(currentPage-1)*pageSize);
        map.put("size", pageBean.getPageSize());
        map.put("S_ID", temp_Id);
        map.put("S_Name", temp_Name);
        //封装每页显示的数据
        List<Employment> lists = employmentDao.selectEmploymentByType(map);
        pageBean.setLists(lists);

        return pageBean;

    }

    /**
     * 分页查询所有的宣讲会信息
     * @param currentPage
     * @param pagesize
     * @return
     */
    @Override
    public PageBean<Employment> findByPage(int currentPage,int pagesize) {
        PageBean<Employment> pageBean = PageBean_temp(currentPage,pagesize,"ALL","");
        return pageBean;
    }

    /**
     * 根据类型查询宣讲会信息
     * @param currentPage
     * @param pagesize
     * @param S_id
     * @param S_name
     * @return
     */
    @Override
    public PageBean<Employment> selectEmploymentByType(int currentPage, int pagesize, String S_id, String S_name) {
        PageBean<Employment> pageBean = new PageBean<Employment>();
        if(S_id=="Company"||S_id.equals("Company")) {
            log.info("公司查询宣讲会信息");
            pageBean= PageBean_temp(currentPage,pagesize,S_id,S_name);
        }
        else
        if(S_id=="School"||S_id.equals("School"))
        {
            log.info("学校查询宣讲会信息");
            pageBean = PageBean_temp(currentPage,pagesize,S_id,S_name);
        }
        else
        if(S_id=="Position"||S_id.equals("Position"))
        {
            log.info("岗位查询宣讲会信息");
            pageBean= PageBean_temp(currentPage,pagesize,S_id,S_name);
        }
        else
        if(S_id=="Professional"||S_id.equals("Professional"))
        {
            log.info("专业查询宣讲会信息");
            pageBean = PageBean_temp(currentPage,pagesize,S_id,S_name);
        }
        else
        if(S_id=="address"||S_id.equals("address"))
        {
            log.info("地址查询宣讲会信息");
            pageBean = PageBean_temp(currentPage,pagesize,S_id,S_name);
        }
        return pageBean;
    }

    @Override
    public PageBean<Employment> selectAllEmploymentByType(int currentPage, int pagesize, String S_Address_Id, String S_Name) {
        log.info("S搜索框查询宣讲会信息");
        HashMap<String,Object> map = new HashMap<String,Object>();
        PageBean<Employment> pageBean = new PageBean<Employment>();

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
        log.info("DSADAFE");
        //封装总记录数
        int totalCount = selectAllCountById(S_Address_Id,S_Name);
        pageBean.setTotalCount(totalCount);
        log.info("SDSADADASDAD框查询宣讲会信息   "+totalCount);
        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());


        if(currentPage<=num.intValue()-1)
            pageBean.setAfterPage(true);
        else
            pageBean.setAfterPage(false);
        map.put("start",(currentPage-1)*pageSize);
        map.put("size", pageBean.getPageSize());
        map.put("S_ADDRESS_ID", S_Address_Id);
        map.put("S_Name", S_Name);
        //封装每页显示的数据
        System.out.println("12121212");
        List<Employment> lists = employmentDao.selectAllEmploymentByType(map);
        pageBean.setLists(lists);

        return pageBean;
    }

    /**
     * 查询详细的宣讲会信息
     * @param career_talk_id
     * @return
     */
    @Override
    public List<Employment> selectEmploymentById(String career_talk_id) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("career_talk_id",career_talk_id);
        return employmentDao.selectEmploymentById(map);

    }

}
