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
	<div id="bannerForm">
		<div id="bannerConteudo">
			<div id="formCadastro">
				<h2>Agendar uma Sessão</h2>
				<br/>
				<s:form id="formCadastrarSessao" name="formCadastrarSessao" method="POST" action="/cadastrarAgenda.do" theme="simple">
					<div id="inputs">
						<label for="nomeCompleto">Título da Sessão *</label><br/>
						<s:textfield maxlength="200" title="O título deve ter pelo menos 5 caracteres." cssClass="inputGrande" id="input-assunto" name="agenda.assunto" required="true"/>
						<br/><br/>
	
						<label for="apelido">Descrição *</label><br/>
						<s:textarea id="campo-descricao" name="agenda.descricao" required="true" rows="4" cols="50"  cssClass="inputGrande" style="height: 200px" title="A descrição é o local onde você pode falar com detalhes sobre o objetivo da sessão." maxlength="500"/>
						<br/><br/>
						
						<label for="apelido">Observa&ccedil;&atilde;o</label><br/>
						<s:textarea id="campo-observacao" name="agenda.observacao" rows="4" cols="50"  cssClass="inputGrande" style="height: 200px" title="A descrição é o local onde você pode falar com detalhes sobre o objetivo da sessão." maxlength="500"/>
						<br/><br/> 
	                                    
						<label for="dia">Dia *</label><br/>
						<s:textfield id="date" cssClass="datepicker" title="Especifique um dia para a sessão" required="true" name="dia"/>
						<br/><br/>
	                                
						<label for="hora">Hor&aacute;rio *</label><br/>
						<s:textfield id="time" cssClass="onlytimepicker" title="Especifique um dia para a sessão" required="true" name="hora"/>
						<br/><br/>

	                    <label for="sistema">Sistema *</label>
	                    <br/><br/>
						
						<s:select list="#{'0':'Tagarelas', '5':'DebatePapo'}" name="agenda.idSistema" required="true" cssClass="inputGrande" style="height: 20px;" title="Escolha um sistema para realizar a sua sessão"/>
						<br/><br/>
					</div>
					<div id="botaoBannerFace">
						<ul>
							<li>
								<s:submit id="submit" value="Cadastrar Agenda"  cssClass="botaoGrande botaoGrande-icon botaoGrandecadastrar" style="cursor: pointer"/>
							</li>
						</ul><!-- class="button  button-alt button-icon button_face button-icon-face"-->
					</div>
				</s:form>
			</div>
		</div>
		<br clear="all"/>
	</div>
</body>
</html>
