package br.com.portal.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;



import br.com.portal.dao.AgendaDAOImpl;
import br.com.portal.model.Agenda;
import br.com.portal.model.Usuario;

@ManagedBean
@SessionScoped
public class AgendaBean {

	private String assunto;
	private String descricao;
	private String breveDescricao;
	private String idSistema;
	//private Calendar data = new GregorianCalendar();
	//private Calendar hora = new GregorianCalendar();
	private int idUsuario;
	private String mensagem;
	private String aviso;
	private Date data;
	private Date hora;
	
	private String arquivo;
	
	
	 
	private List<Agenda> listaArquivoPaginaPrincipal;
	
	private Agenda agenda;
	
	private List<Agenda> agendasRecentes;
	
	private List<Agenda> agendas; 	
	
	@ManagedProperty(value = "#{loginBean}")
	public LoginBean loginBean;	
	
	private String detalheItem;
	
	
	
	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public void setDetalheItem(String detalheItem) {
		this.detalheItem = detalheItem;
	}

	public void setListaArquivoPaginaPrincipal(
			List<Agenda> listaArquivoPaginaPrincipal) {
		this.listaArquivoPaginaPrincipal = listaArquivoPaginaPrincipal;
	}

	public void setAgendasRecentes(List<Agenda> agendasRecentes) {
		this.agendasRecentes = agendasRecentes;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public void setLoginBean(LoginBean loginBean){
		this.loginBean = loginBean;
	}
	
	public AgendaBean(){
		this.data = new Date();
		this.hora = new Date();
	}
	
	public String getMensagem() {
		return mensagem;
	}

	
	public String getAviso() {
		return aviso;
	}

	public void setAviso(String aviso) {
		this.aviso = aviso;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public String getDay(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(data);
	}
	
	public String getTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(hora);
	}
	
	public void setDay(String dia) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.data = sdf.parse(dia);
	}

	public void setTime(String time) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		this.hora = sdf.parse(time);
	}
	
	public void cadastrarAgenda(){
		System.out.println("Agenda :" + data + " " + hora);
		//System.out.println("hora mili: " + hora.getTimeZone().toString());
		//System.out.println("hora dayhour: " + hora.getTimeInMillis());
		
		Usuario u = loginBean.getUsuario();
		
		
		Agenda agenda = new Agenda();
		if(u == null){
			setMensagem("Usuário não está logado");
		}else{
			AgendaDAOImpl dao = new AgendaDAOImpl();
			
			
			agenda.setAssunto(assunto);
			agenda.setData(data);
			agenda.setHora(hora);
			agenda.setDescricao(descricao);
			agenda.setIdSistema(idSistema);
			agenda.setBreveDescricao(breveDescricao);
			agenda.setIdUsuario(String.valueOf(u.getIdUsuario()));
			
			dao.save(agenda);
			
			setMensagem("Agenda cadastrada com sucesso.");
		}
		
	} 
	
	public String getAgendaLink(){
		List <Agenda> lista = getAgendas();
		if(lista.size() == 0){
			setMensagem("Não existem salas agendadas.");
		}else{
			setAgendas(lista);
		}
		return "listaAgenda";
	}
	
	public List<Agenda> getAgendas(){
		AgendaDAOImpl dao = new AgendaDAOImpl();
		List<Agenda> lista = dao.list();
		
//		for(Agenda a : lista) {
//			System.out.println("Agenda: " + a.getAssunto() + " - " + a.getDescricao());
//		}
		
		return lista;
	}
	
	public String agendarSessao(){
		return "agendar";
	}
	
	public String getDetalheItem(){
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("itemAgenda"); 
		AgendaDAOImpl dao = new AgendaDAOImpl();
		Agenda agenda = dao.detalheItem(id);
		setAgenda(agenda);
		return "detalheItemAgenda";
	}
	
	
	public String busca(){
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("itemAgenda"); 
		AgendaDAOImpl dao = new AgendaDAOImpl();
		Agenda agenda = dao.detalheItem(id);
		setAgenda(agenda);
		return "detalheItemAgenda";
	}
	
	public String getPaginaPrincipal(){
		return "principalPortal";
	}
	
	public void participarSessao(){
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("itemAgenda"); 
		String idUsuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUsuarioAgenda"); 
		AgendaDAOImpl dao = new AgendaDAOImpl();
		dao.participarSessao(id, idUsuario);
		setMensagem("Participação efetuada");
		
	}
	
	public List<Agenda> getAgendasRecentes(){
		AgendaDAOImpl dao = new AgendaDAOImpl();
		List<Agenda> lista = dao.getAgendasRecentes();
		return lista;
	}
	
	public List<Agenda> getListaArquivoPaginaPrincipal(){
		AgendaDAOImpl dao = new AgendaDAOImpl();
		List<Agenda> lista = dao.getArquivoPaginaPrincipal();
		return lista;
	}
	
	
	
	
	public String getBreveDescricao() {
		return breveDescricao;
	}

	public void setBreveDescricao(String breveDescricao) {
		this.breveDescricao = breveDescricao;
	}

	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getIdSistema() {
		return idSistema;
	}
	public void setIdSistema(String idSistema) {
		this.idSistema = idSistema;
	}

	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setAgendas(List<Agenda> agendas) {
		this.agendas = agendas;
	}
	
	
	
}
