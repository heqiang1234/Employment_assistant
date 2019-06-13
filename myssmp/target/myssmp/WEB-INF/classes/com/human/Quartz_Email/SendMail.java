package com.human.Quartz_Email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Properties;

public class SendMail {
    private static final Logger LOG = LoggerFactory.getLogger(SendMail.class);
    private String host = "smtp.qq.com"; // smtp服务器
    private String from = ""; // 发件人地址
    private String to = ""; // 收件人地址
    private String[]  attachmentPath = null; // 附件地址
    private String user = ""; // 用户名
    private String pwd = ""; // 密码
    private String subject = ""; // 邮件标题
    private int status = 0; // 发送结果：0-成功，1-失败
    private String htmlContent = ""; //若果发送的是html格式的邮件，这是html内容

    private static final Logger logger = LoggerFactory.getLogger(SendMail.class);
    public void setAddress(String from, String to, String subject) {
        this.from = from;
        this.to = to;
        this.subject = subject;
    }

    public String[] getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String[] attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }
    public int sendMail(String user, String pwd, String host) {
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        logger.info("发送邮件ing");
        Properties prop = new Properties();
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port","25");
        prop.put("mail.transport.protocol", "smtp");

        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getInstance(prop);
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        try {
            //设置发件人地址
            message.setFrom(new InternetAddress(from,"就业助手","UTF-8"));
            //设置收件人地址
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //设置邮件标题
            message.setSubject(subject);
            Multipart mp = new MimeMultipart();
            // 设置邮件的文本内容
            BodyPart bodyPart = new MimeBodyPart();
            //邮件正文（html形式）
            bodyPart.setContent(htmlContent, "text/html; charset=utf-8");
            mp.addBodyPart(bodyPart);

            message.setContent(mp);
//            //设置邮件附件
//            for (String filePath:attachmentPath) {
//                bodyPart= new MimeBodyPart();
//                File attachment = new File(filePath);
//                if (!attachment.exists()) {
//                    LOG.warn("文件:"+attachment+"不存在！");
//                    status = 1;
//                    return status;
//                } else{
//
//                    // 设置要发送附件的文件路径
////                    DataSource source = new FileDataSource(attachment);
////                    bodyPart.setDataHandler(new DataHandler(source));
//
//                    // messageBodyPart.setFileName(filename);
//                    // 处理附件名称中文（附带文件路径）乱码问题
////                    bodyPart.setFileName(MimeUtility.encodeText(attachment));
////                    mp.addBodyPart(bodyPart);
//                    DataSource ds = new FileDataSource(attachment);
//                    bodyPart.setDataHandler(new DataHandler(ds));
////                     添加附件的标题
////                     这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
////                    sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
////                    bodyPart.setFileName("=?GBK?B?"
////                            + enc.encode(attachment.getName().getBytes()) + "?=");
//                   bodyPart.setFileName(MimeUtility.encodeText(attachment.getName()));
//                    mp.addBodyPart(bodyPart);
//                    mp.addBodyPart(bodyPart);
//                    message.setContent(mp);
//                }
//            }
            message.saveChanges();
            //创建Transport对象
            Transport transport = session.getTransport("smtp");
            //连接服务器
            transport.connect(host, user, pwd);
            //发送邮件
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("发送失败！");
        }
        return status;
    }
}
