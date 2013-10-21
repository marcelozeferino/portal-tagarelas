package br.com.portal.controller.grafico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.icefaces.ace.component.chart.Axis;
import org.icefaces.ace.component.chart.AxisType;
import org.icefaces.ace.model.chart.BubbleSeries;
import org.icefaces.ace.model.chart.CartesianSeries;
import org.icefaces.ace.model.chart.CartesianSeries.CartesianType;
import org.icefaces.ace.model.chart.ChartSeries.ChartType;
import org.icefaces.impl.push.SessionViewManager;

import br.com.portal.dao.AgendaDAOImpl;
import br.com.portal.dao.MensagemDAO;
import br.com.portal.model.Agenda;
import br.com.portal.model.ClassificacaoUsuario;
import br.com.portal.model.MensagensPorMinuto;
import br.com.portal.model.MensagensPorMinutoUsuario;
import br.com.portal.model.MensagesPorUsuario;

@ManagedBean(name = "chartBarBean")
@SessionScoped
public class ChartBarBean implements Serializable {

	private static final long serialVersionUID = 9104302184172744217L;

	private ClassificacaoUsuario classUsuario;
	private List<MensagesPorUsuario> mensagensPorUsuario;
	List<CartesianSeries> dados;
	List<CartesianSeries> dadosLinha;
	List<BubbleSeries> dadosBolha;
	private int limiteRuim = 10;
	private int limiteMedio;
	private List<SelectItem> participantes;
	private String participanteSelecionado;
	
	private List<SelectItem> sessoes;
	private String sessaoSelecionada;
	
	private List<MensagensPorMinuto> mensagensPorMinuto;
	private List<MensagensPorMinutoUsuario> mensagensPorMinutoUsuario;
	

	public ChartBarBean() {

		this.sessoes = prepararItemsSessoes();
		
		if (this.sessaoSelecionada == null ){
			this.sessaoSelecionada = this.sessoes.get(0).getValue().toString();
		}
		
		gerarGraficoDeBarras(new Integer(this.sessaoSelecionada));

		gerarGraficoLinha(new Integer(this.sessaoSelecionada));
		
		gerarGraficoBolhas(new Integer(this.sessaoSelecionada));

	}

	private void gerarGraficoLinha(int sessao) {
		
		this.mensagensPorMinuto = new MensagemDAO().getMensagensPorMinuto(sessao);
		this.dadosLinha = new ArrayList<CartesianSeries>();

		CartesianSeries serieLinha = new CartesianSeries();
		serieLinha.setLabel("Evolução no Envio de Mensagens");
		serieLinha.setType(CartesianType.LINE);

		for (MensagensPorMinuto mensagem : this.mensagensPorMinuto) {
			serieLinha.add(mensagem.getHorario(), mensagem.getTotal());
		}

		dadosLinha.add(serieLinha);
		
	}

	private void gerarGraficoDeBarras(int sessao) {
		
		this.mensagensPorUsuario = new MensagemDAO().getMensagensPorUsuario(sessao);
		this.classUsuario = new ClassificacaoUsuario(mensagensPorUsuario);
		this.participantes = prepararItemsParticipantes();

		this.dados = new ArrayList<CartesianSeries>();

		CartesianSeries serieRuim = prepararRuim(classUsuario);
		serieRuim.setLabel("Ruim");

		CartesianSeries serieMedio = prepararMedio(classUsuario);
		serieMedio.setLabel("Médio");

		CartesianSeries serieBom = prepararBom(classUsuario);
		serieBom.setLabel("Bom");

		dados.add(serieRuim);
		dados.add(serieMedio);
		dados.add(serieBom);
	}
	
	private void gerarGraficoBolhas(int sessao) {
		
		this.dadosBolha = new ArrayList<BubbleSeries>();

		BubbleSeries serieBolha = new BubbleSeries();
		serieBolha.setLabel("Bolhas");

		for (MensagesPorUsuario mensagem : this.mensagensPorUsuario) {
			serieBolha.add(mensagem.getTotalMensagens(), mensagem.getTotalCaracteres(), 
					mensagem.getTotalMensagens(), mensagem.getNomeUsuario());
		}

		dadosBolha.add(serieBolha);
	}


	private List<SelectItem> prepararItemsParticipantes() {
		
		List<SelectItem> partItems = new ArrayList<SelectItem>();
		partItems.add(new SelectItem("<Todos>"));
		for (String nomeParticipante : this.classUsuario.getListaUsuarios()){
			partItems.add(new SelectItem(nomeParticipante));
		}
		
		return partItems;
		
	}
	
	private List<SelectItem> prepararItemsSessoes() {
		
		List<SelectItem> sessaoItems = new ArrayList<SelectItem>();
		
		List<Agenda> sessoes = new AgendaDAOImpl().getAgendas();
		
		for (Agenda agenda : sessoes) {
			sessaoItems.add(new SelectItem( new Integer(agenda.getIdAgenda()).toString() ));
		}
				
		return sessaoItems;
		
	}

	public List<CartesianSeries> getDados() {

		return this.dados;

	}

	public void atualizarDados() {

		this.dados = new ArrayList<CartesianSeries>();

		CartesianSeries serieRuim = prepararRuim(classUsuario);
		serieRuim.setLabel("Ruim");

		CartesianSeries serieMedio = prepararMedio(classUsuario);
		serieMedio.setLabel("Médio");
		
		CartesianSeries serieBom = prepararBom(classUsuario);
		serieBom.setLabel("Bom");

		dados.add(serieRuim);
		dados.add(serieMedio);
		dados.add(serieBom);

	}
	
	
	public void participantesChange(ValueChangeEvent event) {
		
		this.participanteSelecionado = event.getNewValue().toString();
		filtrarMensagensPorUsuario();
        
    }
	
	public void sessoesChange(ValueChangeEvent event) {
		
		this.sessaoSelecionada = event.getNewValue().toString();
		filtrarSessao();
        
    }
	
	public void filtrarSessao(){
		
		gerarGraficoDeBarras(new Integer(this.sessaoSelecionada));

		gerarGraficoLinha(new Integer(this.sessaoSelecionada));
		
		gerarGraficoBolhas(new Integer(this.sessaoSelecionada));		
		
	}
	
	public void filtrarMensagensPorUsuario(){
		
		if (!this.participanteSelecionado.equalsIgnoreCase("<Todos>")) {
			
			this.mensagensPorMinutoUsuario = new MensagemDAO().getMensagensPorMinuto(participanteSelecionado, new Integer(this.sessaoSelecionada));
			this.dadosLinha = new ArrayList<CartesianSeries>();
	
			CartesianSeries serieLinha = new CartesianSeries();
			serieLinha.setLabel("Evolução no Envio de Mensagens");
			serieLinha.setType(CartesianType.LINE);
	
			for (MensagensPorMinutoUsuario mensagem : this.mensagensPorMinutoUsuario) {
				serieLinha.add(mensagem.getHorario(), mensagem.getTotal());
			}
	
			dadosLinha.add(serieLinha);
			
		}else{
			
			gerarGraficoLinha(new Integer(this.sessaoSelecionada));
						
		}
		
	}

	private CartesianSeries prepararBom(ClassificacaoUsuario classUsuario) {
		CartesianSeries serieBom = new CartesianSeries();
		serieBom.setType(CartesianType.BAR);

		for (MensagesPorUsuario mensagem : classUsuario.getListaBom()) {
			serieBom.add(mensagem.getNomeUsuario(),
					mensagem.getTotalMensagens());
		}
		return serieBom;
	}

	private CartesianSeries prepararMedio(ClassificacaoUsuario classUsuario) {
		CartesianSeries serieMedio = new CartesianSeries();
		serieMedio.setType(CartesianType.BAR);

		for (MensagesPorUsuario mensagem : classUsuario.getListaMedio()) {
			serieMedio.add(mensagem.getNomeUsuario(),
					mensagem.getTotalMensagens());
		}
		return serieMedio;
	}

	private CartesianSeries prepararRuim(ClassificacaoUsuario classUsuario) {
		CartesianSeries serieRuim = new CartesianSeries();
		serieRuim.setType(CartesianType.BAR);

		for (MensagesPorUsuario mensagem : classUsuario.getListaRuim()) {
			serieRuim.add(mensagem.getNomeUsuario(),
					mensagem.getTotalMensagens());
		}
		return serieRuim;
	}

	private Axis xAxis = new Axis() {
		{
			setType(AxisType.CATEGORY);
			setTickAngle(-30);
		}
	};

	private Axis[] yAxes = new Axis[] { new Axis() {
		{
			setAutoscale(true);
			setTickInterval("5");

		}
	} };

	private Axis[] yAxesLine = new Axis[] { new Axis() {
		{
			setAutoscale(true);
			setTickInterval("5");
		}
	} };

	private Axis xAxisLine = new Axis() {
		{
			setTickAngle(-30);
			setType(AxisType.CATEGORY);
		}
	};

	public Axis getxAxis() {
		return xAxis;
	}

	public void setxAxis(Axis xAxis) {
		this.xAxis = xAxis;
	}

	public Axis[] getyAxes() {
		return yAxes;
	}

	public void setyAxes(Axis[] yAxes) {
		this.yAxes = yAxes;
	}

	
	private Axis xAxisBolha = new Axis() {
		{
			setType(AxisType.CATEGORY);
			setTickAngle(-30);
			setLabel("Mensagens");
		}
	};

	private Axis[] yAxesBolha = new Axis[] { new Axis() {
		{
			setAutoscale(true);
			setTickInterval("5");
			setLabel("Caracteres");	
		}
	} };
	
	
	
	public Axis getxAxisBolha() {
		return xAxisBolha;
	}

	public void setxAxisBolha(Axis xAxisBolha) {
		this.xAxisBolha = xAxisBolha;
	}

	public Axis[] getyAxesBolha() {
		return yAxesBolha;
	}

	public void setyAxesBolha(Axis[] yAxesBolha) {
		this.yAxesBolha = yAxesBolha;
	}

	public Axis[] getyAxesLine() {
		return yAxesLine;
	}

	public void setyAxesLine(Axis[] yAxesLine) {
		this.yAxesLine = yAxesLine;
	}

	public Axis getxAxisLine() {
		return xAxisLine;
	}

	public void setxAxisLine(Axis xAxisLine) {
		this.xAxisLine = xAxisLine;
	}

	public ClassificacaoUsuario getClassUsuario() {
		return classUsuario;
	}

	public void setClassUsuario(ClassificacaoUsuario classUsuario) {
		this.classUsuario = classUsuario;
	}

	public List<MensagesPorUsuario> getMensagensPorUsuario() {
		return mensagensPorUsuario;
	}

	public void setMensagensPorUsuario(
			List<MensagesPorUsuario> mensagensPorUsuario) {
		this.mensagensPorUsuario = mensagensPorUsuario;
	}

	public int getLimiteRuim() {
		return limiteRuim;
	}

	public void setLimiteRuim(int limiteRuim) {
		this.limiteRuim = limiteRuim;
	}

	public int getLimiteMedio() {
		return limiteMedio;
	}

	public void setLimiteMedio(int limiteMedio) {
		this.limiteMedio = limiteMedio;
	}

	public void setDados(List<CartesianSeries> dados) {
		this.dados = dados;
	}

	public List<MensagensPorMinuto> getMensagensPorMinuto() {
		return mensagensPorMinuto;
	}

	public void setMensagensPorMinuto(
			List<MensagensPorMinuto> mensagensPorMinuto) {
		this.mensagensPorMinuto = mensagensPorMinuto;
	}

	public List<CartesianSeries> getDadosLinha() {
		return dadosLinha;
	}

	public void setDadosLinha(List<CartesianSeries> dadosLinha) {
		this.dadosLinha = dadosLinha;
	}

	public List<SelectItem> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<SelectItem> participantes) {
		this.participantes = participantes;
	}

	public String getParticipanteSelecionado() {
		return participanteSelecionado;
	}

	public void setParticipanteSelecionado(String participanteSelecionado) {
		this.participanteSelecionado = participanteSelecionado;
	}

	public List<MensagensPorMinutoUsuario> getMensagensPorMinutoUsuario() {
		return mensagensPorMinutoUsuario;
	}

	public void setMensagensPorMinutoUsuario(
			List<MensagensPorMinutoUsuario> mensagensPorMinutoUsuario) {
		this.mensagensPorMinutoUsuario = mensagensPorMinutoUsuario;
	}

	public List<BubbleSeries> getDadosBolha() {
		return dadosBolha;
	}

	public void setDadosBolha(List<BubbleSeries> dadosBolha) {
		this.dadosBolha = dadosBolha;
	}

	public List<SelectItem> getSessoes() {
		return sessoes;
	}

	public void setSessoes(List<SelectItem> sessoes) {
		this.sessoes = sessoes;
	}

	public String getSessaoSelecionada() {
		return sessaoSelecionada;
	}

	public void setSessaoSelecionada(String sessaoSelecionada) {
		this.sessaoSelecionada = sessaoSelecionada;
	}
	
	

}