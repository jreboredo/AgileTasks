package Elementos.Ing.AgileTasks.modelo;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
    //set host, message, from and to
    public String SendTo;
    public String from = "InfoAgileTasks@gmail.com";
    public String host = "smtp.gmail.com";
    Properties properties = System.getProperties();

    public Session settingMail(String to) {
        //set mail server
        SendTo = to;
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        //get Session
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, "SuperPancho");
                }
        });
        return session;
    }

    public String sendingEmail(String to, String messageString, String subjectMail) {
        Session session = this.settingMail(to);
        try {
            //Create an email message body
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subjectMail);
            message.setText(messageString);

            //Send message
            Transport.send(message);
            return "Sent message Successfully...";
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return "Something went wrong :(";
        }

    }
}
