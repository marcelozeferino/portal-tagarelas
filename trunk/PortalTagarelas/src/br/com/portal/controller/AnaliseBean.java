package br.com.portal.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.ItemSelectEvent;

import br.com.portal.model.service.AnaliseSessaoService;
import br.com.portal.model.vo.AnaliseVO;

@ManagedBean
@SessionScoped
/**
 * Managed Bean para manipulação de análises de conversas
 * @author mzeferino
 *
 */
public class AnaliseBean {

	private AnaliseVO analise = new AnaliseVO();

	public AnaliseVO getAnalise() {
		return analise;
	}

	public void setAnalise(AnaliseVO analise) {
		this.analise = analise;
	}
	
	public AnaliseBean(){
		gerarAnalise();
	}
	
	public void gerarAnalise(){
		
		String assunto = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("assuntoAgenda");
		this.analise = new AnaliseSessaoService().gerarAnalise(assunto);
	}

	 public void itemSelect(ItemSelectEvent event) {
		 
	        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",  
	                        "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());  
	        
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        
	    }  
	
}
