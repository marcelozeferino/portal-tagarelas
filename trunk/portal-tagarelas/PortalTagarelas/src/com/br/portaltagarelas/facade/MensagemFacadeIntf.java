package com.br.portaltagarelas.facade;

import com.br.portaltagarelas.model.Mensagem;

public interface MensagemFacadeIntf {
	
	public boolean salvarMensagemFacade(Mensagem mensagem) throws Exception;
	
	public Mensagem salvarMensagemRetornandoIdFacade(Mensagem mensagem) throws Exception;
}