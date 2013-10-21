package br.com.portal.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ClassificacaoUsuario {
	
	private int limiteRuim;
	private int limiteMedio;
	private List<MensagesPorUsuario> listaMensagens;
	
	public ClassificacaoUsuario(List<MensagesPorUsuario> listaMensagens) {
		
		this.listaMensagens = listaMensagens;
		this.calcularLimites();
		
	}
		
	private void calcularLimites (){
		
		if (this.limiteMedio == 0 || this.limiteRuim == 0){
			int totalMsgs = 0;
			int divisao = 0;
			
			for (MensagesPorUsuario mensagesPorUsuario : this.listaMensagens) {
				totalMsgs = totalMsgs + mensagesPorUsuario.getTotalMensagens();
			}
			
			divisao = new Double (totalMsgs / 3).intValue();
			
			this.limiteRuim	= divisao;
			this.limiteMedio = divisao * 2;	
		}
		
	}
	
	public List<MensagesPorUsuario> getListaRuim() {
		
		List<MensagesPorUsuario> listaRuim = new ArrayList<MensagesPorUsuario>();
		
		for (MensagesPorUsuario mensagesPorUsuario : this.listaMensagens) {
			
			if (mensagesPorUsuario.getTotalMensagens() <= this.limiteRuim){
				listaRuim.add(mensagesPorUsuario);
			}
			
		}
		
		return listaRuim;
		
	}
	

	public List<MensagesPorUsuario> getListaMedio() {
		
		List<MensagesPorUsuario> listaMedio = new ArrayList<MensagesPorUsuario>();
		
		for (MensagesPorUsuario mensagesPorUsuario : this.listaMensagens) {
			
			if (mensagesPorUsuario.getTotalMensagens() > this.limiteRuim && mensagesPorUsuario.getTotalMensagens() <= this.limiteMedio){
				listaMedio.add(mensagesPorUsuario);
			}
			
		}
		
		return listaMedio;
		
	}

	public List<MensagesPorUsuario> getListaBom() {
		
		List<MensagesPorUsuario> listaBom = new ArrayList<MensagesPorUsuario>();
		
		for (MensagesPorUsuario mensagesPorUsuario : this.listaMensagens) {
			
			if (mensagesPorUsuario.getTotalMensagens() > this.limiteMedio){
				listaBom.add(mensagesPorUsuario);
			}
			
		}
		
		return listaBom;
		
	}	
	
	public Set<String> getListaUsuarios(){
		
		Set<String> setUsuarios = new TreeSet<String>();
		
		for (MensagesPorUsuario mensagens : this.listaMensagens){
			setUsuarios.add(mensagens.getNomeUsuario());
		}
		
		return setUsuarios;
		
	}
	
	public int getLimiteRuim() {
		return limiteRuim;
	}

	public void setLimiteRuim(int limiteRuim) {
		this.limiteRuim = limiteRuim;
	}

	public int getLimiteMedio() {
		return limiteMedio;
	}

	public void setLimiteMedio(int limiteMedio) {
		this.limiteMedio = limiteMedio;
	}
	
	

}
