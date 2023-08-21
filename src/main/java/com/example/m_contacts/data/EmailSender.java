package com.example.m_contacts.data;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailSender {

    public static void SendEmail(String message, String email1) throws EmailException {
        // Email configuration
        String smtpHost = "smtp.office365.com";
        int smtpPort = 587;
        String username = "your email here";
        String password = "your paasword";

        try {
            // Create the email message
            Email email = new SimpleEmail();
            email.setHostName(smtpHost);
            email.setSmtpPort(smtpPort);
            email.setAuthenticator(new DefaultAuthenticator(username, password));
            email.setStartTLSEnabled(true);
            email.setFrom(username);
            email.setSubject("password recovery");
            email.setMsg(message);
            email.addTo(email1);
            System.setProperty("https.protocols", "TLSv1.2"); // Enable TLS v1.2
            System.setProperty("https.cipherSuites", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256"); // Set an appropriate cipher suite

            // Send the email
            email.send();

            System.out.println("Email sent successfully.");
        } catch (Exception e) {
            throw  new EmailException();
        }
    }
}

