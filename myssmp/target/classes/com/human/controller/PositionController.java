package com.human.controller;


import com.human.model.PageBean;
import com.human.model.Position;
import com.human.service.PositionService;
import com.human.util.JsonMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/Position")
public class PositionController {

    private static final Logger log = LoggerFactory.getLogger(PositionController.class);

    @Resource
    private PositionService positionService;


    /**
     * 根据专业查询岗位
     * @param request
     * @return
     */
    @RequestMapping(value = "/SelectByName")
    @ResponseBody
    public JsonMsg SelectByid(HttpServletRequest request)
    {
        log.info("根据专业查询岗位");
        String Search_Id=request.getParameter("Search_Id_Name");
        String currentPage=request.getParameter("CurrentPage");
        String pageSize=request.getParameter("PageSize");
        int cur = Integer.parseInt(currentPage);
        int pag = Integer.parseInt(pageSize);
        PageBean<Position> lists=positionService.selectPositionToTald_Id(cur,pag,Search_Id);
        return JsonMsg.success().addInfo("List",lists);
    }


    /**
     *
     * 查询数据数量
     * @param request
     * @return
     */
    @RequestMapping(value = "/SelectCount")
    @ResponseBody
    public JsonMsg SelectCount(HttpServletRequest request)
    {
        log.info("查询数据数量");
        String S_ID=request.getParameter("S_ID");
        String S_Name=request.getParameter("S_Name");

        int  count_s=positionService.selectCountBytype(S_ID,S_Name);
        return JsonMsg.success().addInfo("count_s",count_s);
    }

    /**
     * 根据岗位ID查询宣讲会
     * @param request
     * @return
     */
    @RequestMapping(value = "/SelectByCareerId")
    @ResponseBody
    public JsonMsg SelectByCareerid(HttpServletRequest request)
    {
        log.info("根据岗宣讲会ID查询岗位");
        String Career_Id_Name=request.getParameter("Career_Id_Name");
        String currentPage=request.getParameter("CurrentPage");
        String pageSize=request.getParameter("PageSize");
        int cur = Integer.parseInt(currentPage);
        int pag = Integer.parseInt(pageSize);
        PageBean<Position> lists=positionService.selectPositionIdToCareerd(cur,pag,Career_Id_Name);
        return JsonMsg.success().addInfo("List",lists);
    }

    /**
     * 展示粗略岗位信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectAllPosition")
    @ResponseBody
    public JsonMsg selectAllPosition(HttpServletRequest request)
    {
        log.info("展示粗略岗位信息");
        String currentPage=request.getParameter("CurrentPage");
        String pageSize=request.getParameter("PageSize");
        int cur = Integer.parseInt(currentPage);
        int pag = Integer.parseInt(pageSize);
        PageBean<Position> lists=positionService.selectAllPosition(cur,pag);
        return JsonMsg.success().addInfo("List",lists);
    }


    /**
     * 展示粗略岗位信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectByPositionId")
    @ResponseBody
    public JsonMsg selectByPositionId(HttpServletRequest request)
    {
        log.info("根据岗位ID查询详细数据");
        String position_Id=request.getParameter("Position_Id");
          List<Position> lists=positionService.selectPositionById(position_Id);
        return JsonMsg.success().addInfo("List",lists);
    }



    @RequestMapping(value = "/SearchPosition")
    @ResponseBody
    public  JsonMsg SearchEmployment(HttpServletRequest request, Model model){
        try {
            log.info("查询岗位信息");
            String currentPage = request.getParameter("CurrentPage");
            String pageSize = request.getParameter("PageSize");
            String Search_Id = request.getParameter("Search_Id");
            String Search_Name = request.getParameter("Search_Name");
            int cur = Integer.parseInt(currentPage);
            int pag = Integer.parseInt(pageSize);
            if(Search_Id!=null||Search_Id.trim().length()!=0) {
                log.info("查询宣讲会信息"+Search_Id);
                PageBean pagebean= positionService.selectPositionByType(cur, pag,Search_Id,Search_Name);
                return JsonMsg.success().addInfo("pagebean_",pagebean);
            }
            PageBean pagebean = positionService.selectAllPosition(cur, pag);
            return JsonMsg.success().addInfo("pagebean",pagebean);
        }
        catch (Exception e)
        {
            return JsonMsg.fail().addInfo("log_error","查询失败");
        }
    }

}
