package com.human.controller;


import com.human.model.PageBean;
import com.human.service.EmploymentService;
import com.human.service.PositionService;
import com.human.util.JsonMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/Search")
public class SearchController {

    private static final Logger log = LoggerFactory.getLogger(SearchController.class);

    @Resource
    private PositionService positionService;

    @Resource
    private EmploymentService employmentService;

    @RequestMapping(value = "/SearchByType")
    @ResponseBody
    public JsonMsg SearchEmploymentByType(HttpServletRequest request, Model model){
        try {
            log.info("搜索 根据类型查询查询各类信息");
            String currentPage = request.getParameter("CurrentPage");
            String pageSize = request.getParameter("PageSize");
            String Search_Address_Id = request.getParameter("Search_Address_Id");
            String Search_Id = request.getParameter("Search_Id");
            String Search_Name = request.getParameter("Search_Name");
            int cur = Integer.parseInt(currentPage);
            int pag = Integer.parseInt(pageSize);
            PageBean pageBean=new PageBean();
            if(Search_Id=="Company"||Search_Id.equals("Company"))
            {
                log.info("查询公司相关信息");
            }
            if(Search_Id=="Employment"||Search_Id.equals("Employment"))
            {
                log.info("查询宣讲会相关信息");
                log.info("查询岗位相关信息");
                pageBean=employmentService.selectAllEmploymentByType(cur,pag,Search_Address_Id,Search_Name);
                log.info("success");
                return JsonMsg.success().addInfo("pagebean_Position",pageBean);
            }
            if(Search_Id=="Position"||Search_Id.equals("Position"))
            {
                log.info("查询岗位相关信息");
                pageBean=positionService.selectAllPositionByType(cur,pag,Search_Address_Id,Search_Name);
                log.info("success");
                return JsonMsg.success().addInfo("pagebean_Position",pageBean);
            }
            if(Search_Id=="Professional"||Search_Id.equals("Professional"))
            {
                log.info("根据专业查询岗位信息");
                pageBean=positionService.selectAllPositionByType(cur,pag,Search_Address_Id,Search_Name);
                return JsonMsg.success().addInfo("pagebean_Professional",pageBean);
            }
            pageBean=positionService.selectAllPositionByType(cur,pag,Search_Address_Id,Search_Name);
            return JsonMsg.success().addInfo("pagebean",pageBean);
        }
        catch (Exception e)
        {
            return JsonMsg.fail().addInfo("log_error","查询失败");
        }
    }


}
