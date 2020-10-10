package com.bookstore.utils;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Properties;

public class SendJMail {

    public static boolean sendMail(String email,String emailMsg){
        String from = "gn000007@163.com";
        String to = email;
        final String username = "gn000007@163.com";
        final String passwrod = "zp845896787";

        Properties properties = System.getProperties();
        properties.setProperty("mail.stmp.host","stmp.163.com");
        properties.setProperty("mail.stmp.auth","true");
        properties.setProperty("mail.transport.protocol","smtp");
        Session session = Session.getInstance(properties);
        session.setDebug(true);


        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setSubject("验证码激活");
            message.setContent((emailMsg),"text/html;charset=utf-8");
            Transport transport = session.getTransport();
            transport.connect("smtp.163.com",25,username,passwrod);
            transport.sendMessage(message,new Address[]{new InternetAddress(to)});
            transport.close();
            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

    }
}
