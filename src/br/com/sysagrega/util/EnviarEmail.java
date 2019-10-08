package br.com.sysagrega.util;

 import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.sysagrega.util.jsf.FacesUtil;

	public class EnviarEmail{

		public static String REMETENTE_SAD="integra@agregaconsultores.com.br";
		public static String REMETENTE_SAD_SENHA ="Integra@2018sis";
		public static String SERVIDOR_SMTP="email-ssl.com.br"; // servidor SMTP da Agrega
		public static String SERVIDOR_SMTP_PORTA="465";
		
	
	public static void enviarEmail(String assunto, String corpo, String destinatario) {
		if(destinatario != null){
		 Properties props = new Properties();
		  props.put("mail.smtp.host", "email-ssl.com.br");
	      props.put("mail.smtp.auth", true);
		  props.put("mail.smtp.port", "465");
		  props.put("mail.smtp.ssl.enable", true);  
		  Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
		        protected PasswordAuthentication getPasswordAuthentication(){
		        	return new PasswordAuthentication(REMETENTE_SAD, REMETENTE_SAD_SENHA);
		             }
		        });
		            session.setDebug(true);
		            try {
		                  Message message = new MimeMessage(session);
		                  message.setFrom(new InternetAddress(REMETENTE_SAD));
		                  message.setSubject(assunto);//Assunto
		                  message.setText(corpo); // corpo
		                 
		                  if(destinatario != null){ // Destinatario(s)
		                	  Address[] toUser = InternetAddress.parse(destinatario); // teste- GOTO-Mudar email
			                message.setRecipients(Message.RecipientType.TO, toUser);
			              }
		                 		                  
		                  //**Método para enviar a mensagem criada*//*
		                  Transport.send(message);
		                  System.out.println("Enviado com sucesso!");
		                  FacesUtil.addInfoMessage("Os e-mails validos foram enviado com sucesso.");
		             
		            } catch (MessagingException e) {
		  				FacesUtil.addErrorMessage("Não foi possível enviar os e-mails para as partes interessadas, emails iválidos." + e.getMessage());	
		            } 
		      }   
		}
	
	public static boolean validarEmail(String email) {
	    Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"); 
	    Matcher m = p.matcher(email); 
	    
	    if (m.find()){
	      return true;
	    }
	      return false;
	 }
	
	public static void informaEmailsInvalidos (String destinatariosInvalidos){
		FacesUtil.addErrorMessage(destinatariosInvalidos);
	}
	
	}