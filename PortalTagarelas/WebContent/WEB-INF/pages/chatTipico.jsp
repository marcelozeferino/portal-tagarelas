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
	
		<link type="text/css" rel="stylesheet" media="all" href="<s:url value='/includes/css/style.css'/>" charset="utf-8" /> 
		<link type="text/css" rel="stylesheet" media="all" href="<s:url value='/includes/css/$.css'/>" charset="utf-8" /> 
		<link type="text/css" rel="stylesheet" media="all" href="<s:url value='/includes/css/jquery.ui.button.css'/>" charset="utf-8" />
		
		<style type="text/css">
			body, html {
				margin: 0px;
				padding: 0px;
				height: 100%;
				width: 100%;
				font-size: 11px;
				font-family: 'Lucida Grande', Verdana, Arial;
			}
			#arrowchat_topo {
				background: #fff url(<s:url value='/includes/images/logo_tagarelas.png'/>) no-repeat; 
				/* padding-left: 225px; */
				padding-top: 10px;
				font-family: Calibri;
				font-size: 17px;
				/* color: #FF5324;*/
				color: #000;
				}
			#digitacao {
				vertical-align: top; 
				transition: height 0.2s;
				-webkit-transition: height 0.2s; 
				-moz-transition: height 0.2s; 
			}
			
			#chat {
				height: 70%;
				
			}
		</style>
		
		<script type="text/javascript" src="<s:url value='/includes/js/jquery-1.10.2.js'/>" charset="UTF-8"></script>
		<script type="text/javascript" src="<s:url value='/includes/js/jquery-ui-1.10.4.custom.js'/>" charset="UTF-8"></script> 
		<script type="text/javascript" src="<s:url value='/includes/js/jquery.autosize.js'/>"></script>
		<script type="text/javascript" src="<s:url value='/includes/js/jquery.emotions.js'/>"></script>
		<script type="text/javascript" src="<s:url value='/includes/js/ui/jquery.ui.dialog.js'/>"></script>
		<!--<script type="text/javascript" src="js/jqModal.js"></script>-->
		<script type="text/javascript" src="<s:url value='/includes/js/funcoes_gerais.js'/>" charset="UTF-8"></script>
		
		<script type="text/javascript">
			var flag = true;
			var horaF = "";
			var shift = false;
			var comecou = false;
			var tempoIni = 0;
			var caracter = 0;
			var texto = "";
			var tm = 0;
			var tempoFim = 0;
			var flagEnviar = false;
		
			function recuperaListaUsuariosAjax(){
				var html = "";
				$.ajax({ 
					cache:'false',
						type:"POST",
						url:"<s:url value='/ajax/retornaListaUsuariosPorAgendaAjax.do'/>?agenda.idAgenda=<s:property value='agenda.idAgenda'/>",
						success:function(listaUsuarios){
							$(listaUsuarios).each(function(i, usuario) {
								html += "<div class=\"arrowchat_userlist\">";
								html += "	<img src=\""+usuario.foto+"\"  class=\"arrowchat_userlist_avatar\" width=\"30\" height=\"30\"/>";
								html += "	<span class=\"arrowchat_userscontentname\">";
								html += "		<strong>"+usuario.nome+"</strong>";
								html += "	</span>";
								html += "	<span class=\"arrowchat_userscontentdot arrowchat_available\"></span>";
								html += "</div>";
							});
							
							$('#arrowchat_userslist_available').html(html);
						},
						erro:function(){
							alert("erro no servidor");
						}
				 });
			}
			
			function enviarMensagemUsuarioTipicoAjax(){
				var param = $("#enviarMensagemForm").serialize();
				param += "&texto="+$("#digitacao").val();
				$.ajax({ 
					cache:'false',
						type:"POST",
						url:"<s:url value='/ajax/enviarMensagemUsuarioTipicoAjax.do'/>",
						data:param,	
						success:function(){
							document.enviarMensagemForm.msgTempoTotal.value = "";
							document.enviarMensagemForm.msgTempo.value = "";
							flag = true;
							
							$("#digitacao").val("");
							$("#digitacao").focus();
						},
						erro:function(){
							alert("erro no servidor");
						}
				 });
			}
			
			function retornaMensagensPorAgendaAjax(){
				$.ajax({ 
					cache:'false',
						type:"POST",
						url:"<s:url value='/ajax/retornaMensagensPorAgendaAjax.do'/>?agenda.idAgenda=<s:property value='agenda.idAgenda'/>",
						success:function(mensagem){
							var tamanhoTotalDiv = $('#janela_chat')[0].scrollHeight;
							var tamanhoExibido = $('#janela_chat').height();
							var tamanhoScroll = $('#janela_chat').scrollTop();
							
							$('#janela_chat').html(mensagem);
							
							if(tamanhoTotalDiv == parseInt(parseInt(tamanhoExibido)+parseInt(tamanhoScroll))){
								$('#janela_chat').scrollTop(tamanhoTotalDiv);
							}else{
								$('#janela_chat').scrollTop(tamanhoScroll);
							}
						},
						erro:function(){
							alert("erro no servidor");
						}
				 });
			}
			
			function sairDaSessaoAjax(){
				var html = "";
				$.ajax({ 
					cache:'false',
						type:"POST",
						url:"<s:url value='/ajax/sairDaSessaoAjax.do'/>?agenda.idAgenda=<s:property value='agenda.idAgenda'/>",
						async:false,
						success:function(mensagem){
							$('#janela_chat').html(mensagem);
							return 'OK';
						},
						erro:function(){
							alert("erro no servidor");
						}
				 });
			}
			
			setInterval(function() { recuperaListaUsuariosAjax(); }, 1000);
			setInterval(function() { retornaMensagensPorAgendaAjax(); }, 1000);
			
			window.unload = window.beforeunload = sairDaSessaoAjax;
			
			function formataHora(h){
				var horas = h.getHours();
				var minutos = h.getMinutes();
				var segundos = h.getSeconds();
				var milisegundos = h.getMilliseconds();
				var hf;
				
				hf = horas + ":" + minutos + ":" + segundos + ":" + milisegundos;
				return hf;
			}
			
			function enviarMensagemEnter(e){
				if(window.event) { // IE
					keynum = e.keyCode;
				}else if(e.which) { // Netscape/Firefox/Opera
					keynum = e.which;
				}
				if (keynum == 13){
					enviarMensagemUsuarioTipicoAjax();
				}
			}
			
			function VerificaKeyDown(e){
				var keynum;
				
				if(window.event) { // IE
					keynum = e.keyCode;
				}else if(e.which) { // Netscape/Firefox/Opera
					keynum = e.which;
				}
			
				if (keynum == 16){
					shift = true;
				}
				
				var textoDigitacao = $("#digitacao").val();
				texto = textoDigitacao.replace(/ /gi, String.fromCharCode(183));
					
				if (keynum == 8) {
					caracter = "BACKSPACE";
				}else if (keynum == 36) {
					caracter = "HOME";
				}else if (keynum == 45) {
					caracter = "INSERT";
				}else if (keynum == 46) {
					caracter = "DELETE";
				}else if (keynum == 35) {
					caracter = "END";
				}else if (keynum == 39) {
					caracter = "SETA DIR";
				}else if (keynum == 37) {
					caracter = "SETA ESQ";
				}else if (keynum == 222) {
					if (shift) {
						caracter = "^";
					}else{
						caracter = "~";
					}
				}else if (keynum == 219) {
					if (shift) {
						caracter = "`";
					}else {
						caracter = "´";
					}
				}
			
				// se for verdadeiro comecei agora a digitar e nao dei o enviar ainda
				if(flag){
					var horaT = new Date;
					tempoIni = horaT;
					flag = false;
				}
				var hora = new Date;
				tempoFim = new Date;
				//if (tempoIni == 0){
				//	tempoIni = hora;
				//}

				horaF = formataHora(hora);

				if (caracter != ""){
					ExibePagina();
				}
			}
			
			function VerificaKeypress(e){
				if(window.event) { // IE
					keynum = e.keyCode;
					
				}else if(e.which) { // Netscape/Firefox/Opera
					keynum = e.which;
					
				}
				
				caracter = String.fromCharCode(keynum);
				
				if (caracter == " "){
					caracter = String.fromCharCode(183);
				}
				ExibePagina();
			}
			
			function VerificaKeyUp(e){
				var keynum;
				if(window.event) { // IE
					keynum = e.keyCode;
				}else if(e.which) { // Netscape/Firefox/Opera
					keynum = e.which;
				}
				if (keynum == 16 ) shift = false;
			}
			
			function ExibePagina(){
				
				if (!comecou){
					
					document.getElementById("chatList").innerHTML = "";
					document.enviarMensagemForm.msgTempoTotal.value = "";
					document.enviarMensagemForm.msgTempo.value = "";
				}
				
				comecou = true;
				if (shift){
					caracter = "SHIFT + " + caracter;
				}
				
				document.getElementById("chatList").innerHTML +=  texto + ", " + caracter + ", " + horaF;
				document.getElementById("msgTempo").value += "[" + texto + ", " + caracter + ", " + horaF +  "],";
				caracter = "";
				document.getElementById("chatList").innerHTML = "";
				document.getElementById("chatList").innerHTML = "tempo de escrita: " + tm + " segundos";
				tm = (tempoFim - tempoIni)/1000;
				enviarMensagemForm.msgTempoTotal.value = tm;
				caracter = "";
			}
		</script>
	</head>
	<body onload="$('#digitacao').focus();">
		<div id="arrowchat_sound_player_holder"></div>

		<div id="arrowchat_popout_wrapper">
			<div id="arrowchat_topo">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<strong><s:property value='agenda.assunto'/></strong>
				<span id="sair" style="position: absolute; right: 6px;">
					<s:form id="sairSessao" name="sairSessao" method="POST" action="/sairDaSessao.do">
						<a href="#" onclick="document.sairSessao.submit()">
							<s:hidden id="idAgenda" name="agenda.idAgenda" value="%{agenda.idAgenda}"/>
							<s:hidden id="idSistema" name="agenda.idSistema" value="%{agenda.idSistema}"/>
							<img src="<s:url value='/includes/images/fechar.jpg'/>" border="0" ></img>
						</a>
					</s:form>
				</span>
			</div>
			
			<div id="arrowchat_popout_left">
				<div id="arrowchat_popout_friends">
					<div id="arrowchat_userslist_available"></div>
					<div id="arrowchat_userslist_busy"></div>
					<div id="arrowchat_userslist_away"></div>
					<div id="arrowchat_userslist_offline"></div>
				</div>
			</div>
			<div id="chatList" style="display: none;"></div>
			<div id="arrowchat_popout_right" style="height: 250px; ">
				<div id="janela_chat" style="height: 77%;"></div>
				<!-- janela_chat -->
				
				<div id="arrowchat_digita">
					<div class="arrowchat_popout_input_container">
						<div class="arrowchat_popout_input_wrapper">
							<s:form name="enviarMensagemForm" id="enviarMensagemForm">
								<s:hidden id="msgTempo" name="msgTempo"/>
								<s:hidden id="msgTempoTotal" name="msgTempoTotal"/>
								<s:hidden id="idMensagemPai" name="idMensagemPai" value="0"/>
								<s:hidden id="nivelResposta" name="nivelResposta" value="0"/>
								<s:hidden id="idAgenda" name="agenda.idAgenda" value="%{agenda.idAgenda}"/>
								<s:hidden id="idSistema" name="agenda.idSistema" value="%{agenda.idSistema}"/>
							</s:form>
							
							<s:textarea id="digitacao" cssClass="arrowchat_popout_convo_input" name="texto" onkeypress="VerificaKeyDown(event);VerificaKeypress(event);" onkeyup="VerificaKeyUp(event);" onKeyDown="VerificaKeyDown(event);enviarMensagemEnter(event);"/>
						
							<div id="submit" style="float: right; display: block">
								<input type="button" id="btn" value="Enviar" class="ui-button ui-widget ui-state-default ui-corner-all" onclick="enviarMensagemUsuarioTipicoAjax();"/>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
</html>