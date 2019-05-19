package com.human.controller;

import com.human.model.Employment;
import com.human.model.PageBean;
import com.human.service.EmploymentService;
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
@RequestMapping("/")
public class EmploymentController {

    private static final Logger log = LoggerFactory.getLogger(EmploymentController.class);

    @Resource
    @Autowired
    private EmploymentService employmentService;

    @RequestMapping(value = "/showemployment")
    public @ResponseBody PageBean<Employment> showEmployment(HttpServletRequest request, Model model){

        log.info("查询所有宣讲会信息");
        String currentPage = request.getParameter("currentPage");
        int cur=Integer.parseInt(currentPage);
       PageBean pagebean = employmentService.findByPage(cur);
        //model.addAttribute("userList",userList);
        return pagebean;
    }
    @RequestMapping("/main")
    public String  main(@RequestParam(value="currentPage",defaultValue="1",required=false)int currentPage, Model model){
        model.addAttribute("pagemsg", employmentService.findByPage(currentPage));//回显分页数据
        return "main";
    }
}
