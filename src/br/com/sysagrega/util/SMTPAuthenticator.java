package br.com.sysagrega.util;

import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends javax.mail.Authenticator {
	 
	private static final String SMTP_AUTH_USER = "integra@agregaconsultores.com.br";
    private static final String SMTP_AUTH_PWD = "Integra@2018sis";
    
    public PasswordAuthentication getPasswordAuthentication() {
        String username = SMTP_AUTH_USER;
        String password = SMTP_AUTH_PWD;
        return new PasswordAuthentication(username, password);
    }
}
	
