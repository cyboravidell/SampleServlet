package controller;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

class Mailer {
    public static void send(String from, String password, String to, String sub, String msg) {
        // Get properties object
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Explicitly specify TLS version


        // Get session
        Session session = Session.getDefaultInstance(properties,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });

        // Compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);
            // Send message
            Transport.send(message);
            System.out.println("Message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

public class SendMailSSL {
    public static void main(String[] args) {
        // from, password, to, subject, message
        Mailer.send("cyboravidell1234@gmail.com", "Ravi@1234", "anukritysrivastava@gmail.com", "hello javatpoint", "How r u?");
        // change from, password, and to
    }
}
