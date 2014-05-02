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
	<title>Esqueci minha senha</title>
	<script type="text/javascript" src="<s:url value='/includes/js/jquery-1.10.2.js'/>"></script>
	<script type="text/javascript" src="<s:url value='/includes/js/jquery-ui-1.10.4.custom.js'/>"></script>
	<script type="text/javascript" src="<s:url value='/includes/js/formee.js'/>"></script>
	<script type="text/javascript" src="<s:url value='/includes/js/funcoes_gerais.js'/>"></script>
	
	<link rel="stylesheet" href="<s:url value='/includes/css/reset.css'/>" />
	<link rel="stylesheet" href="<s:url value='/includes/css/styleTagarelas.css'/>" />
	<link type="text/css" rel="stylesheet" media="all" href="<s:url value='/includes/css/formee-structure.css'/>" charset="utf-8" />
	<link type="text/css" rel="stylesheet" media="all" href="<s:url value='/includes/css/formee-style.css'/>" charset="utf-8" />
	
	<script type="text/javascript">
		$(document).ready(function() {
			"<s:if test='hasActionErrors()'>";
				"<s:iterator value='actionErrors'>";
				exibeMensagemUsuario('error',"<s:property/>", 'divMensagemRetorno');
				"</s:iterator>";
			"</s:if>";
			
			"<s:if test='hasActionMessages()'>";
				"<s:iterator value='actionMessages'>";
				exibeMensagemUsuario('success',"<s:property/>", 'divMensagemRetorno');
				"</s:iterator>";
			"</s:if>";	
		});
	</script>
</head>

<body>
	<div id="bannerConteudo">
		<div id="formCadastro">
			<div id="divMensagemRetorno"></div>
			
			<h2>Esqueci minha senha</h2>
			<br/>
			<s:form id="formReenviarSenha" name="formReenviarSenha" method="POST" action="/enviarSenhaParaUsuario.do">
				<div id="inputs">
					Email: <s:textfield id="input-email" title="seunome@dominio.com" cssClass="inputGrande" name="usuario.email"/>
				</div>
				<div id="botaoBannerFace">
					<s:submit cssClass="botaoGrande botaoGrande-icon botaoGrandecadastrar" id="submit" type="submit" value="Enviar Senha"/>
				</div>
			</s:form>
		</div>
	</div>
</body>
</html>