package br.com.portal.model.vo;

import java.util.List;

import org.primefaces.model.chart.BubbleChartModel;
import org.primefaces.model.chart.BubbleChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudItem;
import org.primefaces.model.tagcloud.TagCloudModel;

/**
 * Classe VO criada para representar dados de análise
 * @author mzeferino
 *
 */
public class AnaliseVO {
	
	private String titulo;
	private int duracao;
	private int qtdeParticipantes;
	private int qtdeMsgsEnviadas;
	private int textoProduzido;
	private CartesianChartModel mensagensPorUsuario;
	private BubbleChartModel caracteresPorUsuario;
	private TagCloudModel tagCloud;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getDuracao() {
		return duracao;
	}
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	public int getQtdeParticipantes() {
		return qtdeParticipantes;
	}
	public void setQtdeParticipantes(int qtdeParticipantes) {
		this.qtdeParticipantes = qtdeParticipantes;
	}
	public int getQtdeMsgsEnviadas() {
		return qtdeMsgsEnviadas;
	}
	public void setQtdeMsgsEnviadas(int qtdeMsgsEnviadas) {
		this.qtdeMsgsEnviadas = qtdeMsgsEnviadas;
	}
	public int getTextoProduzido() {
		return textoProduzido;
	}
	public void setTextoProduzido(int textoProduzido) {
		this.textoProduzido = textoProduzido;
	}
	
	public CartesianChartModel getMensagensPorUsuario() {
		
		mensagensPorUsuario = new CartesianChartModel();

		ChartSeries usuarios = new ChartSeries();
		usuarios.setLabel("Usuários");

		//usuarios.set("Pedro", 10);
		usuarios.set("Zeferino", 10);
		usuarios.set("Marcelo", 20);
		usuarios.set("Pimentel", 25);
		usuarios.set("Edmilson", 39);
		usuarios.set("Thiago", 48);
		usuarios.set("Rafael", 56);
		usuarios.set("Paulo", 70);
		
		mensagensPorUsuario.addSeries(usuarios);

		return mensagensPorUsuario;
		
	}
	
	public BubbleChartModel getCaracteresPorUsuario() {
		
		caracteresPorUsuario = new BubbleChartModel();

		caracteresPorUsuario.add(new BubbleChartSeries("Marcelo",50,20,50));
		caracteresPorUsuario.add(new BubbleChartSeries("Pimentel",80,25,80));
		caracteresPorUsuario.add(new BubbleChartSeries("Edmilson",110,39,110));
		caracteresPorUsuario.add(new BubbleChartSeries("Zeferino",120,10,100));
		caracteresPorUsuario.add(new BubbleChartSeries("Thiago",160,48,160));
		caracteresPorUsuario.add(new BubbleChartSeries("Paulo",170,70,170));
		caracteresPorUsuario.add(new BubbleChartSeries("Rafael",200,56,200));
		
		return caracteresPorUsuario;
		
	}

	
	public TagCloudModel getTagCloud() {
		
		tagCloud = new DefaultTagCloudModel();  
		tagCloud.addTag(new DefaultTagCloudItem("respostas", "#",15));
		tagCloud.addTag(new DefaultTagCloudItem("questões","#", 14));
		tagCloud.addTag(new DefaultTagCloudItem("questionário","#", 11));  
		tagCloud.addTag(new DefaultTagCloudItem("dados","#", 9));
		tagCloud.addTag(new DefaultTagCloudItem("visualizações","#", 6));
		tagCloud.addTag(new DefaultTagCloudItem("técnica","#", 5));
		tagCloud.addTag(new DefaultTagCloudItem("usuários","#", 5));
		tagCloud.addTag(new DefaultTagCloudItem("perguntas","#", 7));
		tagCloud.addTag(new DefaultTagCloudItem("survey","#", 3));
		tagCloud.addTag(new DefaultTagCloudItem("elaborando","#", 2));  
		tagCloud.addTag(new DefaultTagCloudItem("sistema","#",2));  
		tagCloud.addTag(new DefaultTagCloudItem("análise","#", 2));
		tagCloud.addTag(new DefaultTagCloudItem("colaboração","#", 2));

		return tagCloud;
		
	}
	
	public void setTagCloud(TagCloudModel tagCloud) {
		this.tagCloud = tagCloud;
	}
	public void setMensagensPorUsuario(CartesianChartModel mensagensPorUsuario) {
		this.mensagensPorUsuario = mensagensPorUsuario;
	}
	public void setCaracteresPorUsuario(BubbleChartModel caracteresPorUsuario) {
		this.caracteresPorUsuario = caracteresPorUsuario;
	}
	
	
	

}
