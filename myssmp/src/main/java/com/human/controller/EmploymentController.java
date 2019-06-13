package com.human.controller;

import com.human.model.Employment;
import com.human.model.PageBean;
import com.human.model.Position;
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
          PageBean pagebean = employmentService.findByPage(cur,pag);
          log.info(pagebean.getLists().toString());
          return JsonMsg.success().addInfo("pagebean",pagebean);
      }
      catch(Exception e)
      {
          return JsonMsg.fail().addInfo("log_error","查询失败 "+e);
      }
    }

    @RequestMapping(value = "/ShowEmploymentCount")
    @ResponseBody
    public JsonMsg ShowEmploymentCount(HttpServletRequest request, Model model) {
        try {
            log.info("查询宣讲会数目");
            int count = employmentService.selectCount();
            return JsonMsg.success().addInfo("pagebean",count);
        }
        catch (Exception e)
        {
            return JsonMsg.fail().addInfo("log_error","查询失败 "+e);
        }
    }

    @RequestMapping(value = "/ShowAllEmploymentInfo")
    @ResponseBody
    public JsonMsg ShowAllEmploymentInfo(HttpServletRequest request, Model model) {
        try {
            log.info("查询详细");
            String career_talk_id = request.getParameter("career_talk_id");
            List<Employment> lists = employmentService.selectEmploymentById(career_talk_id);
            return JsonMsg.success().addInfo("pagebean",lists);
        }
        catch (Exception e)
        {
            return JsonMsg.fail().addInfo("log_error","查询失败 "+e);
        }
    }

    @RequestMapping(value = "/ShowEmploymentInfoByCompany")
    @ResponseBody
    public JsonMsg ShowEmploymentInfoByCompany(HttpServletRequest request, Model model) {
        try {
            String currentPage = request.getParameter("CurrentPage");
            String pageSize = request.getParameter("PageSize");
        //    String Search_Id = request.getParameter("Search_Id");
            String Search_Name = request.getParameter("Search_Name");
            int cur = Integer.parseInt(currentPage);
            int pag = Integer.parseInt(pageSize);
            log.info("公司id查询宣讲会信息");
            PageBean  pagebean_Company= employmentService.selectEmploymentByType(cur,pag,"Company_ID",Search_Name);
            return JsonMsg.success().addInfo("pagebean",pagebean_Company);
        }
        catch (Exception e)
        {
            return JsonMsg.fail().addInfo("log_error","查询失败 "+e);
        }
    }



    /**
     * 宣讲会信息搜索展示
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/SearchEmployment")
    @ResponseBody
    public  JsonMsg SearchEmployment(HttpServletRequest request, Model model){
        try {
            log.info("查询宣讲会信息");
            String currentPage = request.getParameter("CurrentPage");
            String pageSize = request.getParameter("PageSize");
//            String Search_Id = request.getParameter("Search_Id");
            String Search_Name = request.getParameter("Search_Name");
            int cur = Integer.parseInt(currentPage);
            int pag = Integer.parseInt(pageSize);
            log.info("搜索框查询宣讲会信息");
             PageBean  pagebean_Professional = employmentService.selectEmploymentByType(cur,pag,"All_Search",Search_Name);
             return JsonMsg.success().addInfo("pagebean",pagebean_Professional);
        }
        catch (Exception e)
        {
            return JsonMsg.fail().addInfo("log_error","查询失败 "+e );
        }
    }

}
