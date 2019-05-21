package com.human.service.impl;

import com.human.dao.CompanyDao;
import com.human.model.Company;
import com.human.model.Employment;
import com.human.model.PageBean;
import com.human.model.User;
import com.human.service.CompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CompanyServiceImpl implements CompanyService {

    @Resource
    private CompanyDao companyDao;

    //查询所有公司信息
    @Override
    public List<Company> getAllUser() {
        return companyDao.selectAllInfo();
    }
    //查询公司数量
    @Override
    public int CompanyCountAll() {
        return companyDao.CompanyCountAll();
    }
    //查询某地区公司数量
    @Override
    public int CompanyCountByAddress(String CompanyAddress) {
        return companyDao.CompanyCount(CompanyAddress);
    }
    //查询某学校宣讲会公司信息
    @Override
    public List<Company> selectBySchool(String SchoolAddress) {
        return companyDao.selectBySchool(SchoolAddress);
    }

    @Override
    public List<Company> selectByCompanyType(String CompanyType) {
        return companyDao.selectByCompanyType(CompanyType);
    }

    @Override
    public PageBean<Company> findByPageBySchool(int currentPage, int pagesize,String School_Id) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        PageBean<Company> pageBean = new PageBean<Company>();

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
        int totalCount = companyDao.CompanyCountAll();
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
        map.put("school_id", School_Id);
        //封装每页显示的数据
        List<Company> lists = companyDao.findByPage(map);
        pageBean.setLists(lists);

        return pageBean;
    }

    /**
     * 根据宣讲会ID和公司ID查询公司信息
     * @param CompanyId
     * @param Career_Talk_Id
     * @return
     */
    @Override
    public List<Company> selectByCompanyId(String CompanyId,String Career_Talk_Id) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("CompanyId",CompanyId);
        map.put("Career_Talk_Id", Career_Talk_Id);
        List<Company> lists = companyDao.selectByCompanyId(map);
        return lists;
    }

    @Override
    public PageBean<Company> findByPage(int currentPage, int pagesize) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        PageBean<Company> pageBean = new PageBean<Company>();

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
        int totalCount = companyDao.CompanyCountAll();
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
        List<Company> lists = companyDao.findByPage(map);
        pageBean.setLists(lists);

        return pageBean;
    }
}
