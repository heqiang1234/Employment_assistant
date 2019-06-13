package com.human.Quartz_Email;

import com.human.model.Employment;
import com.human.model.Position;
import com.human.model.User;
import com.human.service.EmploymentService;
import com.human.service.PositionService;
import com.human.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SendMailScheduler {
    private static final Logger LOG = LoggerFactory.getLogger(SendMailScheduler.class);
    @Autowired
    private UserService userService;
    @Autowired
    private EmploymentService employmentService;
    @Autowired
    private PositionService positionService;
    public void work() {
//        try {
            List<User> userList = userService.getAllUser();
            for (User u : userList) {
                try{
                if (u.getUser_Status().equals("true") || u.getUser_Status().length() > 0) {
                    if (u.getUser_Email() == null || u.getUser_Email().trim().length() == 0) {
                        LOG.info("邮箱为空！");
                    } else {
                        List<Position> positionsList = positionService.selectPositionToEmail(u.getUser_IntentionalPost(), u.getUser_Major());
                        LOG.info("========   " + u.getUser_IntentionalPost() + "            " + u.getUser_Major());

                        List<Position> positionsList_em = positionService.selectPositionById(positionsList.get(0).getPositionID() + "");

                        List<Employment> employmentList = employmentService.selectEmploymentById(positionsList_em.get(0).getCareer_talk_id());

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        LOG.debug(sdf.format(new Date()));
                        SendMail sendmail = new SendMail();
                        //设置发件地址和收件地址及主题
                        sendmail.setAddress("1401651730@qq.com", u.getUser_Email().toString(), "宣讲会信息更新通知");
                        //设置附件
                        sendmail.setAttachmentPath(new String[]{employmentList.get(0).getCompany().getCompany_logo()});
                        //设置html内容
                        StringBuffer content = new StringBuffer();
                       content.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">")
                       .append("<html>")
                        .append("<head>")
                         .append("<meta charset=\"utf-8\" />")
                       .append("<title>通知函</title>")
                       .append("<script src=\"https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js\"></script>")
		              .append("<style>")
                       .append("	* {")
                          .append("      border: 0;")
                             .append("    margin: 0;")
                              .append("     }")
                               .append("	.out {")
                                   .append("   display: block;")
                                     .append("    width: 1000px;")
                                           .append("   margin: auto;")
                                             .append("  }")
                                                .append("	.out .outleft {")
                                            .append("   display: block;")
                                              .append(" width: 748px;")
                                                .append("  float: left;")
                                                    .append("  border-right: solid 2px #EEEEEE;")
                                               .append("  }")
                                                  .append("	.out .outright{")
                                                   .append("   float: left;")
                                                     .append("  width: 250px;")
                                                       .append("      }")
                                                      .append("	.heard1 {")
                               .append("     margin-top: 10px;")
                               .append("    margin-bottom: 15px;")
                                .append("     width: 100%;")
                                 .append("    display: inline-block;")
                                .append("   }")
                                   .append("	.content1 {")
                                     .append("        display: inline-block;")
                                        .append("    }")
                                          .append("	.heard1 .left {")
                                            .append("   float: left;")
                                               .append("     height: 30px;")
                                                 .append("     width: 5px;")
                                                   .append("    background-color: #1D629C;")
                                                        .append("   border-left-style: solid;")
                                                          .append("  }")
                .append(".heard1 .title {")
                 .append("      float: left;")
                 .append(" margin-left: 10px;")
                    .append(" line-height: 30px;")
                  .append("font-family: \"微软雅黑\";")
                     .append(" font-size: 21px;")
                       .append("  }")
                          .append(".content1 ul li {")
                             .append("display: block;")
                              .append("margin-top: 5px;")
                              .append(" margin-bottom: 10px;")
                                   .append(" font-family: \"华文新魏\";")
                               .append("font-size: 16px;")
                               .append(" }")
                            .append(".content2 ul li {")
                            .append("    display: inline-block;")
                          .append("  margin-top: 5px;")
                              .append("  margin-bottom: 10px;")
                               .append("  height: 60px;")
                              .append("  width: 650px;")
                             .append("  border-bottom: solid 2px #EEEEEE;")
                            .append(" }")
                           .append(".content2 .top {")
                          .append("   display: inline-block;")
                          .append("    font-family: \"华文新魏\";")
                             .append("      font-size: 16px;")
                               .append("  }")
                                .append(".content2 .top .left {")
                                  .append("    float: left;")
                                     .append("     color: #1D629C;")
                                         .append("     }")
                                            .append(".content2 .top .center{")
                                  .append("     float: left;")
                                  .append("    margin-left: 180px;")
                               .append("  color: #FFA6A5;")
                              .append("  }")
                               .append(".content2 .top .right{")
                                 .append("     float: left;")
                              .append("    margin-left: 185px;")
                                .append("   }")
                                .append(".content2 .bottom{")
                             .append("   }")
                             .append(".content2 .bottom .left{")
                            .append("    float: left;")
                               .append("  width: 500px;")
                                .append("  height: 20px;")
                               .append("  overflow: hidden;")
                            .append("   }")
                              .append(".content2 .bottom .right{")
                             .append("   float: left;")
                               .append("  margin-left: 40px;")
                               .append("  width: 100px;")
                            .append(" height: 20px;")
                               .append("  overflow: hidden;")
                                .append(" }")
                                  .append(".outright .logo{")
                              .append("   width: 250px;")
                                .append("    }")
                                  .append(".outright .logo img{")
                                    .append("   margin-left: 75px;")
                                 .append("  margin-top: 30px;")
                                 .append("   display: inline-block;")
                                     .append("   width: 100px;")
                                       .append("   height: 50px;")
                                     .append("  }")
                                 .append(".outright .company_name{")
                               .append("   color: #1D629C;")
                             .append(" width: 100%;")
                              .append("  margin-top: 20px;")
                               .append("  margin-bottom: 20px;")
                                 .append(" text-align: center;")
                                    .append("}")
                                      .append(".outright .company ul li{")
                                      .append("  margin-top: 3px;")
                                    .append(" display: inline-block;")
                                  .append("  margin-left: -30px;")
                                .append(" }")
                              .append(".outright .map{")
                           .append("  margin-top: 20px;")
                            .append("   height: 250px;")
                          .append("    width: 100%;")
                        .append(" }")
                       .append(".outright .map img{")
                         .append("      display: inline-block;")
                       .append("    margin-left: 5px;")
                         .append("     height: 240px;")
                           .append("     width: 240px;")
                           .append("   }")
                            .append("</style>")
                           .append("</head>")
                               .append("	<body>")
                                   .append("<div class=\"out\">")
                               .append("<p>尊贵的   "+u.getUser_Name()+"    先生/女士,根据您的需求，我们推送最新的宣讲会信息</p>")
                                    .append("	<div class=\"outleft\">")
                                     .append("	<div>")
                                   .append("	<div class=\"heard1\">")

                                    .append("	<div class=\"left\"></div>")


                                  .append("	<div class=\"title\">宣讲会信息</div>")
                                .append("</div>")
                               .append("<div class=\"content1\">")
                               .append("	<ul>")
                               .append("<li> 宣讲单位："+employmentList.get(0).getMeet_name()+" </li>")
                                .append("<li> 宣讲时间: "+employmentList.get(0).getMeet_day()+" "+employmentList.get(0).getMeet_time()+"</li>")
                                 .append("<li> 宣讲学校："+employmentList.get(0).getCareer_address()+"</li>")
                                .append("<li> 宣讲地点："+employmentList.get(0).getAddress()+"</li>")
                                   .append("	</ul>")
                                      .append("</div>")
                                        .append("</div>")
                                            .append("<div>")
                                                    .append("<div class=\"heard1\">")
        .append("<div class=\"left\"></div>")
                .append("	<div class=\"title\">单位简介</div>")
                               .append("</div>")
                               .append("<div class=\"content1\">")
                               .append("	<ul>")
                               .append("<li> 公司类型："+employmentList.get(0).getCompany().getCompanyType()+" </li>")
                               .append("<li> 公司地址："+employmentList.get(0).getCompany().getCompany_address()+" </li>")
                               .append("<li> 公司领域："+employmentList.get(0).getCompany().getCompanyTrade()+" </li>")
                               .append("<p>"+employmentList.get(0).getCompany().getCompanyProfile()+"</p>")
                               .append("	</ul>")
                               .append("</div>")
                               .append("</div>")
            .append("</div>")
            .append("<div>")
             .append("</div>")
           .append("</div>")
                .append("<div>")
                   .append("<div class=\"heard1\">")
                     .append("<div class=\"left\"></div>")
                        .append("<div class=\"title\">招聘职位</div>")
                          .append("</div>")
                            .append("<div class=\"content2\">")
                            .append("<ul>")
                               .append("<li>")
                            .append("<div class=\"top\">")
                           .append("<div class=\"left\">"+employmentList.get(0).getPositionList().get(0).getPosition_name()+"</div>")
                         .append("	<div class=\"center\">"+employmentList.get(0).getPositionList().get(0).getPositionWage()+"</div>")
                             .append("<div class=\"right\">"+employmentList.get(0).getPositionList().get(0).getNum()+"</div>")
                                 .append("</div>")
                                  .append("<div class=\"bottom\">")
                                       .append("	<div class=\"left\">")
                               .append("招聘专业："+employmentList.get(0).getPositionList().get(0).getProfessionals()+"")
                                         .append("</div>")
                                         .append("	<div class=\"right\">")
                                      .append("  本科以上")
                                     .append(" </div>")
                                     .append("</div>")
                                    .append("</li>")
                                        .append("<li>")
                                     .append("<div class=\"top\">")
                               .append("<div class=\"left\">"+employmentList.get(0).getPositionList().get(1).getPosition_name()+"</div>")
                      .append("<div class=\"center\">"+employmentList.get(0).getPositionList().get(1).getPositionWage()+"</div>")
                   .append("<div class=\"right\">"+employmentList.get(0).getPositionList().get(1).getNum()+"</div>")
                      .append("</div>")
                           .append("<div class=\"bottom\">")
                        .append("<div class=\"left\">")
              .append("招聘专业："+employmentList.get(0).getPositionList().get(1).getProfessionals()+"")
                     .append(" </div>")
                             .append("<div class=\"right\">")
                             .append("  本科以上")
                                .append("</div>")
                                   .append("	</div>")
                                    .append("</li>")
                                        .append("	</ul>")
                                     .append("	</div>")
                                    .append("	</div>")
                             .append("	</div>")
                                     .append("<div class=\"outright\">")
                                             .append("<div class=\"logo\">")
                          .append("<img src="+employmentList.get(0).getCompany().getCompany_logo()+" />")
				 .append("</div>")
                                .append("<div class=\"company_name\">")
                                        .append(""+employmentList.get(0).getCompany().getCompany_name()+"")
                          .append("<div class=\"company\">")
                         .append("<ul>")
                                        .append("<li>")
                                        .append("    地址："+employmentList.get(0).getCompany().getCompany_address()+"")
                                      .append("   </li>")
                                    .append("</ul>")
                                .append("</div>")
                                        .append("<div class=\"map\">")
                               .append("<a href="+employmentList.get(0).getCompany().getMap_link()+">")
			 .append("<img src="+employmentList.get(0).getCompany().getImage_link()+" />")
				 .append("	</a>")
                    .append("</div>")
                   .append("</div>")
                   .append("	</div>")
                 .append("</body>")
                        .append("<script>")
                                         .append("  if($('.outleft').height()>$('.outright').height()){")
                               .append("  alert($('.outright').height()+$('.map').height())")
                                 .append("$('.outright').css('height',''+$('.outleft').css('height'))")
                             .append(" }else{")
                                .append("  $('.outleft').css('height',$('.outright').css('height'))")
                         .append(" }")
                       .append("</script>")
                         .append("</html>");
                        sendmail.setHtmlContent(content.toString());

                        //调用发送邮件方法
                        int status = sendmail.sendMail("1401651730@qq.com", "wajvbqjhgyrpbadj", "smtp.qq.com");
                        if (status == 1) {
                            LOG.error("发送失败");
                        } else {
                            LOG.info("发送成功");
                        }
                    }
                }
                }
                catch (Exception ee)
                {
                    LOG.info(ee+"cccc");
                }
            }
//            }catch(Exception e)
//            {
//                LOG.info("出现错误");
//            }


    }
}