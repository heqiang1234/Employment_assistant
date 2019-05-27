package com.human.controller;


import com.human.model.Company;
import com.human.model.PageBean;
import com.human.service.CompanyService;
import com.human.util.JsonMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/Company")
public class CompanyController {

    private static final Logger log = LoggerFactory.getLogger(CompanyController.class);

    @Resource
    private CompanyService companyService;

    /**
     *
     * 查询所有公司信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/ShowAllCompanyInfo")
    @ResponseBody
    public JsonMsg  ShowAllCompanyInfo(HttpServletRequest request)
    {
        try {
            log.info("查询所有公司信息");
            String currentPage = request.getParameter("CurrentPage");
            String pageSize = request.getParameter("PageSize");
            int cur = Integer.parseInt(currentPage);
            int pag = Integer.parseInt(pageSize);
            PageBean pagebean = companyService.findByPage(cur, pag);
            return JsonMsg.success().addInfo("pagebean",pagebean);
        }
        catch (Exception e)
        {
            return JsonMsg.fail().addInfo("log_error","查询失败");
        }


    }

    /**
     *
     *
     * 查询公司数量
     * @param request
     * @return
     */
    @RequestMapping(value = "/CompanyCountAll")
    @ResponseBody
    public JsonMsg CompanyCountAll(HttpServletRequest request)
    {
        try {
            log.info("查询公司数量");
            int CompanyCount = companyService.CompanyCountAll();
            return JsonMsg.success().addInfo("CompanyCount", CompanyCount);
        }
        catch (Exception e)
        {
            return JsonMsg.fail().addInfo("log_error","查询失败");
        }
    }

    /**
     *
     * 根据地址查询某地区公司数量
     * @param request
     * @return
     */
    @RequestMapping(value = "/CompanyCountByAddress")
    @ResponseBody
    public JsonMsg CompanyCountByAddress(HttpServletRequest request)
    {
        try {
            log.info("根据地址查询某地区公司信息");
            String CompanyAddress = request.getParameter("CompanyAddress");
            if (CompanyAddress == null || CompanyAddress.trim().length() == 0)
                return JsonMsg.Lose().addInfo("login_error", "错误数据，请重新输入！");
            int CompanyCountByAddress = companyService.CompanyCountByAddress(CompanyAddress);
            return JsonMsg.success().addInfo("CompanyCountByAddress", CompanyCountByAddress);
        }
        catch (Exception r)
        {
            return JsonMsg.fail().addInfo("log_error","查询失败");
        }
    }


    /**
     *
     *
     * 根据学校查询宣讲会所有公司信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/SelectCompanyBySchool")
    @ResponseBody
    public JsonMsg  SelectBySchool(HttpServletRequest request)
    {
        try {
            log.info("根据学校查询宣讲会所有公司信息");
            String CurrentPage = request.getParameter("CurrentPage");
            String PageSize = request.getParameter("PageSize");
            String School_Id = request.getParameter("School_Id");
            int cur = Integer.parseInt(CurrentPage);
            int pag = Integer.parseInt(PageSize);
            PageBean pagebean = companyService.findByPageBySchool(cur, pag, School_Id);
            return JsonMsg.success().addInfo("pagebean",pagebean);
        }catch (Exception e)
        {
            return JsonMsg.fail().addInfo("log_error","查询失败");
        }
    }



    /**
     *
     *
     * 根据公司ID查询宣讲会公司信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/SelectCompanyByCompanyId")
    @ResponseBody
    public JsonMsg SelectCompanyById(HttpServletRequest request)
    {
        try {
            log.info("根据公司ID查询宣讲会公司信息");
            String CurrentPage = request.getParameter("CurrentPage");
            String PageSize = request.getParameter("PageSize");
            String Company_Id = request.getParameter("Company_Id");
            String Career_Talk_Id = request.getParameter("Career_Talk_Id");
            int cur = Integer.parseInt(CurrentPage);
            int pag = Integer.parseInt(PageSize);
            PageBean<Company> companyList= companyService.selectByCompanyId(cur,pag,Company_Id, Career_Talk_Id);
            return JsonMsg.success().addInfo("companyList",companyList);
        }catch (Exception e)
        {
            return JsonMsg.fail().addInfo("log_error","查询失败");
        }
    }

}
