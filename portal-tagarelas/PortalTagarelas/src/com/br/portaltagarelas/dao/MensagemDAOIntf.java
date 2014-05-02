package com.br.portaltagarelas.dao;

import com.br.portaltagarelas.model.Mensagem;

public interface MensagemDAOIntf {

	public boolean salvarMensagem(Mensagem mensagem) throws Exception;
	
	public Mensagem salvarMensagemRetornandoId(Mensagem mensagem) throws Exception;
}