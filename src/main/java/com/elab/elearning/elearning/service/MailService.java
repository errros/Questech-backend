package com.elab.elearning.elearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;



@Service
public class MailService {
    @Autowired
    JavaMailSender mailSender;

    @Autowired
    Environment env;


    private static final String MAILBODY = "Welcome dear %s !\n you can find below your authentication credentials : \nemail: %s \npassword: %s";
    private static final String MAILSUBJECT = "ESI SBA E-LEARNING PLATFORM : login credentials";

    public void sendEmailtoUser(String toEmail, String password, String role) {


        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setTo(toEmail);
        mail.setSubject(MAILSUBJECT);
        mail.setText(String.format(MAILBODY,role.toLowerCase(Locale.ROOT),toEmail,password));

       mailSender.send(mail);


    }
}