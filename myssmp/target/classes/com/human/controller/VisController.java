package com.human.controller;

import com.human.model.Mapp;
import com.human.model.Photo;
import com.human.model.eChartsData;
import com.human.service.eChartsDataService;
import com.human.util.JsonMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.human.util.GetLatAndLngByBaidu.exec;

@Controller
@RequestMapping(value = "/eChartsData")
public class VisController {

    private static final Logger log = LoggerFactory.getLogger(VisController.class);

    @Resource
    private eChartsDataService eChartsDataService;

    /**
     *
     * echarts查询岗位地区信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/getEchartsData_Place_count")
    @ResponseBody
    public JsonMsg ShowAllPlaceInfo(HttpServletRequest request)
    {
        try {
            log.info("echarts查询地区信息");
            List<eChartsData> result_list= eChartsDataService.ShowPlaceTotal();
            return JsonMsg.success().addInfo("result_list",result_list);
        }
        catch (Exception e)
        {
            return JsonMsg.fail().addInfo("log_error",e+"查询失败");
        }

    }



    /**
     *
     * echarts查询宣讲-学校会信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/getEchartsData_Employment_count")
    @ResponseBody
    public JsonMsg ShowAllEmploymentInfo(HttpServletRequest request)
    {
        try {
            log.info("echarts查询宣讲会信息");
            List<eChartsData> result_list= eChartsDataService.ShowEmploymentTotal();
            return JsonMsg.success().addInfo("result_list",result_list);
        }
        catch (Exception e)
        {
            return JsonMsg.fail().addInfo("log_error",e+"查询失败");
        }


    }

    /**
     *
     * echarts查询宣讲会时间-数目信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/getEchartsData_MeetDay_count")
    @ResponseBody
    public JsonMsg ShowAllMeetDay_Info(HttpServletRequest request)
    {
        try {
            log.info("echarts查询宣讲会时间-数目信息");
            List<eChartsData> result_list= eChartsDataService.ShowMeetDayTotal();

            return JsonMsg.success().addInfo("result_list",result_list);
        }
        catch (Exception e)
        {
            return JsonMsg.fail().addInfo("log_error",e+"查询失败");
        }

    }


    /**
     *
     * MAptupian
     * @param request
     * @return
     */
    @RequestMapping(value = "/getEchartsMap")
    @ResponseBody
    public JsonMsg getEchartsMap(HttpServletRequest request)
    {
        try {
            log.info("echartsMap");
            List<Mapp> result_list= eChartsDataService.ShowMap();
            List<Mapp> Lis=exec(result_list);
            return JsonMsg.success().addInfo("result_list",Lis);
        }
        catch (Exception e)
        {
            return JsonMsg.fail().addInfo("log_error",e+"查询失败");
        }

    }




    /**
     *
     * 轮播图信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/getPhoto")
    @ResponseBody
    public JsonMsg ShowPhoto(HttpServletRequest request)
    {
        try {
            log.info("轮播图信息");
            List<Photo> result_list= eChartsDataService.ShowPhoto();
            return JsonMsg.success().addInfo("result_list",result_list);
        }
        catch (Exception e)
        {
            return JsonMsg.fail().addInfo("log_error",e+"查询失败");
        }


    }
}
