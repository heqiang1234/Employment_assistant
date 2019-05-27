package com.human.service.impl;

import com.human.dao.EmploymentDao;
import com.human.model.Employment;
import com.human.model.PageBean;
import com.human.service.EmploymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class EmploymentServiceImpl implements EmploymentService {

    @Resource
    private EmploymentDao employmentDao;

    @Override
    public List<Employment> selectAllEmployment() {

        return employmentDao.selectAllEmployment();
    }

    @Override
    public int selectCount() {
        return employmentDao.selectCount();
    }

    @Override
    public int selectCountById(String S_ID, String S_Name) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("S_ID",S_ID);
        map.put("S_Name",S_Name);
        System.out.println("655555122222");
        return employmentDao.selectCountById(map);
    }

    /**
     * 分页查询所有的宣讲会信息
     * @param currentPage
     * @param pagesize
     * @return
     */
    @Override
    public PageBean<Employment> findByPage(int currentPage,int pagesize) {
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
        int totalCount = employmentDao.selectCount();
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
        //封装每页显示的数据
        List<Employment> lists = employmentDao.findByPage(map);
        pageBean.setLists(lists);

        return pageBean;
    }

    /**
     * 根据地址分页查询宣讲会信息
     * @param currentPage
     * @param pagesize
     * @param address
     * @return
     */
    @Override
    public PageBean<Employment> selectEmploymentByAddress(int currentPage, int pagesize, String address) {
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
        int totalCount = selectCountById("address",address);
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        map.put("start",(currentPage-1)*pageSize);
        map.put("size", pageBean.getPageSize());
        map.put("address", address);

        //封装每页显示的数据
        List<Employment> lists = employmentDao.selectEmploymentByAddress(map);
        pageBean.setLists(lists);
        return pageBean;
    }

    /**
     * 根据专业分页查询宣讲会信息
     * @param currentPage
     * @param pagesize
     * @param pro
     * @return
     */
    @Override
    public PageBean<Employment> selectEmploymentByPro(int currentPage, int pagesize, String pro) {
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
        int totalCount = selectCountById("Professional",pro);
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
        map.put("pro", pro);
        //封装每页显示的数据
        List<Employment> lists = employmentDao.selectEmploymentByPro(map);
        pageBean.setLists(lists);

        return pageBean;
    }

    /**
     * 根据学校分页查询宣讲会信息
     * @param currentPage
     * @param pagesize
     * @param School
     * @return
     */
    @Override
    public PageBean<Employment> selectEmploymentBySchool(int currentPage, int pagesize, String School) {
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
        int totalCount = selectCountById("School",School);
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
        map.put("School",School);
        //封装每页显示的数据
        System.out.println("=---------------------------");
        List<Employment> lists = employmentDao.selectEmploymentBySchool(map);
        pageBean.setLists(lists);

        return pageBean;
    }

    /**
     * 根据公司名分页查询宣讲会信息
     * @param currentPage
     * @param pagesize
     * @param CompanyName
     * @return
     */
    @Override
    public PageBean<Employment> selectEmploymentByCompanyName(int currentPage, int pagesize, String CompanyName) {
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
        int totalCount = selectCountById("CompanyName",CompanyName);
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
        map.put("CompanyName",CompanyName);
        //封装每页显示的数据
        List<Employment> lists = employmentDao.selectEmploymentByCompanyName(map);
        pageBean.setLists(lists);

        return pageBean;
    }
}
