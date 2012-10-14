package br.com.portal.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.portal.model.Agenda;
import br.com.portal.model.Sessao;
import br.com.portal.util.JPAUtil;

public class AgendaDAOImpl implements AgendaDAO{

	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date dataAtual = new Date();
	String dataStr = sdf.format(dataAtual);
	@Override
	public void save(Agenda agenda) {
		JPAUtil jpa = new JPAUtil();
		EntityManager em = jpa.getEm();
		em.getTransaction().begin();
		em.persist(agenda);
		em.getTransaction().commit();
		em.close();
		
	}

	@Override
	public Agenda getAgenda(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Agenda> list() {
		
	
		JPAUtil jpa = new JPAUtil();
		EntityManager em = jpa.getEm();
		em.getTransaction().begin();
		Query query = em.createNativeQuery("SELECT * FROM Agenda e where data >= ?", Agenda.class);
		query.setParameter(1, dataStr);
		List<Agenda> agendas = query.getResultList();
		
		return agendas;
	}
	
	public Agenda detalheItem(String item) {
		JPAUtil jpa = new JPAUtil();
		EntityManager em = jpa.getEm();
		em.getTransaction().begin();
		//Query query = em.createQuery("SELECT e FROM Agenda e where e.idAgenda = " + item);
		//List<Agenda> agendas = query.getResultList();
		
		Query query = em.createNativeQuery("SELECT * FROM Agenda e where idAgenda = ?", Agenda.class);
		query.setParameter(1, item);
		Agenda agenda = new Agenda();
		agenda = (Agenda) query.getSingleResult();
		return agenda;
	}
	
	public void participarSessao(String itemAgenda, String idUsuario) {
		JPAUtil jpa = new JPAUtil();
		EntityManager em = jpa.getEm();
		em.getTransaction().begin();
		Sessao sessao = new Sessao();
		sessao.setIdAgenda(Integer.parseInt(itemAgenda));
		sessao.setIdUsuario(Integer.parseInt(idUsuario));
		em.persist(sessao);
		em.getTransaction().commit();
		em.close(); 
	}
	 
	public List<Agenda> getArquivo() {
		
		
		JPAUtil jpa = new JPAUtil();
		EntityManager em = jpa.getEm();
		em.getTransaction().begin();
		Query query = em.createNativeQuery("SELECT * FROM Agenda e where data < ?", Agenda.class);
		query.setParameter(1, dataStr);
		List<Agenda> arquivos = query.getResultList();
		return arquivos;
	}
	
public List<Agenda> getArquivoPaginaPrincipal() {
		
		
		JPAUtil jpa = new JPAUtil();
		EntityManager em = jpa.getEm();
		em.getTransaction().begin();
		Query query = em.createNativeQuery("SELECT * FROM Agenda e where data < ? order by datahora desc Limit 0,3", Agenda.class);
		query.setParameter(1, dataStr);
		List<Agenda> arquivos = query.getResultList();
		return arquivos;
	}
	
	
	public List<Agenda> getMinhaSessao(String idUsuario) {
		
		
		JPAUtil jpa = new JPAUtil();
		EntityManager em = jpa.getEm();
		em.getTransaction().begin();
		Query query = em.createNativeQuery("SELECT * FROM Agenda e, Sessao s where e.data >= ? and e.idAgenda = s.idAgenda and s.idUsuario = ? ", Agenda.class);
		query.setParameter(1, dataStr);
		query.setParameter(2, idUsuario);
		List<Agenda> arquivos = query.getResultList();
		return arquivos;
	}
	
	
	@Override
	public List<Agenda> getAgendasRecentes() {
		JPAUtil jpa = new JPAUtil();
		EntityManager em = jpa.getEm();
		em.getTransaction().begin();
		Query query = em.createNativeQuery("SELECT * FROM portal.agenda a order by datahora desc Limit 0,3", Agenda.class);
		List<Agenda> agendas = query.getResultList();
		return agendas;
	}
	

	@Override
	public void remove(Agenda agenda) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Agenda agenda) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		AgendaDAOImpl i = new AgendaDAOImpl();
		i.getAgendasRecentes();
		
		
	}


	
}
