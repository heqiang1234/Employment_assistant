package com.human.ExtraPack;


import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class email {
    private static String host = "smtp.qq.com";
    private static String userName = "1401651730@qq.com";
    private static String password = "wajvbqjhgyrpbadj";
    public static void init(String host,String userNamme,String password){
        email.host = host;
        email.userName = userNamme;
        email.password = password;
    }
    public static void sendMail(String receiveEmail,String subject,String emailContent)
            throws AddressException, MessagingException {
        // 1.创建一个程序与邮件服务器会话对象 Session

        Properties props = new Properties();
        //设置第三方服务器所要设置的基本配置
        //邮箱所应用的协议
        props.setProperty("mail.transport.protocol", "smtp");
        //主机位置
        props.setProperty("mail.host", host);
        //更改端口
        props.setProperty("mail.port", "465");
        props.setProperty("mail.properties.mail.smtp.socketFactory.port", "465");
        // 邮件ssl验证
        //props.setProperty("mail.smtps.ssl.enable", "true");
        //是否要进行授权
        props.setProperty("mail.smtp.auth", "true");// 指定验证为true


        //获取到目标邮箱返回的session
        Session session = Session.getInstance(props);

        // 2.创建一个Message，它相当于是邮件内容
        MimeMessage message = new MimeMessage(session);

        message.addRecipient(MimeMessage.RecipientType.CC, new InternetAddress(userName)); // 设置发送方式与接收者
        message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveEmail));
        message.setFrom(new InternetAddress(userName)); // 设置发送者



        //设置主题
        message.setSubject(subject);
        //System.out.println(emailContent);
        //message.setText(emailContent);

        //设置正文内容
        message.setContent(emailContent, "text/html;charset=utf-8");

        // 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();
        transport.connect(userName,password);
        // 发送消息
        transport.sendMessage(message,message.getAllRecipients());
        transport.close();
    }
    public static void main(String args[]) throws MessagingException {
        sendMail("snowsinjuly@qq.com","test","nihao");

    }

}

