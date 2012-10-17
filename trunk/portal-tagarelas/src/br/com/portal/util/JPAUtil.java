package br.com.portal.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	
	public EntityManager getEm(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");  
		EntityManager em = emf.createEntityManager();
		return em;
	}
	
	public static void main(String[] args) {
		JPAUtil jpa = new JPAUtil();
		jpa.getEm();
	}
}
