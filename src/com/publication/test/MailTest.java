package com.publication.test;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailTest {
	
	
	public static void main(String[] args) {
		Properties properties = System.getProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.user", "publication.ncu@gmail.com"); // User name
        properties.put("mail.smtp.password", "poilkjmnb098"); // password
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("publication.ncu@gmail.com", "poilkjmnb098");
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("publication.ncu@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("ilikeitmyway1998@gmail.com") ); 
            message.setSubject("Subject");
            message.setText("Text");
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
