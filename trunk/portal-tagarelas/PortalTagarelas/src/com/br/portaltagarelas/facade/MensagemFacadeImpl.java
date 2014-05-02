package com.br.portaltagarelas.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.portaltagarelas.dao.MensagemDAOIntf;
import com.br.portaltagarelas.model.Mensagem;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class MensagemFacadeImpl implements MensagemFacadeIntf{

	@Autowired private MensagemDAOIntf mensagemDAO;
	
	public boolean salvarMensagemFacade(Mensagem mensagem) throws Exception{
		try {
			return mensagemDAO.salvarMensagem(mensagem);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Mensagem salvarMensagemRetornandoIdFacade(Mensagem mensagem) throws Exception{
		try {
			return mensagemDAO.salvarMensagemRetornandoId(mensagem);
		} catch (Exception e) {
			throw e;
		}
	}
}