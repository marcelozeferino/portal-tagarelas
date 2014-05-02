package com.br.portaltagarelas.utils;

import java.util.ArrayList;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

public class EmailUtils {

	private static final String HOSTNAME = "smtp.gmail.com";
	private static final String EMAIL_REMETENTE = "email.teste.desenvolvimento@gmail.com";
	private static final String NOME_REMETENTE = "SISTEMA";
	private static final Integer PORTA_SMTP = 587;
	private static final String LOGIN_AUTENTICACAO_EMAIL = "email.teste.desenvolvimento";
	private static final String SENHA_AUTENTICACAO_EMAIL = "desenvolvimento123@#";
	
	public final String RETORNO_ERRO_ENVIO_EMAIL = "erroEnvio";
	public final String RETORNO_ERRO_INESPERADO = "erroInesperado";
	public final String RETORNO_SUCESSO_ENVIO_EMAIL = "ok";
	
	/** 
     * envia email simples(somente texto)
     * @return String
     * @throws Exception 
     */  
   public String enviaEmailSimples(String emailDestinatario, String nomeDestinatario, String assunto, String mensagem) {  
          
        try {
        	SimpleEmail email = new SimpleEmail();  
            email.setHostName(HOSTNAME); // o servidor SMTP para envio do e-mail  
            email.addTo(emailDestinatario, nomeDestinatario); //destinatário  
            email.setFrom(EMAIL_REMETENTE, NOME_REMETENTE); // remetente  
            email.setSubject(assunto); // assunto do e-mail  
            email.setMsg(mensagem); //conteudo do e-mail  
            email.setAuthentication(LOGIN_AUTENTICACAO_EMAIL, SENHA_AUTENTICACAO_EMAIL);  
            email.setSmtpPort(PORTA_SMTP);  
            email.setSSLOnConnect(true);  
//            email.setStartTLSEnabled(true);
            email.send();
            
            return RETORNO_SUCESSO_ENVIO_EMAIL;
		} catch (Exception e) {
			e.printStackTrace();
			
			if(e instanceof EmailException){
				return RETORNO_ERRO_ENVIO_EMAIL;
			} else {
				return RETORNO_ERRO_INESPERADO;
			}
		}     
    }  
      
      
    /** 
     * envia email com arquivo anexo
     * @return String
     * @throws Exception 
     */  
    public String enviaEmailComAnexo(ArrayList<EmailAttachment> anexos, String emailDestinatario, String nomeDestinatario, String assunto, String mensagem){  
          
        try {
        	// cria o anexo 1.  
        	/*      
        	EmailAttachment anexo1 = new EmailAttachment();  
            anexo1.setPath("teste/teste.txt"); //caminho do arquivo (RAIZ_PROJETO/teste/teste.txt)  
            anexo1.setDisposition(EmailAttachment.ATTACHMENT);  
            anexo1.setDescription("Exemplo de arquivo anexo");  
            anexo1.setName("teste.txt");          
              
            // cria o anexo 2.  
            EmailAttachment anexo2 = new EmailAttachment();  
            anexo2.setPath("teste/teste2.jsp"); //caminho do arquivo (RAIZ_PROJETO/teste/teste2.jsp)  
            anexo2.setDisposition(EmailAttachment.ATTACHMENT);  
            anexo2.setDescription("Exemplo de arquivo anexo");  
            anexo2.setName("teste2.jsp");*/
              
            // configura o email  
            MultiPartEmail email = new MultiPartEmail();  
            email.setHostName(HOSTNAME); // o servidor SMTP para envio do e-mail  
            email.addTo(emailDestinatario, nomeDestinatario); //destinatário  
            email.setFrom(EMAIL_REMETENTE, NOME_REMETENTE); // remetente  
            email.setSubject(assunto); // assunto do e-mail  
            email.setMsg(mensagem); //conteudo do e-mail  
            email.setAuthentication(LOGIN_AUTENTICACAO_EMAIL, SENHA_AUTENTICACAO_EMAIL);  
            email.setSmtpPort(PORTA_SMTP);  
            email.setSSLOnConnect(true);  
//            email.setStartTLSEnabled(true);  
              
            // adiciona arquivo(s) anexo(s)
            for (EmailAttachment anexo : anexos) {
            	email.attach(anexo);
    		} 
            // envia o email  
            return RETORNO_SUCESSO_ENVIO_EMAIL;
		} catch (Exception e) {
			e.printStackTrace();
			
			if(e instanceof EmailException){
				return RETORNO_ERRO_ENVIO_EMAIL;
			} else {
				return RETORNO_ERRO_INESPERADO;
			}
		}
    }  
      
      
    /** 
     * Envia email no formato HTML
     * @return String
     * @throws Exception  
     */  
    public String enviaEmailFormatoHtml(String emailDestinatario, String nomeDestinatario, String assunto, String mensagem, String mensagemHTML){  
        try {
        	HtmlEmail email = new HtmlEmail();  
            
            // configura a mensagem para o formato HTML  
            email.setHtmlMsg(mensagemHTML);  
      
            // configure uma mensagem alternativa caso o servidor não suporte HTML  
            email.setTextMsg(mensagem);  
              
            email.setHostName(HOSTNAME); // o servidor SMTP para envio do e-mail  
            email.addTo(emailDestinatario, nomeDestinatario); //destinatário  
            email.setFrom(EMAIL_REMETENTE, NOME_REMETENTE); // remetente  
            email.setSubject(assunto); // assunto do e-mail  
            email.setMsg(mensagem); //conteudo do e-mail  
            email.setAuthentication(LOGIN_AUTENTICACAO_EMAIL, SENHA_AUTENTICACAO_EMAIL);  
            email.setSmtpPort(PORTA_SMTP);  
            email.setSSLOnConnect(true);  
//            email.setStartTLSEnabled(true); 
            // envia email  
            email.send();
            
            return RETORNO_SUCESSO_ENVIO_EMAIL;
		} catch (Exception e) {
			e.printStackTrace();
			
			if(e instanceof EmailException){
				return RETORNO_ERRO_ENVIO_EMAIL;
			} else {
				return RETORNO_ERRO_INESPERADO;
			}
		} 
    } 
}