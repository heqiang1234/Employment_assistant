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
        try {
            List<User> userList = userService.getAllUser();
            for (User u : userList) {
                System.out.println(u.getUser_Email() + "  " + u.getUser_Major() + "  " + u.getUser_IntentionalPost());
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
                    sendmail.setAttachmentPath(new String[]{"D:/ZTest/text.txt", "D:/ZTest/a.jpg"});
                    //设置html内容
                    StringBuffer content = new StringBuffer();
                    content.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">")
                            .append("<html>")
                            .append("<head>")
                            .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">")
                            .append("<title>测试邮件</title>")
                            .append("<body>")
                            .append("<h2>尊敬的xxx,您好,您需要的信息如下：</h2>")
                            .append("<ol>")
                            .append("<li>" + employmentList.get(0).getAddress() + "</li>")
                            .append("<li>" + employmentList.get(0).getCareer_talk_id() + "</li>")
                            .append("<li>" + employmentList.get(0).getCompany_id() + "</li>")
                            .append("<li>" + employmentList.get(0).getCompany() + "</li>")
                            .append("<li>" + employmentList.get(0).getCompany_name() + "</li>")
                            .append("<li>" + employmentList.get(0).getHitCount() + "</li>")
                            .append("<li>" + employmentList.get(0).getLogo() + "</li>")
                            .append("<li>" + employmentList.get(0).getMeet_day() + "</li>")
                            .append("<li>" + employmentList.get(0).getMeet_name() + "</li>")
                            .append("<li>" + employmentList.get(0).getMeet_time() + "</li>")
                            .append("<li>" + employmentList.get(0).getPresentLink() + "</li>")
                            .append("<li>" + employmentList.get(0).getProfessionals() + "</li>")
                            .append("<li>" + employmentList.get(0).getRecruitPost() + "</li>")
                            .append("<li>" + employmentList.get(0).getSchool_id() + "</li>")
                            .append("<li>" + employmentList.get(0).getEmployment_id() + "</li>")
                            .append("<li>" + employmentList.get(0).toString() + "</li>")
                            .append("<li>xxxxx2</li>")
                            .append("<li>xxxxx2</li>")
                            .append("</ol>")
                            .append("</body>")
                            .append("</head>")
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
        }catch (Exception e)
        {
            LOG.info("出现错误");
        }

    }
}