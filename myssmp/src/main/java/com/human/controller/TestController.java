package com.human.controller;

import com.human.model.PageBean;
import com.human.service.EmploymentService;
import com.human.service.PositionService;
import com.human.util.JsonMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Controller
@RequestMapping(value = "Testt")
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private EmploymentService employmentService;

    @RequestMapping(value = "tt")
    @ResponseBody
    public JsonMsg test1(HttpServletRequest request)
    {
        String lin2="";
        try{
            log.info("查询所有宣讲会信息");
            String currentPage = request.getParameter("CurrentPage");
            String pageSize = request.getParameter("PageSize");
            int cur = Integer.parseInt(currentPage);
            int pag = Integer.parseInt(pageSize);
            PageBean pagebean = employmentService.findByPage(cur,pag);

            System.out.println("start");
            String[] args1=new String[]{"python","/usr/java/pythonFile/test1.py"};
            Process pr = Runtime.getRuntime().exec(args1);
            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;

            while ((line = in.readLine()) != null) {
                System.out.println(line);
                lin2+=line;
            }
            in.close();
            pr.waitFor();
            System.out.println("end");
            return JsonMsg.success().addInfo("pagebean"+lin2+"   2",pagebean);
        } catch (Exception e){
            return JsonMsg.fail().addInfo("error +"+lin2+"   2" ,e+" Tets");

        }

    }
}
