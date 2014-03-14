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
	<script src="<s:url value='/includes/js/tooltip.js'/>"></script>

	<script type="text/javascript">
		function pegaNomeCurto() {
			var str = document.getElementById('input-nome').value;
			var apelido = document.getElementById('input-sobrenome').value;
			
			if ( apelido==""){
				var n=str.split(" ",1);
				document.getElementById('input-sobrenome').value = n;
			}
							
		
		}
	</script>

	<link rel="stylesheet" href="<s:url value='/includes/css/jquery.Jcrop.css'/>" type="text/css" />
	<link rel="stylesheet" href="<s:url value='/includes/css/tooltip.css'/>" type="text/css" />
	
	<style type="text/css">
		.step2, .error {
			display: none;
		}
		.error {
			font-size: 18px;
			font-weight: bold;
			color: red;
		}
		.info {
			font-size: 14px;
		}
		.textGrande{
			font-size: 14px;
			font-weight: bold;
			
		}
	</style>
</head>
<body>
	<div id="bannerForm">
		<div id="bannerConteudo">
			<div id="faceCadatro">
				<br clear="all"/>
				<div id="botaoBannerFace">
					<img src="<s:url value='/includes/images/cadastro_peq2.jpg'/>" id="target" />
				</div>
			</div>
			<div id="formCadastro">
				<h2>Cadastro</h2>
				<br/>
				<s:form id="formCadastroUsuario" name="formCadastroUsuario" method="POST" action="/cadastrarUsuario.do" theme="simple" enctype="multipart/form-data">
					<div class="error"></div>
	  				<br/><br/>
  					<div id="inputs">
                    	<label for="foto">Foto</label><br/>
						<s:file name="fotoUsuario" id="upload" onchange="previewImagem(this)" cssClass="tooltip imagem" rel="<s:url value='/includes/images/sem-imagem.jpg'/>"/><br/>
 						
 						<label for="nomeCompleto">Nome Completo *</label><br/>
						<s:textfield id="input-nome" name="usuario.nome" cssClass="inputGrande" title="O nome deve ter pelo menos 5 caracteres." required="true" onBlur="pegaNomeCurto()" maxlength="200"/>
							
						<label for="apelido">Nome Curto *</label><br/>
						<s:textfield id="input-sobrenome" name="usuario.sobrenome" cssClass="inputGrande" required="true"/>
								
						<label for="email">Email *</label><br/>
						<s:textfield id="input-email" name="usuario.email" title="seunome@dominio.com" cssClass="inputGrande" required="true"/>
								
						<span class="restricoes">Informe o e-mail que você deseja utilizar para acessar o Portal Tagerelas</span>
									
						<label for="senha">Senha *</label><br/>
						<s:password id="input-senha" name="usuario.senha" cssClass="inputGrande" required="true"/><br/><br/>
  									
  						<span class="restricoes">Escolha uma senha de 8 a 15 caracteres.<br/>Recomendamos combinar letras minúsculas com maiúsculas e números. <br/>Porém não utilize caracteres especiais como *, $, %, #, etc. <br/></span>
  									
  						<label for="confirmaSenha">Confirmar a senha *</label><br/>
  						<s:password id="input-confirmaEmail" name="confirmarSenha" cssClass="inputGrande" required="true"/><br/><br/>
  						<br clear="all"/>
  					</div>
  								
  					<div id="botaoBannerFace">
  						<s:submit cssClass="botaoGrande botaoGrande-icon botaoGrandecadastrar" id="submit" value="Concluir"/>
					</div>
				</s:form>
			</div>		
		</div>
		<br clear="all"/>
	</div>
</body>
</html>