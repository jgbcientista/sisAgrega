package br.com.sysagrega.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class EmailUtil{
	
	
    public static String REMETENTE_SPLAM="joao.brito@capgemini.com";
    public static String REMETENTE_SPLAM_SENHA ="Capgemini2022";
    public static String SERVIDOR_SMTP="relay.cpmbraxis.com"; 
    public static String SERVIDOR_SMTP_PORTA="25";
	
    public static void enviarEmail(String assunto, String corpo, String destinatario) {
        Properties props = new Properties();
        /** Par�metros de conex�o com servidor Gmail */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
 
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication() 
                         {
                               return new PasswordAuthentication("joaoguedesdebrito@gmail.com", "jo17ge40");
                         }
                    });
 
            /** Ativa Debug para sess�o */
            session.setDebug(true);
            try {
 
                  Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress("joaoguedesdebrito@gmail.com")); //Remetente
                //Destinat�rio(s)
                  Address[] toUser = InternetAddress.parse(destinatario);  
 
                  message.setRecipients(Message.RecipientType.TO, toUser);
                  message.setSubject(assunto);//assunto
                  message.setText(corpo); // corpo
                  /**M�todo para enviar a mensagem criada*/
                  Transport.send(message);
                  System.out.println("Enviado com sucesso!");
 
             } catch (MessagingException e) {
                  throw new RuntimeException(e);
            }
      }
    
    
}

