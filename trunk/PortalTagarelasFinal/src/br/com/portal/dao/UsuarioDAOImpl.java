package br.com.portal.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import br.com.portal.model.Agenda;
import br.com.portal.model.Usuario;
import br.com.portal.util.JPAUtil;

public class UsuarioDAOImpl implements UsuarioDAO{

	private EntityManager entityManager;
	
	public UsuarioDAOImpl(){
		
	}
	
	public UsuarioDAOImpl(EntityManager entityManager) {
		 this.entityManager = entityManager;
	}
	
	
	public void adiciona(Usuario usuario) {
		this.entityManager.persist(usuario);
		this.entityManager.flush();
	}
	
	public List<Usuario> getUsuarios() {
		Query query = this.entityManager.createQuery("select j from Usuario as j");
		return query.getResultList();
	}
	
	public Usuario validaLogin(String login, String senha){
		Query query = this.entityManager.createNativeQuery("Select * from usuario where nomeUsuario=? and senha=?", Usuario.class);
		query.setParameter(1, login);
		query.setParameter(2, senha);
		return (Usuario) query.getSingleResult();
		
	}
	
	
	public Usuario getUsuarioPorID(String idUsuario){
		Query query = this.entityManager.createNativeQuery("Select * from usuario where idUsuario=?", Usuario.class);
		query.setParameter(1, idUsuario);
		return (Usuario) query.getSingleResult();
		
	}
	
	@Override
	public void save(Usuario usuario) {
		JPAUtil jpa = new JPAUtil();
		EntityManager em = jpa.getEm();
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Usuario getUsuario(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> list() {
		JPAUtil jpa = new JPAUtil();
		EntityManager em = jpa.getEm();
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT * FROM usuario");
		List<Usuario> usuarios = query.getResultList();
		em.close(); 
		
		
		
		return usuarios;
	}

	public Usuario verificaSenhaUsuario(String email) {
		
		
		JPAUtil jpa = new JPAUtil();
		EntityManager em = jpa.getEm();
		em.getTransaction().begin();
		Query query = em.createNativeQuery("SELECT * FROM Usuario e where email = ?", Usuario.class);
		query.setParameter(1, email);
		Usuario  usu = new Usuario();
		try{
			usu = (Usuario)query.getSingleResult();
		}catch(NoResultException e ){
			usu = null;
		}
		
		return usu;
	}
	
	@Override
	public void remove(Usuario usuario) {
		JPAUtil jpa = new JPAUtil();
		EntityManager em = jpa.getEm();
		em.getTransaction().begin();
		Usuario c = em.find(Usuario.class, usuario.getIdUsuario());
		em.getTransaction().commit();
		em.getTransaction().begin();
		em.remove(c);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void update(Usuario usuario) {
		JPAUtil jpa = new JPAUtil();
		EntityManager em = jpa.getEm();
		em.getTransaction().begin();
		em.merge(usuario);
		em.getTransaction().commit();
		
	}

}
