package com.bookrating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String fromoMail, String message){

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(fromoMail);
        mailMessage.setTo("kaoutar.lakhal@gmail.com");
        mailMessage.setText(message);
        mailMessage.setSubject("Signaler un commentaire");

        mailSender.send(mailMessage);

    }
}
