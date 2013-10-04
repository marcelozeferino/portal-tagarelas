package br.com.portal.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.icefaces.ace.component.fileentry.FileEntry;
import org.icefaces.ace.component.fileentry.FileEntryEvent;
import org.icefaces.ace.component.fileentry.FileEntryResults;
//import org.primefaces.model.UploadedFile;

import br.com.portal.dao.UsuarioDAOImpl;
import br.com.portal.model.Usuario;


@ManagedBean
@RequestScoped
public class CadastroUsuarioBean implements Serializable {

	private String nome;
	private String sobreNome;
	private String email;
	private String confirmaEmail;
	private String senha;
	private String mensagem;
	
	  private List<String> fileData;
	
	  
	  
	 public List<String> getFileData() {
		return fileData;
	}

	public void setFileData(List<String> fileData) {
		this.fileData = fileData;
	}
//	private UploadedFile file;  
//	  
//	    public UploadedFile getFile() {  
//	        return file;  
//	    }  
//	   
//	    public void setFile(UploadedFile file) {  
//	        this.file = file;   
//	    }  
//	    

	  
	  
	
	
	public void cadastrarUsuario(FileEntryEvent e)throws IOException{
		System.out.println("estou aqui no cdastro de usuario: " + nome);
		
		
		 FileEntry fe = (FileEntry)e.getComponent();
	        FileEntryResults results = fe.getResults();
	        File parent = null;
	        byte[] bytes = null;
	     
	        fileData = new ArrayList();

	        System.out.println(getNome());
	    //get data About File
	    
	        for (FileEntryResults.FileInfo i : results.getFiles()) 
	        {
	            fileData.add("File Name: " + i.getFileName());
	            	
	            if (i.isSaved()) {
	               // fileData.add("File Size: " + i.getSize() + " bytes");

	                File file = i.getFile();
//	                FileInputStream fin = new FileInputStream(file);
//	                FileChannel ch = fin.getChannel();
//	                int size = (int) ch.size();
//	                MappedByteBuffer buf = ch.map(MapMode.READ_ONLY, 0, size);
//	                bytes = new byte[size];
	                
	                InputStream is = null;  
	                    is = new FileInputStream(file);  
	                    bytes = new byte[is.available()];  
	                    is.read(bytes);  
	                    is.close();  
	                if (file != null) {
	                    parent = file.getParentFile();
	                }
	            } else {
	                fileData.add("File was not saved because: " +
	                    i.getStatus().getFacesMessage(
	                        FacesContext.getCurrentInstance(),
	                        fe, i).getSummary());
	            }
	        }

	        if (parent != null) {
	            long dirSize = 0;
	            int fileCount = 0;
	            for (File file : parent.listFiles()) {
	                fileCount++;
	                dirSize += file.length();
	            }
	            //fileData.add("Total Files In Upload Directory: " + fileCount);
	            //fileData.add("Total Size of Files In Directory: " + dirSize + " bytes");
	        }
		
		
		
		
//		 if(file != null) {  
//	        	//InputStream in = new ByteArrayInputStream(file.getContents()); 
//	            FacesMessage msg = new FacesMessage("Teste", file.getFileName() + " is uploaded.");  
//	            FacesContext.getCurrentInstance().addMessage(null, msg);  
//	        }  
		
		if(senha.equals(confirmaEmail)){
			Usuario usuario = new Usuario();
			usuario.setEmail(email);
			usuario.setNomeUsuario(nome);
			usuario.setSenha(senha);
			usuario.setSobreNome(sobreNome);
			//usuario.setFoto(bytes);
			usuario.setFoto(bytes);
			
			UsuarioDAOImpl dao = new UsuarioDAOImpl();
			dao.save(usuario);
			setMensagem("Usuário cadastrado com sucesso.");
			setEmail("");
			setNome("");
			setSenha("");
			setSobreNome("");
			setConfirmaEmail("");
			
		}else{
			setMensagem("O campo email e confirma Email são diferentes.");
		}
	}
	
	public void verificaSenhaUsuario() {
		UsuarioDAOImpl dao = new UsuarioDAOImpl();
		Usuario usu = dao.verificaSenhaUsuario(email);
		
		if(usu != null){
			setMensagem("Sua senha é: " + usu.getSenha());
		}else{
			setMensagem("Não existe usuário cadastrado com esse email");
		}
		
	}
	
	public void sendEmail() throws EmailException {
	    
		   SimpleEmail email = new SimpleEmail();
		   //Utilize o hostname do seu provedor de email
		   System.out.println("alterando hostname...");
		   email.setHostName("smtps.uol.com.br");
		   //Quando a porta utilizada não é a padrão (gmail = 465)
		   email.setSmtpPort(465);
		   //Adicione os destinatários
		   email.addTo("marcelo.estruc@cpmbraxis.com", "Marcelo");
		   //Configure o seu email do qual enviará
		   email.setFrom("marceloestruc@uol.com.br", "Marcelo Estruc");
		   //Adicione um assunto
		   email.setSubject("Test message");
		   //Adicione a mensagem do email
		   email.setMsg("This is a simple test of commons-email");
		   //Para autenticar no servidor é necessário chamar os dois métodos abaixo
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
