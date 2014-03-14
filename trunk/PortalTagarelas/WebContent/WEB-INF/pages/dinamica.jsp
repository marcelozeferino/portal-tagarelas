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
		$(document).ready(function()
			{
				//slides the element with class "menu_body" when paragraph with class "menu_head" is clicked 
				$("#firstpane p.menu_head").click(function()
				{
					$(this).css({backgroundImage:"url(<s:url value='/includes/images/down.png'/>)"}).next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
					$(this).siblings().css({backgroundImage:"url(images/left.png)"});
				});
				//slides the element with class "menu_body" when mouse is over the paragraph
				$("#secondpane p.menu_head").mouseover(function()
				{
					 $(this).css({backgroundImage:"url(<s:url value='/includes/images/down.png'/>)"}).next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp("slow");
					 $(this).siblings().css({backgroundImage:"url(<s:url value='/includes/images/left.png'/>)"});
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
			margin:1px;
			font-weight:bold;
			/*
			background: #eef4d3 url(images/left.png) center right no-repeat;*/
			background: url(<s:url value='/includes/images/left.png'/>') center right no-repeat;
		}
		.menu_body {
			display:none;
		}
		.menu_body a{
		  display:block;
		  color:#696969;
		  background-color:#fff;
		  padding-left:10px;
		  /*font-weight:bold;*/
		  text-decoration:none;
		  line-height: 1.5em;
		  font-size: 13px;
		}
		.menu_body a:hover{
		  color: #000000;
		  text-decoration:underline;
		  }
		.menu_body p{
		  display:block;
		  color:#696969;
		  background-color:#fff;
		  padding-left:10px;
		  line-height: 1.5em;
		  font-size: 13px;
		}		

		
		  
		.textGrande{
			font-size: 12px;
			line-height: 2em;
			font-weight: bold;
		}
		
		.textGrande li{
			/*margin-bottom: 25px;*/
			
		}
		
		a .listaSistemas{
			color: #000;
		}
	</style>
</head>
<body>
	<div id="bannerConteudo">
		<!--
			<div id="faceCadatro">
				<br clear="all">
				<div id="botaoBannerFace">
				<img src="images/sistemas_min_interna.jpg"/>
			</div>
		</div>
		-->
		<div id="tituloPrincipal">
			<h2>Din&acirc;micas</h2>
			<br/>
			<img src="<s:url value='/includes/images/dinamicasInterna.jpg'/>"/>
			<p align="justify"> 
				Nesta se&ccedil;&atilde;o est&atilde;o sugeridas algumas din&acirc;micas educacionais para a realiza&ccedil;&atilde;o de uma aula por meio de bate-papo, 
				tais como: entrevista e debate. Para cada din&acirc;mica, s&atilde;o catalogadas informa&ccedil;ões como: os objetivos educacionais, a descri&ccedil;&atilde;o de 
				todos os passos do processo da din&acirc;mica, os recursos necess&aacute;rios para realizar a din&acirc;mica incluindo a indica&ccedil;&atilde;o de qual 
				sistema de bate-papo espec&iacute;fico deve ser usado, quais os pap&eacute;is dos participantes e como organizar os alunos em grupos para 
				participar da din&acirc;mica, como moderar a sess&atilde;o, o que posteriormente analisar da conversa&ccedil;&atilde;o realizada e como avaliar o desempenho 
				da participa&ccedil;&atilde;o dos alunos. O objetivo &eacute; divulgar um conjunto de pr&aacute;ticas pedag&oacute;gicas para o professor conhecer e escolher 
				quais din&acirc;micas com bate-papo deseja realizar com a sua turma.
			</p>
			<br clear="all"/>
			<br clear="all"/>
		</div>	
		<div id="conteudoPagfull">
			<div id="listaSistemas">
				<!-- Box -->
				<section>
					<ul>
						<!--<li><a href="#" class="botaoGrande botaoGrande-icon botaoGrandecadastrar">Concluir</a></li>-->
						<li><p>&#187; <a href="#entrevista">Entrevista</a></p></li>
						<li><p>&#187; <a href="#debate">Debates</a></p></li>
					</ul>
					<br/><br/><br/>
					<a name='entrevista'></a>
					<p class="textGrande">Entrevista</p>
					<ul>
						<li>
							<p align="justify">
								Entrevista &eacute; uma conversa&ccedil;&atilde;o em que o entrevistador faz perguntas para obter informa&ccedil;&atilde;o do entrevistado.
								A entrevista online &eacute; a realiza&ccedil;&atilde;o de entrevistas geralmente por meio de bate-papo.
							</p>
						</li>
						<br/><br/>
						<li>
							<a href="<s:url value='/dinamicasEntrevista.do'/>" class="botaoGrande botaoGrandeIconPaper botaoGrandeSistemas">Ver mais...</a>
						</li>
					</ul>
					<br/><br/><br/><br/><br/><br/>
					<a name='debate'></a>
					<p class="textGrande">Debates</p>
					<ul>
						<li>
							<p align="justify">
								Debate &eacute; uma discuss&atilde;o entre duas ou mais pessoas que queiram apenas colocar suas ideias em quest&atilde;o. 
								O debate online &eacute; a realiza&ccedil;&atilde;o de debates  por meio eletr&ocirc;nico.
								<br />
    							<br />
   							</p>
   						</li>
				        <br/><br/>
						<li>
							<a href="<s:url value='/dinamicasDebate.do'/>" class="botaoGrande botaoGrandeIconPaper botaoGrandeSistemas">Ver mais...</a>
						</li>
					</ul>
					<br/><br/>
				</section>
			</div>
		</div>
		<br clear="all"/>
	</div>
</body>
</html>