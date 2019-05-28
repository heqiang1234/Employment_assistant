package com.human.controller;

import com.human.model.Employment;
import com.human.model.PageBean;
import com.human.service.EmploymentService;
import com.human.util.JsonMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * create on hq 2019/5/7;
 */

@Controller
@RequestMapping("/Employment")
public class EmploymentController {

    private static final Logger log = LoggerFactory.getLogger(EmploymentController.class);

    @Resource
    @Autowired
    private EmploymentService employmentService;

    @RequestMapping(value = "/ShowEmployment")
    @ResponseBody
    public JsonMsg showEmployment(HttpServletRequest request, Model model){
      try {
          log.info("查询所有宣讲会信息");
          String currentPage = request.getParameter("CurrentPage");
          String pageSize = request.getParameter("PageSize");
          int cur = Integer.parseInt(currentPage);
          int pag = Integer.parseInt(pageSize);
          PageBean pagebean = employmentService.findByPage(cur, pag);
          return JsonMsg.success().addInfo("pagebean",pagebean);
      }
      catch(Exception e)
      {
          return JsonMsg.fail().addInfo("log_error","查询失败");
      }
    }

    @RequestMapping(value = "/ShowEmploymentCount")
    @ResponseBody
    public JsonMsg ShowEmploymentCount(HttpServletRequest request, Model model) {
        try {
            log.info("查询宣讲会数目");
            int count = employmentService.selectCount();
            return JsonMsg.success().addInfo("count",count);
        }
        catch (Exception e)
        {
            return JsonMsg.fail().addInfo("log_error","查询失败");
        }
    }

    @RequestMapping(value = "/SearchEmployment")
    @ResponseBody
    public  JsonMsg SearchEmployment(HttpServletRequest request, Model model){
        try {
            log.info("查询宣讲会信息");
            String currentPage = request.getParameter("CurrentPage");
            String pageSize = request.getParameter("PageSize");
            String Search_Id = request.getParameter("Search_Id");
            String Search_Name = request.getParameter("Search_Name");
            int cur = Integer.parseInt(currentPage);
            int pag = Integer.parseInt(pageSize);
            if(Search_Id=="Company"||Search_Id.equals("Company")) {
                log.info("公司查询宣讲会信息");
                PageBean  pagebean_Company= employmentService.selectEmploymentByCompanyName(cur, pag, Search_Name);
                return JsonMsg.success().addInfo("pagebean_Company",pagebean_Company);
            }
            else
            if(Search_Id=="School"||Search_Id.equals("School"))
            {
                log.info("学校查询宣讲会信息");
                PageBean  pagebean_School = employmentService.selectEmploymentBySchool(cur, pag, Search_Name);
                return JsonMsg.success().addInfo("pagebean_School",pagebean_School);
            }
            else
            if(Search_Id=="Position"||Search_Id.equals("Position"))
            {
                log.info("岗位查询宣讲会信息");
                PageBean  pagebean_Position = employmentService.findByPage(cur, pag);
                return JsonMsg.success().addInfo("pagebean_Position",pagebean_Position);
            }
            else
                if(Search_Id=="Professional"||Search_Id.equals("Professional"))
                {
                    log.info("专业查询宣讲会信息");
                    PageBean  pagebean_Professional = employmentService.selectEmploymentByPro(cur, pag, Search_Name);
                    return JsonMsg.success().addInfo("pagebean_Professional",pagebean_Professional);
                }
            PageBean pagebean = employmentService.findByPage(cur, pag);
            return JsonMsg.success().addInfo("pagebean",pagebean);
        }
        catch (Exception e)
        {
            return JsonMsg.fail().addInfo("log_error","查询失败");
        }
    }


  /*  @RequestMapping(value = "/SelectEmploymentByAddress")
    @ResponseBody
    public  JsonMsg SelectEmploymentByAddress(HttpServletRequest request, Model model){
        try {
            log.info("根据地址查询宣讲会信息");
            String currentPage = request.getParameter("CurrentPage");
            String pageSize = request.getParameter("PageSize");
            String address = request.getParameter("Address");
            int cur = Integer.parseInt(currentPage);
            int pag = Integer.parseInt(pageSize);
            PageBean pagebean = employmentService.selectEmploymentByAddress(cur, pag, address);
            return JsonMsg.success().addInfo("pagebean",pagebean);
        }
        catch (Exception e)
        {
            return JsonMsg.fail().addInfo("log_error","查询失败");
        }
    }*/


/*    @RequestMapping(value = "/selectEmploymentByPro")
    @ResponseBody
    public JsonMsg selectEmploymentByPro(HttpServletRequest request, Model model){
        try {
            log.info("根据专业查询宣讲会信息");
            String currentPage = request.getParameter("CurrentPage");
            String pageSize = request.getParameter("PageSize");
            String Pro = request.getParameter("Professional");
            int cur = Integer.parseInt(currentPage);
            int pag = Integer.parseInt(pageSize);
            PageBean pagebean = employmentService.selectEmploymentByAddress(cur, pag, Pro);
           return   JsonMsg.success().addInfo("info",pagebean);
        }
         catch(Exception e)
            {
                return JsonMsg.fail().addInfo("log_error","查询失败");
            }

    }*/


  /*  @RequestMapping(value = "/SelectEmploymentBySchool")
    @ResponseBody
    public  JsonMsg selectEmploymentBySchool(HttpServletRequest request, Model model){
          try {
              log.info("根据学校查询宣讲会信息");
              String currentPage = request.getParameter("CurrentPage");
              String pageSize = request.getParameter("PageSize");
              String School = request.getParameter("School");
              int cur = Integer.parseInt(currentPage);
              int pag = Integer.parseInt(pageSize);
              PageBean pagebean = employmentService.selectEmploymentBySchool(cur, pag, School);
              return JsonMsg.success().addInfo("pagebean",pagebean);
          }
          catch (Exception e)
          {
              return JsonMsg.fail().addInfo("log_error","查询失败");
          }
    }*/


/*    @RequestMapping(value = "/SelectEmploymentByCompany")
    @ResponseBody
    public  JsonMsg selectEmploymentByCompany(HttpServletRequest request, Model model){
          try{
        log.info("根据公司名查询宣讲会信息");
        String currentPage = request.getParameter("CurrentPage");
        String pageSize = request.getParameter("PageSize");
        String CompanyName = request.getParameter("CompanyName");
        int cur=Integer.parseInt(currentPage);
        int pag=Integer.parseInt(pageSize);
        PageBean pagebean = employmentService.selectEmploymentByCompanyName(cur,pag,CompanyName);
        return JsonMsg.success().addInfo("pagebean",pagebean);
    } catch (Exception e)
          {
              return JsonMsg.fail().addInfo("log_error","查询失败");
          }
    }*/

}
