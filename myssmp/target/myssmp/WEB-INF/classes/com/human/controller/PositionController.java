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
     *
     * 查询数据数量
     * @param request
     * @return
     */
    @RequestMapping(value = "/SelectCount")
    @ResponseBody
    public JsonMsg SelectCount(HttpServletRequest request)
    {
        try {
            log.info("查询数据数量");
            String S_ID = request.getParameter("S_ID");
            String S_Name = request.getParameter("S_Name");
            int count_s = positionService.selectCountBytype(S_ID, S_Name);
            return JsonMsg.success().addInfo("count_s", count_s);
        }
        catch ( Exception e)
        {
        return JsonMsg.fail().addInfo("FAIL", "出现错误");}
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
        try {
            log.info("根据岗宣讲会ID查询岗位");
            String Career_Id_Name = request.getParameter("Career_Id_Name");
            String currentPage = request.getParameter("CurrentPage");
            String pageSize = request.getParameter("PageSize");
            int cur = Integer.parseInt(currentPage);
            int pag = Integer.parseInt(pageSize);
            PageBean<Position> lists = positionService.selectPositionIdToCareerd(cur, pag, Career_Id_Name);
            return JsonMsg.success().addInfo("List", lists);
        }
        catch (Exception e)
        {
            return JsonMsg.fail().addInfo("FAIL", "出现错误");
        }
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
        try {
            log.info("展示粗略岗位信息");
            String currentPage = request.getParameter("CurrentPage");
            String pageSize = request.getParameter("PageSize");
            String S_ID = request.getParameter("S_ID");
            int cur = Integer.parseInt(currentPage);
            int pag = Integer.parseInt(pageSize);
            PageBean pageBean = new PageBean();
            pageBean = positionService.selectAllPosition(cur, pag, S_ID);
            return JsonMsg.success().addInfo("pageBean_List", pageBean);
        }
        catch (Exception E)
        {
            return JsonMsg.fail().addInfo("FAIL", "出现错误");
        }
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
        try {
            log.info("根据岗位ID查询详细数据");
            String position_Id = request.getParameter("Position_Id");
            List<Position> lists = positionService.selectPositionById(position_Id);
            return JsonMsg.success().addInfo("List", lists);
        }
        catch (Exception e)
        {
            return JsonMsg.fail().addInfo("log_error","查询失败");
        }
    }

    @RequestMapping(value = "/SearchPosition")
    @ResponseBody
    public  JsonMsg SearchEmploymentByType(HttpServletRequest request, Model model){
        try {
            log.info("根据类型查询查询岗位信息");
            String currentPage = request.getParameter("CurrentPage");
            String pageSize = request.getParameter("PageSize");
            String Search_Id = request.getParameter("Search_Id");
            String Search_Name = request.getParameter("Search_Name");
            int cur = Integer.parseInt(currentPage);
            int pag = Integer.parseInt(pageSize);
            PageBean pageBean=new PageBean();
            if(Search_Id=="position_name"||Search_Id.equals("position_name"))
            {
                log.info("根据岗位名称查询岗位信息");
                pageBean=positionService.selectPositionByType(cur,pag,Search_Id,Search_Name);
                return JsonMsg.success().addInfo("pagebean_position_name",pageBean);
            }
            if(Search_Id=="workPlace"||Search_Id.equals("workPlace"))
            {
                log.info("根据工作地点查询岗位信息");
                pageBean=positionService.selectPositionByType(cur,pag,Search_Id,Search_Name);
                return JsonMsg.success().addInfo("pagebean_workPlace",pageBean);
            }
            if(Search_Id=="Professional"||Search_Id.equals("Professional"))
            {
                log.info("根据专业查询岗位信息");
                pageBean=positionService.selectPositionByType(cur,pag,Search_Id,Search_Name);
                return JsonMsg.success().addInfo("pagebean_Professional",pageBean);
            }
            pageBean=positionService.selectPositionByType(cur,pag,Search_Id,Search_Name);
            return JsonMsg.success().addInfo("pagebean",pageBean);
        }
        catch (Exception e)
        {
            return JsonMsg.fail().addInfo("log_error","查询失败");
        }
    }

}
