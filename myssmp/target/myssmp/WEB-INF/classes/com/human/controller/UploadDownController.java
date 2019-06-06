package com.human.controller;


import com.human.service.UserService;
import com.human.util.JsonMsg;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping(value = "/Upload")
public class UploadDownController {

    @Autowired
    private UserService userService;

    /**
     * 照片文件上传
     * @param UserImg
     * @param request
     * @return
     */
    @RequestMapping(value="/UploadImg")
    @ResponseBody
    public JsonMsg uploadImg(@RequestParam(value = "UserImg") MultipartFile UserImg, HttpServletRequest request) {
       try {
           if (UserImg.isEmpty()) {
               return JsonMsg.fail().addInfo("fail", "文件为空！");
           }
           //获取文件在服务器的储存位置
           String path = request.getSession().getServletContext().getRealPath("/upload");
           File filePath = new File(path);
          // System.out.println("文件的保存路径：" + path);
           if (!filePath.exists() && !filePath.isDirectory()) {
            //   System.out.println("目录不存在，创建目录:" + filePath);
               filePath.mkdir();
           }

           //获取原始文件名称(包含格式)
           String originalFileName = UserImg.getOriginalFilename();
         //  System.out.println("原始文件名称：" + originalFileName);

           //获取文件类型，以最后一个`.`为标识
           String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
         //  System.out.println("文件类型：" + type);
           //获取文件名称（不包含格式）
           String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

           //设置文件新名称: 当前时间+文件名称（不包含格式）
           Date d = new Date();
           SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
           String date = sdf.format(d);
           String fileName = date + name + "." + type;
         //  System.out.println("新文件名称：" + fileName);

           //在指定路径下创建一个文件
           File targetFile = new File(path, fileName);
           //将文件保存到服务器指定位置
           try {
               UserImg.transferTo(targetFile);
            //   System.out.println("targetFile   =    " + targetFile);
               System.out.println("上传成功");
//               userService.saveImg("/upload/" + fileName, "HQ");
               //将文件在服务器的存储路径返回
               return JsonMsg.success().addInfo("success", "http://120.79.15.183:8080/myssmp/upload/" + fileName);
           } catch (IOException e) {
               System.out.println("上传失败");
               e.printStackTrace();
               return JsonMsg.fail().addInfo("fail", "上传失败");
           }
       }
       catch (Exception e)
       {
           return JsonMsg.fail().addInfo("fail",  UserImg+"    "+e+"出现错误"+request.toString());
       }
    }

    /**
     * 简历文件上传
     * @param UserResume
     * @param request
     * @return
     */
    @RequestMapping(value="/UploadResume")
    @ResponseBody
    public JsonMsg uploadResume(@RequestParam("UserResume") MultipartFile UserResume, HttpServletRequest request) {
        try {
            if (UserResume.isEmpty()) {
                return JsonMsg.fail().addInfo("fail", "文件为空！");
            }
            //获取文件在服务器的储存位置
            String path = request.getSession().getServletContext().getRealPath("/upload");
            File filePath = new File(path);
            System.out.println("文件的保存路径：" + path);
            if (!filePath.exists() && !filePath.isDirectory()) {
                System.out.println("目录不存在，创建目录:" + filePath);
                filePath.mkdir();
            }

            //获取原始文件名称(包含格式)
            String originalFileName = UserResume.getOriginalFilename();
            System.out.println("原始文件名称：" + originalFileName);

            //获取文件类型，以最后一个`.`为标识
            String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
            System.out.println("文件类型：" + type);
            //获取文件名称（不包含格式）
            String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

            //设置文件新名称: 当前时间+文件名称（不包含格式）
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String date = sdf.format(d);
            String fileName = date + name + "." + type;
            System.out.println("新文件名称：" + fileName);

            //在指定路径下创建一个文件
            File targetFile = new File(path, fileName);
            //将文件保存到服务器指定位置
            try {
                UserResume.transferTo(targetFile);
                System.out.println("上传成功");
                //将文件在服务器的存储路径返回
                return JsonMsg.success().addInfo("success", "http://120.79.15.183:8080/myssmp/upload/" + fileName);
            } catch (IOException e) {
                System.out.println("上传失败");
                e.printStackTrace();
                return JsonMsg.fail().addInfo("fail", fileName + "上传失败");
            }
        }
        catch (Exception e)
        {
            return JsonMsg.fail().addInfo("fail",  e+"出现错误！");
        }
    }
}