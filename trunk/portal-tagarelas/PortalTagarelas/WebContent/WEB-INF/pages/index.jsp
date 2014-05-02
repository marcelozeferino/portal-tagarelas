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
	<br clear="all" />
	<div id="banner">
		<div id="bannerConteudo">
			<div id="botoesBanner">
				<h2>Torne a sua aula mais interativa!</h2>
				<p>
					- Escolha uma
					<a href="<s:url value='/dinamica.do'/>">Din&acirc;mica&nbsp;</a>
					para realizar com a sua turma.
				</p>
				<p>
					-
					<a href="<s:url value='/agendar.do'/>">Agende</a>
					uma sessão com o uso de um&nbsp;
					<a href="<s:url value='/sistemas.do'/>">sistemas</a>
					de bate-papo.
				</p>
				<!--<p>3º -<h:link outcome="analise">Analise</h:link> uma sessão <a href="arquivos.html">arquivada</a> que você realizou.</p><br/> -->

				<br clear="all" />

				<div id="botaoBannerFace">
					<ul>
						<li>
							<sec:authorize access="isAnonymous()">
								<a href="<s:url value='/cadastroUsu.do'/>" class="botaoGrande botaoGrande-icon botaoGrandecadastrar">
									Cadastre-se &nbsp;&nbsp;&nbsp;
								</a>
							</sec:authorize>
							
							<!-- <a href="#" class="botaoGrande botaoGrandeAlternativo botaoGrande-icon botaoGrande-icon-face">Logar com</a>
                              </li>-->
						</li>
						
					</ul>
					<!-- class="button  button-alt button-icon button_face button-icon-face"-->
				</div>
			</div>
			<div id="fotoBanner">
				<img src="<s:url value='/includes/css/images/img2_med.png'/>"/> <br clear="all" />
			</div>

		</div>
		<br clear="all" />
	</div>
	<div id="boxes">
		<div id="boxesConteudo">
			<div id="tagaBoxDinamicas">
				<section>
					<a href="<s:url value='/dinamica.do'/>" class="dynamics">
						<img src="<s:url value='/includes/images/dinamicas_min.jpg'/>" alt="" />
					</a>
	
					<div class="inner">
						<header>
							<h2>Dinâmicas</h2>
							<p class="subtitleBox">Din&acirc;micas de grupo com bate-papo</p>
						</header>
	
						<ul class="sessoes">
							<li>Entrevistas</li>
							<p>Entrevista é uma conversação[...]</p>
							<br />
							<li>Debate</li>
							<p>Debate é uma discuss&atilde;o[...]</p>
						</ul>
						<br />
						<br />
						<a href="<s:url value='/dinamica.do'/>" class="botaoGrande botaoGrande-icon botaoGrandeIconPaper botaoGrandeDinamicas">
							Lista Completa
						</a>
					</div>
				</section>
			</div>
			<!-- dinamica -->
			
			<div id="tagaBoxAgenda">
				<!-- Box -->
				<section> 
					<a href="<s:url value='/listaAgenda.do'/>" class="agenda">
						<img src="<s:url value='/includes/images/agenda_min.jpg'/>" alt="" />
					</a>
	
					<div class="inner">
						<header>
							<h2>Agenda</h2>
							<p class="subtitleBox">Agende suas próximas sessões</p>
						</header>
	
						<ul class="sessoes">
							<s:iterator value="listaAgendasDisponiveis" var="agenda">
								<li><s:property value="assunto"/></li>
								<p>
									<s:date name="data" format="dd/MM/yyyy"/> - <s:date name="hora" format="HH:mm:ss"/>
								</p>
							</s:iterator>
						</ul>
						<br />
						<br />
						<a href="<s:url value='/listaAgenda.do'/>" class="botaoGrande botaoGrande-icon botaoGrandeIconPaper botaoGrandeAgenda">
							Agendadas
						</a>
					</div>
				</section>
			</div>
			<!-- agenda -->
			
			<div id="tagaBoxSistemas">
				<!-- Box -->
				<section>
					<a href="<s:url value='/sistemas.do'/>">
						<img src="<s:url value='/includes/images/sistemas_min.jpg'/>" alt="" />
					</a>
	
					<div class="inner">
						<header>
							<h2>Sistemas</h2>
							<p class="subtitleBox">Sistemas de bate-papo</p>
						</header>
	
						<ul class="sessoes">
							<li>Típico</li>
							<p>Bate-papo típico</p>
							<br />
							<li>DebatePapo</li>
							<p>Debate de conteúdo</p>
							<br />
	
						</ul>
	
						<br />
						<br />
						<a href="<s:url value='/sistemas.do'/>" class="botaoGrande botaoGrande-icon botaoGrandeIconPaper botaoGrandeSistemas">
							Lista Completa
						</a>
					</div>
				</section>

			</div>
			<!-- sistemas -->
			<br clear="all" />
		</div>
		
		<!-- fim das box da primeira linha -->
		<div id="boxesConteudo2">
			<div id="tagaBoxArquivo">
				<section> 
					<a href="#">
						<img src="<s:url value='/includes/images/arquivo_min.jpg'/>" alt="" />
					</a>
	
					<div class="inner">
						<header>
							<h2>Arquivos</h2>
							<p class="subtitleBox">Arquivos de sessões passadas</p>
						</header>
	
						<ul class="sessoes">
							<s:iterator value="listaAgendasArquivadasUsuario" var="agenda">
								<li><s:property value="assunto"/></li>
								<p>
									<s:date name="data" format="dd/MM/yyyy"/> - <s:date name="hora" format="HH:mm:ss"/>
								</p>
							</s:iterator>
						</ul>
						<br />
						<br />
	
						<a href="" class="botaoGrande botaoGrande-icon botaoGrandeIconPaper botaoGrandeAgenda">
							Lista Completa
						</a>
					</div>
				</section>
			</div>
			<!-- fim box arquivos -->
			
			<div id="tagaBoxAnalise">

				<!-- Box -->
				<section> 
					<a href="#">
						<img src="<s:url value='/includes/images/analises_min.jpg'/>" alt="" />
					</a>
	
					<div class="inner">
						<header>
							<h2>Análise</h2>
							<p class="subtitleBox">Registros de sessão de bate-papo</p>
						</header>
	
						<ul class="sessoes">
							<li>RelatePapo</li>
							<p>Relatório da sessão</p>
							<br />
							<li>Analítico</li>
							<p>Análise do Discurso</p>
							<br />
							<li>Visu</li>
							<p>Visualizações Gráficas</p>
						</ul>
	
						<br />
						<br />
	
					</div>
				</section>
			</div>
			<!-- fim analise -->
			
			<div id="tagaBoxPublicacoes">

				<!-- Box -->
				<section>
					<a href="<s:url value='/publicacao.do'/>">
						<img src="<s:url value='/includes/images/publicacoes_min.jpg'/>" alt="" />
					</a>

					<div class="inner">
	
						<header>
						<h2>Publicações</h2>
						<p class="subtitleBox">Publicações sobre bate-papo na
							educação</p>
						</header>
	
						<ul class="sessoes">
							<li>Portal Tagarelas: Portal de Bate-papo para Educação</li>
							<p>2012, SBIE</p>
							<br />
							<li>TABSCHAT: Organizando os assuntos para um debate
								educacional</li>
							<p>2010, Dissertação</p>
							<br />
							<li>Sistemas para promover a cultura de bate-papo na
								Educação</li>
							<p>2012, ABCIBER</p>
						</ul>
	
						<br />
						<br />
						<a href="<s:url value='/publicacao.do'/>" class="botaoGrande botaoGrande-icon botaoGrandeIconPaper botaoGrandeSistemas">
							Lista Completa
						</a>
					</div>
				</section>

			</div>
			<!-- fim pubicacao -->
			
			<br clear="all" />
		</div>
	</div>
</body>
</html>