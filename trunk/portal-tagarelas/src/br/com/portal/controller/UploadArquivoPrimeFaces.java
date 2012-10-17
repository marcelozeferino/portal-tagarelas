package br.com.portal.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

@ManagedBean(name="uploadArquivoPrimeFaces")
public class UploadArquivoPrimeFaces {

	
	 private UploadedFile file;  
	  
	    public UploadedFile getFile() {  
	        return file;  
	    }  
	   
	    public void setFile(UploadedFile file) {  
	        this.file = file;   
	    }  
	    

	  
	    public void upload() {  
	        if(file != null) {  
	        	InputStream in = new ByteArrayInputStream(file.getContents()); 
	        
	            FacesMessage msg = new FacesMessage("Teste", file.getFileName() + " is uploaded.");  
	            FacesContext.getCurrentInstance().addMessage(null, msg);  
	            
	            
	            
	            
	            
	        }  
	    }  
}
