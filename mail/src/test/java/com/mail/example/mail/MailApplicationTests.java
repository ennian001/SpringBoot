package com.mail.example.mail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;


@SpringBootTest
class MailApplicationTests {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Test
    void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();
        //简单发送测试
        //标题
        message.setSubject("通知今晚开会");
        //内容
        message.setText("邮箱内容");
        //发送地址
        message.setTo("sunennian@sina.com");
        message.setFrom("sunennian@qq.com");
        javaMailSender.send(message);
    }

    //创建一个复杂邮件发送
    @Test
    void test() throws MessagingException {
        //创建一个复杂的消息邮件
        MimeMessage mimeMessage =
                javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //邮件设置
        helper.setSubject("通知今晚开会");
        //true 标明是html
        helper.setText("<b style='color:red'>今天 7:30 开会</b>",true);
        //发送地址
        helper.setTo("sunennian@sina.com");
        helper.setFrom("sunennian@qq.com");
        //上传文件
        helper.addAttachment("1.jpg",new File("C:\\Users\\Administrator\\Desktop\\timg.jpg"));
        helper.addAttachment("2.jpg",new File("C:\\Users\\Administrator\\Desktop\\timg.jpg"));
    }
}
