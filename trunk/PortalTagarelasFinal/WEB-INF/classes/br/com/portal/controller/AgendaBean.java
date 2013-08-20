package br.com.portal.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.portal.chat.tipico.controller.ChatBean;
import br.com.portal.dao.AgendaDAOImpl;
import br.com.portal.model.Agenda;
import br.com.portal.model.Usuario;

@ManagedBean
@SessionScoped
public class AgendaBean {

	@ManagedProperty(value = "#{chatBean}")
	private ChatBean chatBean;
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
	private String tempoSessao;
	private String arquivo;
	private boolean sessaoLiberada;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date dataAtual = new Date();
	String dataStr = sdf.format(dataAtual);
	private List<Agenda> listaArquivoPaginaPrincipal;
	private Agenda agenda;
	private List<Agenda> agendasRecentes;
	private List<Agenda> agendas; 	
	@ManagedProperty(value = "#{loginBean}")
	public LoginBean loginBean;	
	private String detalheItem;
	private String idAgenda;
	
	private List<Agenda>minhaSessao;
	
	private List<Agenda>sessaoParticipar;
	
	
	private List<Agenda> sessaoParticipei;
	
	
	
	
	public void setSessaoParticipei(List<Agenda> sessaoParticipei) {
		this.sessaoParticipei = sessaoParticipei;
	}

	public void setSessaoParticipar(List<Agenda> sessaoParticipar) {
		this.sessaoParticipar = sessaoParticipar;
	}

	public void setMinhaSessao(List<Agenda> minhaSessao) {
		this.minhaSessao = minhaSessao;
	}

	private List<Agenda> sessoes;
	
	
	public List<Agenda> getSessoes() {
		return sessoes;
	}

	public void setSessoes(List<Agenda> sessoes) {
		this.sessoes = sessoes;
	}

	public String getTempoSessao() {
		return tempoSessao;
	}

	public void setTempoSessao(String tempoSessao) {
		this.tempoSessao = tempoSessao;
	}

	public String getIdAgenda() {
		return idAgenda;
	}

	public void setIdAgenda(String idAgenda) {
		this.idAgenda = idAgenda;
	}

	public ChatBean getChatBean() {
		return chatBean;
	}

	public void setChatBean(ChatBean chatBean) {
		this.chatBean = chatBean;
	}

	public boolean isSessaoLiberada() {
		return sessaoLiberada;
	}

	public void setSessaoLiberada(boolean sessaoLiberada) {
		this.sessaoLiberada = sessaoLiberada;
	}

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
			//agenda.setTempoSessao(tempoSessao);
			agenda.setTempoSessao("10000000");
			
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
		return "listaAgenda?faces-redirect=true;";
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
		return "agendar?faces-redirect=true;";
	}
	
	public String getDetalheItem(){
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("itemAgenda"); 
		AgendaDAOImpl dao = new AgendaDAOImpl();
		Agenda agenda = dao.detalheItem(id);
		setAgenda(agenda);
		return "detalheItemAgenda?faces-redirect=true;";
	}
	
	public String busca(){
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("itemAgenda"); 
		AgendaDAOImpl dao = new AgendaDAOImpl();
		Agenda agenda = dao.detalheItem(id);
		setAgenda(agenda);
		return "detalheItemAgenda?faces-redirect=true;";
	}
	
	public String getPaginaPrincipal(){
		return "principalPortal?faces-redirect=true;";
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
	
	public String acessarSessao(){
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("itemAgenda"); 
		
		int idAgendaSessao = Integer.parseInt(id);
		AgendaDAOImpl dao = new AgendaDAOImpl();
		Agenda agenda = dao.detalheItem(id);
		
		agenda.getDataHora();
		
		long maisCinco = dataAtual.getTime() + 300000;
		
	//	Date dataAtualAumentada = new Date(maisCinco);
		
		boolean flagAcesso; 
	
	//	if(dataAtualAumentada.after(agenda.getDataHora())){
			flagAcesso = true;
	//	}else{
	//		flagAcesso = false;
	//	}
		
		setSessaoLiberada(flagAcesso);
		String retorno = "";
		
		//ChatBean chatBean = new ChatBean();
		
		if(flagAcesso){
			if(agenda.getIdSistema().equals("0")){
				//String apelido = loginBean.getUsuario().getNomeUsuario();
				Usuario usuario = loginBean.getUsuario();
				chatBean.entrar(usuario,idAgendaSessao);
				setIdAgenda(id);
				retorno =  "chatTipico?faces-redirect=true;";
			}else if(agenda.getIdSistema().equals("2")){
				retorno = "chatEntrevista?faces-redirect=true;";
			}else if(agenda.getIdSistema().equals("3")){
				//String apelido = loginBean.getUsuario().getNomeUsuario();
				Usuario usuario = loginBean.getUsuario();
				chatBean.entrar(usuario,idAgendaSessao);
				setIdAgenda(id);
				retorno =  "chatTipicoNovo?faces-redirect=true;";
			}else if(agenda.getIdSistema().equals("4")){
				//String apelido = loginBean.getUsuario().getNomeUsuario();
				Usuario usuario = loginBean.getUsuario();
				chatBean.entrar(usuario,idAgendaSessao);
				setIdAgenda(id);
				retorno =  "chatTipicoNovotrab?faces-redirect=true;";
			}else if(agenda.getIdSistema().equals("5")){ // ESSE QUE TA VALENDO
				//String apelido = loginBean.getUsuario().getNomeUsuario();
				Usuario usuario = loginBean.getUsuario();
				chatBean.entrar(usuario,idAgendaSessao);
				setIdAgenda(id);
				retorno =  "chatTipicoFullFuncionando?faces-redirect=true;";
			}else if(agenda.getIdSistema().equals("6")){
				//String apelido = loginBean.getUsuario().getNomeUsuario();
				Usuario usuario = loginBean.getUsuario();
				chatBean.entrar(usuario,idAgendaSessao);
				setIdAgenda(id);
				retorno =  "chatTipicoFulltrab?faces-redirect=true;";
			}
		}else{
			retorno = "semAcesso?faces-redirect=true;";
			setMensagem("Sessão ainda não foi liberada");
		}
		
		return retorno;
	}
	
	public List<Agenda> getMinhaSessao(){
		AgendaDAOImpl dao = new AgendaDAOImpl();
		Usuario u = loginBean.getUsuario();
		String retorno = "";
		List<Agenda> listaMinha = new ArrayList<Agenda>();
		if(u == null){
			setMensagem("Usuário não está logado");
			retorno = "semAcesso?faces-redirect=true;";
		}else{
			listaMinha = dao.getMinhaSessao(Integer.toString(u.getIdUsuario()));
			//setSessoes(arquivos);
		//	retorno =  "listaMinhaSessao?faces-redirect=true;";
		}
		return listaMinha;
	}
	
	public List<Agenda> getSessaoParticipar(){
		AgendaDAOImpl dao = new AgendaDAOImpl();
		Usuario u = loginBean.getUsuario();
		String retorno = "";
		List<Agenda> lista = new ArrayList<Agenda>();
		if(u == null){
			setMensagem("Usuário não está logado");
			retorno = "semAcesso?faces-redirect=true;";
		}else{
			lista = dao.getSessaoParticipar(Integer.toString(u.getIdUsuario()));
			//setSessoes(arquivos);
		//	retorno =  "listaMinhaSessao?faces-redirect=true;";
		}
		return lista;
	}
	
	
	public List<Agenda> getSessaoParticipei(){
		AgendaDAOImpl dao = new AgendaDAOImpl();
		Usuario u = loginBean.getUsuario();
		String retorno = "";
		List<Agenda> lista = new ArrayList<Agenda>();
		if(u == null){
			setMensagem("Usuário não está logado");
			retorno = "semAcesso?faces-redirect=true;";
		}else{
			lista = dao.getSessaoParticipei(Integer.toString(u.getIdUsuario()));
			//setSessoes(arquivos);
		//	retorno =  "listaMinhaSessao?faces-redirect=true;";
		}
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
	
	public static void main(String[] args) {
		AgendaBean bean = new AgendaBean();
		bean.acessarSessao();
	}
}