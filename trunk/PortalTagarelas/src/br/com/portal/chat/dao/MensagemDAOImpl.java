package br.com.portal.chat.dao;

import javax.persistence.EntityManager;

import br.com.portal.chat.model.vo.Mensagem;
import br.com.portal.util.JPAUtil;

public class MensagemDAOImpl implements MensagemDAO{

	
    private EntityManager entityManager;
	
	public MensagemDAOImpl(){
		
	}
	
	public MensagemDAOImpl(EntityManager entityManager) {
		 this.entityManager = entityManager;
	}
	
	
	
	@Override
	public void save(Mensagem mensagem) {
		JPAUtil jpa = new JPAUtil();
		EntityManager em = jpa.getEm();
		em.getTransaction().begin();
		em.persist(mensagem);
		em.getTransaction().commit();
		em.close();
	}
}
