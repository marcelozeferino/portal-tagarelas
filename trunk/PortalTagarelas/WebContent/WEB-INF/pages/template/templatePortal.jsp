<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE HTML>
<html>
	<head>
		<title>Portal Tagarelas</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	
		<script type="text/javascript" src="<s:url value='/includes/js/jquery-1.10.2.js'/>" charset="UTF-8"></script>
		<script type="text/javascript" src="<s:url value='/includes/js/jquery-ui-1.10.4.custom.js'/>" charset="UTF-8"></script>
		<script type="text/javascript" src="<s:url value='/includes/js/jquery.ui.core.js'/>" charset="UTF-8"></script>
		<script type="text/javascript" src="<s:url value='/includes/js/formee.js'/>" charset="UTF-8"></script>
		<script type="text/javascript" src="<s:url value='/includes/js/funcoes_gerais.js'/>" charset="UTF-8"></script>
		<script type="text/javascript" src="<s:url value='/includes/js/jquery.ui.datepicker.js'/>" charset="UTF-8"></script>
		<script type="text/javascript" src="<s:url value='/includes/js/jquery.ui.datepicker-pt-BR.js'/>" charset="UTF-8"></script>
		<script type="text/javascript" src="<s:url value='/includes/js/jquery.ui.slider.js'/>" charset="UTF-8"></script>
		<script type="text/javascript" src="<s:url value='/includes/js/jquery-ui-timepicker-addon.js'/>" charset="UTF-8"></script>
		<script type="text/javascript" src="<s:url value='/includes/js/jquery.toastmessage.js'/>" charset="UTF-8"></script>
		<script type="text/javascript" src="<s:url value='/includes/js/jquery.fancybox.2.1.5.js'/>" charset="UTF-8"></script>
		<script type="text/javascript" src="<s:url value='/includes/js/jquery.fancybox.pack.js'/>" charset="UTF-8"></script>
		<script type="text/javascript" src="<s:url value='/includes/js/jqueryAcordion.js'/>" charset="UTF-8"></script>
		
		<link href='http://fonts.googleapis.com/css?family=Ubuntu|Ubuntu+Condensed' rel='stylesheet' type='text/css' />
		<link rel="stylesheet" href="<s:url value='/includes/css/reset.css'/>" type="text/css" media="screen" title="default" />
		<link rel="stylesheet" href="<s:url value='/includes/css/styleTagarelas.css'/>" type="text/css" media="screen" title="default" />
		<link rel="stylesheet" href="<s:url value='/includes/css/jquery-ui-1.10.4.custom.css'/>" type="text/css" media="screen" title="default" />
		<link rel="stylesheet" href="<s:url value='/includes/css/jquery.ui.core.css'/>" type="text/css" media="screen" title="default" />
		<link rel="stylesheet" href="<s:url value='/includes/css/jquery.ui.datepicker.css'/>" type="text/css" media="screen" title="default" />
		<link rel="stylesheet" href="<s:url value='/includes/css/jquery.ui.slider.css'/>" type="text/css" media="screen" title="default" />
		<link rel="stylesheet" href="<s:url value='/includes/css/jquery-ui-timepicker-addon.css'/>" type="text/css" media="screen" title="default" />
		<link rel="stylesheet" href="<s:url value='/includes/css/formee-structure.css'/>" type="text/css" media="screen" title="default" />
		<link rel="stylesheet" href="<s:url value='/includes/css/formee-style.css'/>" type="text/css" media="screen" title="default" />
		<link rel="stylesheet" href="<s:url value='/includes/css/jquery.toastmessage.css'/>" type="text/css" media="screen" title="default" />
		<link rel="stylesheet" href="<s:url value='/includes/css/jquery.fancybox.css'/>" type="text/css" media="screen" title="default" />
		
		<script type="text/javascript">
			function clickLink() {
				alert("esta no alerta");
			}
	
			$(document).ready(function() {
				"<s:if test='hasActionErrors()'>";
					"<s:iterator value='actionErrors'>";
						showToast('error',"<s:property/>", false);
					"</s:iterator>";
				"</s:if>";
				
				"<s:if test='hasActionMessages()'>";
					"<s:iterator value='actionMessages'>";
						showToast('success',"<s:property/>", false);
					"</s:iterator>";
				"</s:if>";
				
				$('.fancybox').fancybox({
					autoSize: false,
					height: 300,
					closeBtn: true,
					type: 'iframe',
									
					//FUNDO OPACO
					helpers : {
						overlay : {
							css : {
								'background' : 'rgba(58, 42, 45, 0.95)'
							}
						}
					}
				});
								
				exibeMensagemUsuario(
						"notice",
						"Caso n&atilde;o queira alterar sua senha, deixe os campos de senha e confirmar senha em branco!", "divNotificacaoSenha");
	
				$(".datepicker").datetimepicker({
					changeMonth : true,
					changeYear : true,
					currentText : 'Atual',
					closeText : 'OK',
					altField : '.onlytimepicker',
					prevText : '',
					nextText : '',
					timeOnlyTitle : 'Escolha o Tempo',
					timeText : 'Tempo',
					hourText : 'Hora',
					minuteText : 'Minuto',
					secondText : 'Segundo',
					millisecText : 'Milissegundo',
					microsecText : 'Microssegundo'//,
				//onClose: function(){
				//$('#day').val($('.datepicker').val());
				//$('#time').val($('.timepicker').val());
				//alert("Data: " + $('#day').val() + " Hora: " + $('#time').val());
				//}
				//onBeforeShow: function(){
				//	$('.datepicker').datepickerSetDate($('.datepicker').val(), true);
				//}
				});
	
				$(".onlytimepicker").timepicker({
					currentText : 'Atual',
					closeText : 'OK',
					timeOnlyTitle : 'Escolha o Tempo',
					timeText : 'Tempo',
					hourText : 'Hora',
					minuteText : 'Minuto',
					secondText : 'Segundo',
					millisecText : 'Milissegundo',
					microsecText : 'Microssegundo'//,
				//onClose: function(){
				//$('#day').val($('.datepicker').val());
				//$('#time').val($('.timepicker').val());
				//alert("Data: " + $('#day').val() + " Hora: " + $('#time').val());
				//}
				//onBeforeShow: function(){
				//	$('.datepicker').datepickerSetDate($('.datepicker').val(), true);
				//}
				});
			});
		</script>
		<decorator:head />
	</head>

<body>
	<s:if test="#parameters['error'][0] == 1">
		<script type="text/javascript">
		showToast('error',"Usuário e/ou senha inválidos!", false);
		</script>
	</s:if>
	
	<s:elseif test="#parameters['error'][0] == 2">
		<script type="text/javascript">
		showToast('error',"Você precisa estar logado para acessar!", false);
		</script>
	</s:elseif>

	<div id="barraRed">
		<div id="barraConteudo">
			<div id="barraForm">
				<sec:authorize access="isAnonymous()">
					<s:form name="formLogin" id="formLogin" action="j_spring_security_check" method="post" theme="simple">
						<label for="email">E-mail</label>
						<s:textfield name="j_username" tabindex="1" cssClass="caixaTexto"/>&nbsp;
						<label for="senha">Senha</label>
						<s:password name="j_password" tabindex="2" cssClass="caixaTexto"/>
						<label class="botoes" for="botaoEntrar">
							<input type="submit" class="submit-login" value="Entrar" tabindex="3" id="botaoEntrar"/>
						</label>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="<s:url value='/reenviarSenha.do'/>" class="fancybox fancybox.iframe">Esqueci minha senha</a>
						<!-- <label for="mantenhaConectado"><input class="Checkbox" name="mantenhaConectado" value="1" tabindex="4" type="checkbox"/>Mantenha-me conectado</label> -->
						<!--&nbsp;|&nbsp; <a class="linkBranco" rel="nofollow" href="#">Esqueceu sua senha?</a>		-->
						<!--<label class="botoes botoesFace" for="botaoFacebook"><input value="Logar com Facebook" id="botaoFacebook" tabindex="6" type="submit"/></label>-->
						<!--  inicio  -->
					</s:form>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<ul id="nav">
						<li>
							<img src="<s:property value="#session.usuarioLogado.foto"/>" style="height: 30px; width: 30px; " border="0"/>
							<s:property value="#session.usuarioLogado.nome"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="<s:url value='/alteracaoUsu.do'/>">Alterar Cadastro</a>
							&nbsp;|&nbsp;
							<a href="<s:url value='/j_spring_security_logout' />">Sair</a>
						</li>
					</ul>
				</sec:authorize>
			</div>

		</div>
	</div>
	<!--	</div> -->

	<div id="container">
		<div id="containerConteudo">
			<header>
				<div id="logo">
					<!-- style="border: 1px solid #00ff00;" -->
					<a href="<s:url value='/index.do'/>">
						<img src="<s:url value='/includes/css/images/logofinalTaga.png'/>" alt="Portal Tagarelas" title="Portal Tagarelas" />
					</a>
					<!--<h1>Tagarelas Logo</h1>-->
				</div>

				<div id="menuPrinc">
					<nav id="navTagarelas">
						<ul>
							<sec:authorize access="isAuthenticated()">
								<li>
									<a href="<s:url value='/listaMinhaSessao.do'/>" class="tagarelas">
										Minhas Sess&otilde;es<br>
										<div class="tabActived" id="activedTabTagarelas"></div>
									</a>
								</li>
							</sec:authorize>
							
							<li>
								<a href="<s:url value='/dinamica.do'/>" class="dynamics">
									din&acirc;micas<br>
									<div id="tabDynamics"></div>
								</a>
							</li>
							<li>
								<a href="<s:url value='/sistemas.do'/>" class="system">
									sistemas<br>
									<div id="tabSystem"></div>
								</a>
							</li>
							<li>
								<a href="<s:url value='/listaAgenda.do'/>" class="agenda">
									agenda<br>
									<div id="tabAgenda"></div>
								</a>
							</li>
							<li>
								<a href="<s:url value='/listaArquivo.do'/>" class="file">
									arquivo<br>
									<div class="tabFile" id="tabFile"></div>
								</a>
							</li>
							<li>
								<a href="<s:url value='/publicacao.do'/>" class="publication">
									publica&ccedil;&otilde;es<br>
									<div id="tabPublication"></div>
								</a>
							</li>
							
							<!--<li><h:link outcome="analise"  class="analysis" >anÃ¡lise<br></br><div id="tabAnalysis"></div></h:link></li> -->
						</ul>
					</nav>
				</div>		
			</header>
			<div id="divMensagemRetorno" class="divMensagemRetorno"></div>
			<decorator:body />
		</div>
	</div>
	<div id="rodape">
		<footer>
			<div id="rodapeConteudo">
				<section> <!-- class="widget-contact last" -->
				<h2>Portal Tagarelas - Grupo ComunicaTec</h2>
				<ul>
					<!--<li><a href="#" class="facebook">Facebook</a></li>-->
					<li><iframe
							src="http://www.facebook.com/plugins/like.php?href=https://www.facebook.com/portaltagarelas&amp;layout=standard&amp;show_faces=false&amp;width=380&amp;action=like&amp;colorscheme=light&amp;height=25&amp;locale=pt_BR"
							scrolling="no" frameborder="0"
							style="color: #fff !important; border: none; overflow: hidden; width: 250px; height: 25px;"
							allowTransparency="true"></iframe></li>

					<li><a href="https://plus.google.com/101160155498650278721"
						rel="publisher">Nossa Página no Google+</a>
					</li>
					<!--<li><a href="#" class="dribbble">Dribbble</a></li>-->
					<!--<li><a href="#" class="googleplus">Google+</a></li>-->
				</ul>

				<br />
				<br />
				</section>

			</div>
		</footer>
	</div>
</body>
</html>