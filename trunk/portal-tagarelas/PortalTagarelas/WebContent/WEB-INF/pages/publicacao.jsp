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
</head>
<body>
	<div id="bannerConteudo">
		<div id="tituloPrincipal">
			<h2>Publicações</h2>
			<p class="subtitleBox">&nbsp;</p>
			<div class="inner">
				<ul class="sessoes">
					<li><a
						href="<s:url value='/publicacao/2012.ABCiber.BatepapoEducacao.v11.pdf'/>">Sistemas
							para promover a cultura de bate-papo na Educação</a></li>
					<li><a href="<s:url value='/publicacao/portal tagarelas.pdf'/>">Tagarelas:
							Portal de Bate-papo para Educação</a></li>
					<li><a
						href="<s:url value='/publicacao/2011.Dissertacao.Azevedo.Finalv02.docx'/>">TABSCHAT:
							ORGANIZANDO OS ASSUNTOS PARA UM DEBATE EDUCACIONAL</a></li>
					<li><a href="<s:url value='/publicacao/Dissertacao.ElberthMoraes.pdf'/>">DEBATEPAPO:
							SEQUÊNCIAS CONVERSACIONAIS E VISUALIZAÇÃO DO CO- TEXTO PARA
							COMPREENSÃO DA CONVERSAÇÃO EM BATE-PAPO</a><br />
					</li>
				</ul>
			</div>
		</div>
		<br clear="all" />
	</div>
</body>
</html>