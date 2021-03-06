/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.musicstore.util;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hl.murcia222
 */
public class MailManager {

    
    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;
    

    public static void generateAndSendEmail(String message, String emailRecipient, String subject) {

        try {
            
            //loadProperties();

            //Step1		
            Logger.getGlobal().log(Level.SEVERE, "\n 1st ===> setup Mail Server Properties..");
            mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", "587");
            mailServerProperties.put("mail.smtp.auth", "true");
            mailServerProperties.put("mail.smtp.starttls.enable", "true");
            Logger.getGlobal().log(Level.SEVERE, "Mail Server Properties have been setup successfully..");

            //Step2		
            Logger.getGlobal().log(Level.SEVERE, "\n\n 2nd ===> get Mail Session..");
            getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            generateMailMessage = new MimeMessage(getMailSession);
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailRecipient));
            generateMailMessage.setSubject(subject);
            String emailBody = message;
            generateMailMessage.setContent(emailBody, "text/html");
            Logger.getGlobal().log(Level.SEVERE, "Mail Session has been created successfully..");

            //Step3		
            Logger.getGlobal().log(Level.SEVERE, "\n\n 3rd ===> Get Session and Send mail");
            Transport transport = getMailSession.getTransport("smtp");

            // Enter your correct gmail UserID and Password (XXXApp Shah@gmail.com)
            transport.connect("smtp.gmail.com", "noreply.dummy10@gmail.com", "atalanta85");
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();
            
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
    private static void loadProperties() {

        Properties datos = new Properties( );
        String data="";
        try
        {
                FileInputStream input = new FileInputStream( "src/main/resources/admin_email.properties" );
                datos.load( input );
                //adminEmail=datos.getProperty("admin.email");
        }
        catch( Exception e )
        {
                Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
        }

    }

}
