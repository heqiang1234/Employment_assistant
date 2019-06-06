package com.human.controller;

import com.human.model.Employment;
import com.human.service.EmploymentService;
import com.human.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/Photo")
public class PhotoShowController {

    @Autowired
    private EmploymentService employmentService;

    @RequestMapping(value = "/ShowEmploymentPhoto")
    @ResponseBody
    public JsonMsg ShowEmploymentPhoto(HttpServletRequest request)
    {
        try {
            List<Employment> list = employmentService.selectEmploymentPhoto();
            return JsonMsg.success().addInfo("list", list);
        }
        catch (Exception e)
        {
            return JsonMsg.fail().addInfo("error_info",e+"返回失败");
        }
    }
}
