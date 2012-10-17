package br.com.portal.dao;

import java.util.List;

import br.com.portal.model.Agenda;


public interface AgendaDAO {

	 public void save(Agenda agenda);
	    public Agenda getAgenda(long id);
	    public List<Agenda> list();
	    public void remove(Agenda agenda);
	    public void update(Agenda agenda);
	    public List<Agenda> getAgendasRecentes();
}
