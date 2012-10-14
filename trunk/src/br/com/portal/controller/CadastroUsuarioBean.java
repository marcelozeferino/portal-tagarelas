package br.com.portal.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.primefaces.model.UploadedFile;

import br.com.portal.dao.UsuarioDAOImpl;
import br.com.portal.model.Usuario;


@ManagedBean
@RequestScoped
public class CadastroUsuarioBean {

	private String nome;
	private String sobreNome;
	private String email;
	private String confirmaEmail;
	private String senha;
	private String mensagem;
	
	
	 private UploadedFile file;  
	  
	    public UploadedFile getFile() {  
	        return file;  
	    }  
	   
	    public void setFile(UploadedFile file) {  
	        this.file = file;   
	    }  
	    

	  
	  
	
	
	public void cadastrarUsuario(){
		System.out.println("estou aqui no cdastro de usuario: " + nome);
		
		
		 if(file != null) {  
	        	//InputStream in = new ByteArrayInputStream(file.getContents()); 
	            FacesMessage msg = new FacesMessage("Teste", file.getFileName() + " is uploaded.");  
	            FacesContext.getCurrentInstance().addMessage(null, msg);  
	        }  
		
		if(email.equals(confirmaEmail)){
			Usuario usuario = new Usuario();
			usuario.setEmail(email);
			usuario.setNomeUsuario(nome);
			usuario.setSenha(senha);
			usuario.setSobreNome(sobreNome);
			usuario.setFoto(file.getContents());
			UsuarioDAOImpl dao = new UsuarioDAOImpl();
			dao.save(usuario);
			setMensagem("Usu�rio cadastrado com sucesso.");
			
		}else{
			setMensagem("O campo email e confirma Email s�o diferentes.");
		}
	}
	
	public void verificaSenhaUsuario() {
		UsuarioDAOImpl dao = new UsuarioDAOImpl();
		Usuario usu = dao.verificaSenhaUsuario(email);
		
		if(usu != null){
			setMensagem("Sua senha �: " + usu.getSenha());
		}else{
			setMensagem("N�o existe usu�rio cadastrado com esse email");
		}
		
	}
	
	public void sendEmail() throws EmailException {
	    
		   SimpleEmail email = new SimpleEmail();
		   //Utilize o hostname do seu provedor de email
		   System.out.println("alterando hostname...");
		   email.setHostName("smtps.uol.com.br");
		   //Quando a porta utilizada n�o � a padr�o (gmail = 465)
		   email.setSmtpPort(465);
		   //Adicione os destinat�rios
		   email.addTo("marcelo.estruc@cpmbraxis.com", "Marcelo");
		   //Configure o seu email do qual enviar�
		   email.setFrom("marceloestruc@uol.com.br", "Marcelo Estruc");
		   //Adicione um assunto
		   email.setSubject("Test message");
		   //Adicione a mensagem do email
		   email.setMsg("This is a simple test of commons-email");
		   //Para autenticar no servidor � necess�rio chamar os dois m�todos abaixo
		   System.out.println("autenticando...");
		   email.setSSL(true);
		   email.setAuthentication("username", "senha");
		   System.out.println("enviando...");
		   email.send();
		   System.out.println("Email enviado!");
		}
	
	
	public static void main(String[] args) {
		CadastroUsuarioBean b = new CadastroUsuarioBean();
		try {
			b.sendEmail();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobreNome() {
		return sobreNome;
	}
	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getConfirmaEmail() {
		return confirmaEmail;
	}
	public void setConfirmaEmail(String confirmaEmail) {
		this.confirmaEmail = confirmaEmail;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getMensagem() {
		return mensagem;
	}
	
	
}
