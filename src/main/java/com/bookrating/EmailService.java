package com.bookrating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
//@Component
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(List<String> to, String subject, String text) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("bookrating2023@gmail.com");
        message.setTo(to.toArray(new String[to.size()]));
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendEmailSignal(String from,  String text) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo("bookrating2023@gmail.com");
        message.setSubject("Signaler un commentaire inapproprié");
        message.setText(text);
        mailSender.send(message);
    }

    public void setMailSender(JavaMailSenderImpl mailSender) {
    }
}
