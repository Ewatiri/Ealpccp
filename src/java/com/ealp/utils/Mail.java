/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ealp.utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author eva
 */
public class Mail {

    public boolean sendMail(String to, String host, String subject, String body) {
        boolean res = false;
        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("hpevakp@gmail.com"));
               message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(body,"text/html");

            Transport.send(message);
            res = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

}
