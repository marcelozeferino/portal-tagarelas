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
	<div id="conteudo">
		<article>
			<div id="boxes">
				<div id="bannerConteudo">
					<div id="tagarelandoBoxEsq" style="height: 260px;">
						<!-- Box -->
						<section>
							<div class="inner">
								<header>
									<h2>Minhas Sessões Agendadas</h2>
									<!--	<p class="subtitleBox">Sessões agendadas que participarei</p>-->
								</header>
										
								<div id="tagarelandoLista1" style="height: 142px; overflow: hidden; display: block;">
									<ul class="sessoes">
										<s:iterator value="listaAgendasUsuario">
											<li>
												<s:property value="assunto"/>
                                        	</li>
                                        	<p>
                                        		<s:property value="descricao"/>,
                                        		<s:date name="data" format="dd/MM/yyyy"/>-
                                        		<s:date name="hora" format="HH:mm:ss"/>
											</p>
											
											<s:if test="idSistema == 0">
												<s:set var='url' value="%{'/chatTipico.do'}"/>
											</s:if>
											<s:if test="idSistema == 5">
												<s:set var='url' value="%{'/chatTipicoFullFuncionando.do'}"/>
											</s:if>
											
											<s:form name="formMinhasSessoes_%{idAgenda}" method="POST" action="%{url}" theme="simple">
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
											
												<a href="#" onclick="document.formMinhasSessoes_<s:property value='idAgenda'/>.submit();">
													Acessar
												</a>
											</s:form>
                                            <br/>
										</s:iterator>
									</ul>
								</div>
								<br/><br/>
								<a href="<s:url value='/listaMinhaSessaoGeral.do'/>" class="botaoGrande botaoGrande-icon botaoGrandeIconPaper botaoGrandeAgenda">
									Ver Mais
								</a>
								<br clear="all"/>
							</div>
						</section>
						<br clear="all"/>
					</div>
					
					<div id="tagarelandoBoxDir" style="height: 260px;">
						<!-- Box -->
						<section>
							<div class="inner">
								<header>
									<h2>Minhas Sessões Arquivadas</h2>
									<!--<p class="subtitleBox">Sessões agendadas que participarei</p>-->
								</header>
								
								<div id="tagarelandoLista2" style="height: 142px; overflow: hidden; display: block;">
									<ul class="sessoes">
										<s:iterator value="listaAgendasArquivadasUsuario">
											<li>
												<s:property value="assunto"/>
                                        	</li>
                                        	<p>
                                        		<s:property value="descricao"/>,
                                        		<s:date name="data" format="dd/MM/yyyy"/>-
                                        		<s:date name="hora" format="HH:mm:ss"/>
											</p>
                                            <br/>
										</s:iterator>
									</ul>
								</div>
								<br/><br/>
								<a href="<s:url value='/listaArquivo.do'/>" class="botaoGrande botaoGrande-icon botaoGrandeIconPaper botaoGrandeAgenda">
									Ver Mais
								</a>
								<br clear="all"/>
							</div>
						</section>
					</div>
					
					<div id="tagarelandoBoxEsq2" style="height: 260px;">
						<!-- Box -->
						<section>
							<div class="inner">
								<header>
									<h2>Sessões Que Vou Participar</h2>
									<!--<p class="subtitleBox">Sessões agendadas que participarei</p>-->
								</header>
								<div id="tagarelandoLista3" style="height: 142px; overflow: hidden; display: block;">
									<ul class="sessoes">
										<s:iterator value="listaAgendasUsuarioParticipa">
											<li>
												<s:property value="assunto"/>
                                        	</li>
                                        	<p>
                                        		<s:property value="descricao"/>,
                                        		<s:date name="data" format="dd/MM/yyyy"/>-
                                        		<s:date name="hora" format="HH:mm:ss"/>
											</p>
											
											<s:if test="idSistema == 0">
												<s:set var='url' value="%{'/chatTipico.do'}"/>
											</s:if>
											<s:if test="idSistema == 5">
												<s:set var='url' value="%{'/chatTipicoFullFuncionando.do'}"/>
											</s:if>
											
											<s:form name="formSessoesVouParticipar_%{idAgenda}" method="POST" action="%{url}" theme="simple">
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
											
												<a href="#" onclick="document.formSessoesVouParticipar_<s:property value='idAgenda'/>.submit();">
													Acessar
												</a>
											</s:form>
                                            <br/>
										</s:iterator>
									</ul>
								</div>
								<br/><br/>
								<a href="<s:url value='/listaSessaoVouParticipar.do'/>" class="botaoGrande botaoGrande-icon botaoGrandeIconPaper botaoGrandeAgenda">
									Ver Mais
								</a>
							</div>
						</section>
					</div>
					
					<div id="tagarelandoBoxDir2" style="height: 260px;">
						<!-- Box -->
						<section>
							<div class="inner">
								<header>
									<h2>Sessões Que Já Participei</h2>
									<!--<p class="subtitleBox">Sessões agendadas que participarei</p>-->
								</header>
								<div id="tagarelandoLista4" style="height: 142px; overflow: hidden; display: block;">
									<ul class="sessoes">
										<s:iterator value="listaAgendasUsuarioParticipou">
											<li>
												<s:property value="assunto"/>
                                        	</li>
                                        	<p>
                                        		<s:property value="descricao"/>,
                                        		<s:date name="data" format="dd/MM/yyyy"/>-
                                        		<s:date name="hora" format="HH:mm:ss"/>
											</p>
                                            <br/>
										</s:iterator>
									</ul>
								</div>
								<br/><br/>
								<a href="<s:url value='/listaArquivoParticipei.do'/>" class="botaoGrande botaoGrande-icon botaoGrandeIconPaper botaoGrandeAgenda">
									Ver Mais
								</a>
								<br clear="all"/>
							</div>
						</section>
					</div>
				</div>
			</div>
		</article>
		<br clear="all"/>
	</div>
</body>
</html>