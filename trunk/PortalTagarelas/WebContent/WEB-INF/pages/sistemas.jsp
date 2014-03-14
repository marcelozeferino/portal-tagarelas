<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	
	<script type="text/javascript">
	$(document).ready(
			function() {
				//slides the element with class "menu_body" when paragraph with class "menu_head" is clicked 
				$("#firstpane p.menu_head").click(
						function() {
							$(this).css({
								backgroundImage : "url(<s:url value='/includes/images/down.png'/>)"
							}).next("div.menu_body").slideToggle(300).siblings(
									"div.menu_body").slideUp("slow");
							$(this).siblings().css({
								backgroundImage : "url(<s:url value='/includes/images/left.png'/>)"
							});
						});
				//slides the element with class "menu_body" when mouse is over the paragraph
				$("#secondpane p.menu_head").mouseover(
						function() {
							$(this).css({
								backgroundImage : "url(<s:url value='/includes/images/down.png'/>)"
							}).next("div.menu_body").slideDown(500).siblings(
									"div.menu_body").slideUp("slow");
							$(this).siblings().css({
								backgroundImage : "url(<s:url value='/includes/images/left.png'/>)"
							});
						});
			});
	</script>
	
	<style type="text/css">
		.menu_list {
			width: 550px;
		}
		
		.menu_head {
			padding: 5px 10px;
			cursor: pointer;
			position: relative;
			margin: 1px;
			font-weight: bold;
			/*
					background: #eef4d3 url(images/left.png) center right no-repeat;*/
			background: url(<s:url value='/includes/images/left.png'/>) center right no-repeat;
		}
		
		.menu_body {
			display: none;
		}
		
		.menu_body a {
			display: block;
			color: #696969;
			background-color: #fff;
			padding-left: 10px;
			/*font-weight:bold;*/
			text-decoration: none;
			line-height: 1.5em;
			font-size: 13px;
		}
		
		.menu_body a:hover {
			color: #000000;
			text-decoration: underline;
		}
		
		.menu_body p {
			display: block;
			color: #696969;
			background-color: #fff;
			padding-left: 10px;
			line-height: 1.5em;
			font-size: 13px;
		}
		
		.textGrande {
			font-size: 12px;
			line-height: 2em;
			font-weight: bold;
		}
		
		.textGrande li { /*margin-bottom: 25px;*/
			
		}
		
		a .listaSistemas {
			color: #000;
		}
	</style>
</head>
<body>
	<div id="bannerConteudo">
		<!--<div id="faceCadatro">
				<br clear="all">
				<div id="botaoBannerFace">
				<img src="images/sistemas_min_interna.jpg"/>
				</div>
			</div>-->
		<div id="tituloPrincipal">

			<h2>Sistemas</h2>
			<br /><img src="<s:url value='/includes/images/sistemas_min_interna.jpg'/>" />
			<p align="justify">
				Os <b> Sistemas de Bate-papo </b> disponibilizados neste poral
				foram desenvolvidos para serem usados em atividades educacionais
				específicas. É adequado desenvolver sistemas de bate-papo para
				dinâmicas educacionais específicas. O bate-papo viabiliza a
				interação entre alunos e professor para atividades especificas como
				discutir conteúdo, debater, tirar duvidas, entrevistar ou realizar
				um brainstorm. Cada atividade especifica requer funcionalidades
				diferentes das encontradas em um bate-papo típico. Pesquisas
				realizadas pelo grupo ComunicaTEC na utilização de bate-papo
				mostram que é possível adaptar esse meio para dar melhor suporte a
				uma atividade educacional específica Para você obter um melhor
				resultado, primeiro conheça os diferentes sistemas e a prática
				pedagógica potencializada por cada sistema.
			</p>
			<br clear="all" /> <br clear="all" />
		</div>

		<div id="conteudoPagfull">
			<div id="listaSistemas">
				<!-- Box -->
				<section>
					<ul>
						<!--<li><a href="#" class="botaoGrande botaoGrande-icon botaoGrandecadastrar">Concluir</a></li>-->
						<li><p align="justify">
								&#187; <a href="#tipico">Bate-papo Típico</a>
							</p></li>
						<li><p align="justify">
								&#187; <a href="#debate">Debate-papo</a>
							</p></li>
					</ul>
					<br />
					<br />
					<br />

					<a name='tipico'></a>
					<p class="textGrande">Bate-papo típico</p>
					<ul>
						<li>
							<img src="<s:url value='/includes/images/tipico.jpg'/>" />
							<p align="justify">O típico é o sistema mais comum de bate-papo e o mais
								encontrado em sistemas virtual de aprendizagem. Sua
								característica é de possibilitar os aprendizes falarem a
								qualquer instante, contribuíndo em informações generalizadas. No
								bate-papo tipico as mensagens postadas não são organizadas por
								assunto, cada aluno contribui de forma aleatoria. Desta forma,
								não écontrolado os assuntos que estão sendo tratados..No típico
								os assuntos podem ir surgindo e diversas contribuições podem ser
								postadas sem que haja organização.
							</p>
						</li>
					</ul>
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />



					<a name='debate'></a>
					<p class="textGrande">Debate-papo</p>
					<ul>
						<li>
							<img src="<s:url value='/includes/images/debatepapo.jpg'/>" width="564" height="256" />
							<p align="justify">
								O sistema Debatepapo possui as características básicas de um
								sistema típico de bate-papo e como diferencial implementa os
								mecanismos de visualização do co-texto. Os mecanismos foram
								divididos em duas formas de visualização do co-texto: par
								conversacional e histórico de mensagens. <br /> <br /> Na
								visualização em par conversacional, o participante precisa
								realizar uma associação entre a mensagem que está digitando e
								uma mensagem anterior. No momento que o participante envia a
								nova mensagem aos demais participantes do debate, o par criado
								pela nova mensagem e pela mensagem anterior é exibido na tela. A
								mensagem anterior – mensagem que representa o co-texto – é
								apresentada numa linha antes da mensagem digitada, com o intuito
								de indicar que foi cronologicamente enviada antes. <br /> <br />
								Na visualização do histórico de mensagens, o participante tem a
								possibilidade de ver uma quantidade de mensagens encadeadas além
								do par conversacional. O processo inicial necessário para poder
								observar esse mecanismo é igual ao processo do par
								conversacional: o participante associa a mensagem digitada com
								uma mensagem anterior. A diferença é que se o participante
								associar uma mensagem que possua um par conversacional, a partir
								de agora há uma associação entre três mensagens. Assim, a
								mensagem digitada possuirá uma mensagem co-texto e um histórico
								com duas mensagens anteriores.
							</p>
						</li>
					</ul>
					<br />
					<br />
				</section>
			</div>
		</div>
		<br clear="all" />
	</div>
</body>
</html>