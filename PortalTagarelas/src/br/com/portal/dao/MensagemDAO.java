package br.com.portal.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.portal.model.MensagensPorMinuto;
import br.com.portal.model.MensagensPorMinutoUsuario;
import br.com.portal.model.MensagesPorUsuario;
import br.com.portal.util.JPAUtil;

public class MensagemDAO {

	@SuppressWarnings("unchecked")
	public List<MensagesPorUsuario> getMensagensPorUsuario (int sessao) {
	
		List<MensagesPorUsuario> mensagens = new ArrayList<MensagesPorUsuario>();
		
		try{
			JPAUtil jpa = new JPAUtil();
			EntityManager em = jpa.getEm();
			em.getTransaction().begin();
			Query query = em.createNativeQuery("SELECT * FROM MensagesPorUsuario m where m.idAgenda = ? order by m.totalMensagens", MensagesPorUsuario.class);
			query.setParameter(1, sessao);
			
			mensagens.addAll(query.getResultList());
			
		}catch (Exception e){
			
			e.printStackTrace();
			
		}
		
		return mensagens;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<MensagensPorMinuto> getMensagensPorMinuto (int sessao) {
	
		List<MensagensPorMinuto> mensagens = new ArrayList<MensagensPorMinuto>();
		
		try{
			JPAUtil jpa = new JPAUtil();
			EntityManager em = jpa.getEm();
			em.getTransaction().begin();
			Query query = em.createNativeQuery("SELECT * FROM MensagensPorMinuto m where m.idAgenda = ? order by m.horario", MensagensPorMinuto.class);
			query.setParameter(1, sessao);
			
			mensagens.addAll(query.getResultList());
			
		}catch (Exception e){
			
			e.printStackTrace();
			
		}
		
		return mensagens;
		
	}
		

	@SuppressWarnings("unchecked")
	public List<MensagensPorMinutoUsuario> getMensagensPorMinuto (String nomeUsuario, int sessao) {
	
		List<MensagensPorMinutoUsuario> mensagens = new ArrayList<MensagensPorMinutoUsuario>();
		
		try{
			JPAUtil jpa = new JPAUtil();
			EntityManager em = jpa.getEm();
			em.getTransaction().begin();
			Query query = em.createNativeQuery("SELECT * FROM MensagensPorMinutoUsuario m where m.idAgenda = ? and m.nomeUsuario = ?", MensagensPorMinutoUsuario.class);
			query.setParameter(1, sessao);
			query.setParameter(2, nomeUsuario);
			
			mensagens.addAll(query.getResultList());
			
		}catch (Exception e){
			
			e.printStackTrace();
			
		}
		
		return mensagens;
		
	}

}
