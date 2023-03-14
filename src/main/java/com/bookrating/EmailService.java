package com.bookrating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendMailConfirmation(String toMail, String loginUser){
        String confirmationLink = "http://localhost:8080/BookRating_war_exploded/Confirmation?"+loginUser;
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("kaoutar.lakhal@gmail.com");
        mailMessage.setTo(toMail);
        mailMessage.setText("Bienvenue sur notre site "+ loginUser+" . Veuillez cliquer sur ce lien pour confirmer votre indcription :"+confirmationLink);
        mailMessage.setSubject("Confirmation d'inscription BookRating");

        mailSender.send(mailMessage);

    }
}
