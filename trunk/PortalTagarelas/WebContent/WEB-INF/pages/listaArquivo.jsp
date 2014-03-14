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
	
	<script src="<s:url value='/includes/js/jquery.min.js'/>"></script>
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
	
	<link rel="stylesheet" href="<s:url value='/includes/css/jquery.Jcrop.css'/>" type="text/css" />
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
			<h2>Arquivo</h2>
			<br /> <img src="<s:url value='/includes/images/agendapeqInterna.jpg'/>" />
			<p align="justify">Nesta encontram-se todas as sess&otilde;es de bate-papo
				j&aacute; realizadas pelo portal. Qualquer usu&aacute;rio pode
				consultar o registro (log) de um bate-papo para ler as mensagens
				trocadas naquela sess&atilde;o. Tamb&eacute;m pode consultar a
				an&aacute;lise do log de cada se&ccedil;&atilde;o, sendo
				encaminhado para um relat&oacute;rio em que s&atilde;o
				apresentados dados extra&iacute;dos do log e algumas
				an&aacute;lises autom&aacute;ticas sobre esses dados. Para o
				professor, consultar o arquivo das sess&otilde;es realizadas no
				portal, por meio da recupera&ccedil;&atilde;o e da an&aacute;lise
				da conversa&ccedil;&atilde;o em cada log, &eacute; &uacute;til
				para aprender mais sobre as pr&aacute;ticas de cada
				din&acirc;mica.</p>
			<br />
		</div>

		<div id="conteudoPagfull">
			<div id="listaSistemas">
				<section>
					<p class="textGrande">
						Minhas Sessões Arquivadas<br />
						<br />
					</p>
					<div id="sessoesAgendadas">
						<s:iterator value="listaAgendasArquivadas" var="agenda">
							<div class="menu_list">
								<p class="menu_head">
									<s:date name="data" format="dd/MM/yyyy"/>
									&nbsp;|&nbsp;
									<s:date name="hora" format="HH:mm:ss"/>
									&nbsp;|&nbsp;
									<s:property value="assunto"/>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
								<p class="menu_body">
									<p align="justify">
										<br />
										<strong>Descri&ccedil;&atilde;o: </strong>
										<s:property value="descricao"/>
										<br />
										<br />
									</p>
								</p>
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