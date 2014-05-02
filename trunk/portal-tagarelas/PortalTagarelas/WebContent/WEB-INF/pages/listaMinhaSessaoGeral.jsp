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

	<script src="<s:url value='/include/js/jquery.min.js'/>"></script>
	<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			/*$("#firstpane p.menu_head").click(function()
			{
				$(this).css({backgroundImage:"url(images/down.png)"}).next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
				$(this).siblings().css({backgroundImage:"url(images/left.png)"});
			});*/
	
			$('p.menu_head').click(function() {
				$(this).parent().find('div.menu_body').slideToggle('slow');
			});
			
			$("#cadastroTagarelas :input").tooltip({

				// place tooltip on the right edge
				position : "center right",

				// a little tweaking of the position
				offset : [ -2, 10 ],

				// use the built-in fadeIn/fadeOut effect
				effect : "fade",

				// custom opacity setting
				opacity : 0.7

			});
	
		});
	</script>
	
	<link rel="stylesheet" href="<s:url value='/include/css/jquery.Jcrop.css'/>" type="text/css" />
	<style type="text/css">
		.menu_list {
			width: 900px;
			padding-bottom: 20px;
			margin-bottom: 15px;
			border-bottom: 1px solid #666;
		}
		
		.menu_head {
			padding: 5px 10px;
			cursor: pointer;
			position: relative;
			margin: 1px;
			font-weight: normal;
			background: url(images/down.png) center right no-repeat;
		}
		
		.menu_body {
			display: none;
		}
		
		.menu_body a {
			display: inline;
			background-color: #666;
			padding-left: 10px;
			text-decoration: none;
			line-height: 1.5em;
			font-size: 15px;
		}
		
		.menu_body a:hover {
			
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
			font-size: 16px;
			font-weight: bold;
		}
		
		a .listaSistemas {
			color: #000;
		}
	</style>	
</head>
<body>
	<div id="bannerConteudo">
		<div id="tituloPrincipal">
			<h2>Agenda</h2>
			<br /> <img src="<s:url value='/includes/images/agendapeqInterna.jpg'/>" />
			<p align="justify">Nesta se&ccedil;&atilde;o est&atilde;o
				listadas as minhas sess&otilde;es de bate-papo agendadas pelo
				Portal Tagarelas. Cada sess&atilde;o planejada pelo professor
				&eacute; divulgada nessa agenda p&uacute;blica. Por exemplo, o
				professor pode agendar uma entrevista com algum especialista no
				assunto a ser trabalhado com a turma. A a&ccedil;&atilde;o do
				professor agendar uma sess&atilde;o contribui para o planejamento
				das atividades educacionais online a serem realizadas na
				disciplina. A agenda tamb&eacute;m induz o professor a conhecer
				como o portal est&aacute; sendo usado antes que ele planeje
				sess&otilde;es em sua disciplina. O objetivo &eacute; divulgar os
				pr&oacute;ximos eventos e com isso tamb&eacute;m estimular um
				professor a agendar a sua pr&oacute;pria sess&atilde;o.</p>
			<br />
		</div>
		<br /> <br />
		<div id="conteudoPagfull">
			<div id="listaSistemas">
				<section>
					<p class="textGrande">
						Minhas Sessões<br />
						<br />
					</p>
					<div id="sessoesAgendadas">
						<s:iterator value="listaAgendasDisponiveis" var="agenda">
							<div class="menu_list">
								<p class="menu_head">
									<s:date name="data" format="dd/MM/yyyy"/>
									&nbsp;|&nbsp;
									<s:date name="hora" format="HH:mm:ss"/>
									&nbsp;|&nbsp;
									<s:property value="assunto"/>
									
									<s:if test="idSistema == 0">
										<s:set var='url' value="%{'/chatTipico.do'}"/>
									</s:if>
									<s:if test="idSistema == 5">
										<s:set var='url' value="%{'/chatTipicoFullFuncionando.do'}"/>
									</s:if>
									
									<s:form method="POST" action="%{url}" theme="simple" id="formAcesso_%{idAgenda}" name="formAcesso_%{idAgenda}">
										<s:hidden id="idAgenda" name="agenda.idAgenda" value="%{idAgenda}"/>
										<s:hidden id="assunto" name="agenda.assunto" value="%{assunto}"/>
										<s:hidden id="descricao" name="agenda.descricao" value="%{descricao}"/>
										<s:hidden id="data" name="agenda.data" value="%{data}"/>
										<s:hidden id="hora" name="agenda.hora" value="%{hora}"/>
										<s:hidden id="idSistema" name="agenda.idSistema" value="%{idSistema}"/>
										<s:hidden id="idUsuario" name="agenda.usuario.idUsuario" value="%{usuario.idUsuario}"/>
										<s:hidden id="breveDescricao" name="agenda.breveDescricao" value="%{breveDescricao}"/>
										<s:hidden id="arquivo" name="agenda.arquivo" value="%{arquivo}"/>
										<s:hidden id="dataHora" name="agenda.dataHora" value="%{dataHora}"/>
										<s:hidden id="tempoSessao" name="agenda.tempoSessao" value="%{tempoSessao}"/>
										<s:hidden id="observacao" name="agenda.observacao" value="%{observacao}"/>
										
										<a href="#" onclick="document.formAcesso_<s:property value="idAgenda"/>.submit();" class="botaoGrande botaoGrande-icon botaoGrandeIconPaper botaoGrandeAgenda">
											Acessar
										</a>
									</s:form>
								</p>
								<div class="menu_body">
									<p align="justify">
										<br />
										<strong>Descricao: </strong>
										<s:property value="descricao"/>
										<br />
										<br />
									</p>
								</div>
							</div>
						</s:iterator>
					</div>
					<!-- sessoesAgendadas -->
				</section>
			</div>
			<!-- listaSistemas -->
		</div>
		<!-- conteudoPagfull -->
		<br clear="all" />
	</div>
	<!-- bannerConteudo -->
</body>
</html>