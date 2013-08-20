package br.com.portal.controller;

import java.io.File;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.icefaces.ace.component.fileentry.FileEntry;
import org.icefaces.ace.component.fileentry.FileEntryEvent;
import org.icefaces.ace.component.fileentry.FileEntryResults;

import br.com.portal.dao.AgendaDAOImpl;
import br.com.portal.model.Agenda;
import br.com.portal.model.Usuario;


@ManagedBean(name= FileEntryController.BEAN_NAME)
@CustomScoped(value = "#{window}")
public class FileEntryController implements Serializable {

	@ManagedProperty(value = "#{loginBean}")
	public LoginBean loginBean;	
	
	
	private static final long serialVersionUID = -58705879671097319L;
	public static final String BEAN_NAME = "fileEntry";
    private List<String> fileData;
    private String arrowImage;

    private String mensagem;
    private String assunto;
	private String descricao;
	private String breveDescricao;
	private Date data;
	private Date hora;
	private String day;
	private String time;
	private String idSistema;
	
	private String tempoSessao;
    
	private Date dataHora;
    
	public FileEntryController(){
		this.data = new Date();
		this.hora = new Date();
	}

	public String getTempoSessao() {
		return tempoSessao;
	}

	public void setTempoSessao(String tempoSessao) {
		this.tempoSessao = tempoSessao;
	}

	public Date getDataHora(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date data = new Date();
		try {
			data = sdf.parse(getDay() + " " +getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sdf.format(data));
		return data;
	}
	
	
//	public String getDay(){
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		if(null == day){
//			day = new Date();
//		}
//		return sdf.format(day);
//	}
//	
//	public String getTime(){
//		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//		if(null == time){
//			time = new Date();
//		}
//		return sdf.format(time);
//	}
//	
//	public void setDay(String dia) throws ParseException{
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		this.day = sdf.parse(dia);
//	}
//
//	public void setTime(String time) throws ParseException{
//		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
//		this.day = sdf.parse(time);
//	}
	
	
	
    public LoginBean getLoginBean() {
		return loginBean;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public String getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(String idSistema) {
		this.idSistema = idSistema;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getBreveDescricao() {
		return breveDescricao;
	}

	public void setBreveDescricao(String breveDescricao) {
		this.breveDescricao = breveDescricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getArrowImage() {
		return arrowImage;
	}

	public void setArrowImage(String arrowImage) {
		this.arrowImage = arrowImage;
	}
	

	public void sampleListener(FileEntryEvent e) throws ParseException 
    {
		String[] arq;
		String nomeArquivo = "";
        FileEntry fe = (FileEntry)e.getComponent();
        FileEntryResults results = fe.getResults();
        File parent = null;
        fileData = new ArrayList<String>();
		Agenda agenda = new Agenda();
		 Usuario u = loginBean.getUsuario();
		if(u == null){
			setMensagem("Usuário não está logado");
		}else{
			
			  for (FileEntryResults.FileInfo i : results.getFiles()) 
		        {
		        	nomeArquivo = i.getFile().getPath();
		        	arq = nomeArquivo.split("\\\\PortalTagarelas\\\\");
		        	System.out.println(arq[0]);
		        	System.out.println(arq[1].replace('\\', '/'));
		        	System.out.println(nomeArquivo);
		            //fileData.add("File Name: " + i.getFileName());
		            if (i.isSaved()) {
		                //fileData.add("File Size: " + i.getSize() + " bytes");
		                File file = i.getFile();
		                if (file != null) {
		                    parent = file.getParentFile();
		                   
		                }
		                
		                DateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
		    			Date dataTemp = (Date) dataFormat.parse(day);
		    			
		    			DateFormat horaFormat = new SimpleDateFormat("hh:mm");
		    			Date horaTemp = (Date) horaFormat.parse(time);
		                
		                AgendaDAOImpl dao = new AgendaDAOImpl();
		    			agenda.setAssunto(assunto);
		    			agenda.setData(dataTemp);
		    			agenda.setHora(horaTemp);
		    			agenda.setDataHora(getDataHora());
		    			agenda.setDescricao(descricao);
		    			agenda.setIdSistema(idSistema);
		    			agenda.setBreveDescricao(breveDescricao);
		    			agenda.setIdUsuario(String.valueOf(u.getIdUsuario()));
		    			agenda.setArquivo(arq[1].replace('\\', '/'));
		    			
		    			String temp[] = tempoSessao.split(":");
		    			Long minutos = (new Long(temp[0])*60) + new Long(temp[1]);
		    			Long tempoTotal = minutos * 60;
		    			
		    			agenda.setTempoSessao(tempoTotal.toString());
		    			dao.save(agenda);
		                
		            } else {
		                fileData.add("Erro ao salvar o arquivo: " +
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
	            fileData.add("Agenda cadastrada com sucesso.");
	          //  fileData.add("Total Size of Files In Directory: " + dirSize + " bytes");
	        }
        
		}
		
		
		
        
    }

	public String agendarSessao(){
		return "agendamento";
	}
	
    public List getFileData() {
        return fileData;
    }

  
	
	
}
