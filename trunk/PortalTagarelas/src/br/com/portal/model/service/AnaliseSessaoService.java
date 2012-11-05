package br.com.portal.model.service;

import br.com.portal.model.vo.AnaliseVO;

/**
 * Classe de servi�o para gera��o de Analise, dada conversa��o selecionada
 * 
 * @author mzeferino
 * 
 */
public class AnaliseSessaoService {

	private String assuntoSessao = "";

	/**
	 * M�todo de fachada para gera��o de an�lise
	 * 
	 * @param assunto
	 * @return AnaliseVO Preenchido com os dados de an�lise gerada
	 */
	public AnaliseVO gerarAnalise(String assunto) {

		this.assuntoSessao = "Metodologia de Pesquisa Cientifica (Elaborando Questionarios)";

		AnaliseVO analise = new AnaliseVO();

		analise.setTitulo(assunto);
		analise.setDuracao(obterDuracao());
		analise.setQtdeMsgsEnviadas(obterQtdeMsgsEnviadas());
		analise.setQtdeParticipantes(obterQtdeParticipantes());
		analise.setTextoProduzido(obterTextoProduzido());

		return analise;

	}

	private int obterDuracao() {
		return 60;
	}

	private int obterQtdeMsgsEnviadas() {
		return 268;
	}

	private int obterQtdeParticipantes() {
		return 7;
	}

	private int obterTextoProduzido() {
		return 870;
	}

}
