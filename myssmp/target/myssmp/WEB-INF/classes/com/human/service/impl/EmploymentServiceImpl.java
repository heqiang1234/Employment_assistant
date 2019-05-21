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
    public int deleteByPrimaryKey(Integer id) {
        return employmentDao.deleteByPrimaryKey(id);
    }

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
}
