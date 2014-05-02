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

	<script type="text/javascript">	
		$(document).ready(function()
		{
			//slides the element with class "menu_body" when paragraph with class "menu_head" is clicked 
			$("#firstpane p.menu_head").click(function()
			{
				$(this).css({backgroundImage:"url(<s:url value='/includes/images/down.png'/>)"}).next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
				$(this).siblings().css({backgroundImage:"url(images/left.png)"});
			});
			//slides the element with class "menu_body" when mouse is over the paragraph
			$("#secondpane p.menu_head").mouseover(function()
			{
				 $(this).css({backgroundImage:"url(<s:url value='/includes/images/down.png'/>)"}).next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp("slow");
				 $(this).siblings().css({backgroundImage:"url(<s:url value='/includes/images/left.png'/>)"});
			});
		});
	</script>
	
	<style type="text/css">
		.menu_list {	
			width: 550px;
		}
		.menu_head {
			padding: 5px 10px;
			cursor: pointer;
			position: relative;
			margin:1px;
			font-weight:bold;
			/*
			background: #eef4d3 url(images/left.png) center right no-repeat;*/
			background: url(<s:url value='/includes/images/left.png'/>) center right no-repeat;
		}
		.menu_body {
			display:none;
		}
		.menu_body a{
		 display:block;
		 color:#696969;
		 background-color:#fff;
		 padding-left:10px;
		 /*font-weight:bold;*/
		 text-decoration:none;
		 line-height: 1.5em;
		 font-size: 13px;
		}
		.menu_body a:hover{
		 color: #000000;
		 text-decoration:underline;
		 }
		.menu_body p{
		 display:block;
		 color:#696969;
		 background-color:#fff;
		 padding-left:10px;
		 line-height: 1.5em;
		 font-size: 13px;
		}		
		.textGrande{
			font-size: 12px;
			line-height: 2em;
			font-weight: bold;
		}
		.textGrande li{
			/*margin-bottom: 25px;*/
			
		}
		a .listaSistemas{
			color: #000;
		}
	</style>
</head>
<body>
	<div id="bannerConteudo">
		<!--<div id="faceCadatro">
				<br clear="all">
				<div id="botaoBannerFace">
				<img src="images/sistemas_min_interna.jpg"/>
			</div>
		</div>-->
		<div id="tituloPrincipal">
			<h2>Entrevista online</h2>
			<br/>
			<img src="<s:url value='/includes/images/dinamicasInterna.jpg'/>"/>
			<p>
				Entrevista &eacute; uma conversa&ccedil;&atilde;o em que o entrevistador faz perguntas para obter informa&ccedil;&atilde;o do entrevistado.
				A entrevista online &eacute; a realiza&ccedil;&atilde;o de entrevistas geralmente por meio de bate-papo.
			</p>
			<br clear="all"/><br clear="all"/>
		</div>	
		<div id="conteudoPagfull">
			<div id="listaSistemas">
				<!-- Box -->
				<section>
					<ul>
						<!--<li><a href="#" class="botaoGrande botaoGrande-icon botaoGrandecadastrar">Concluir</a></li>-->
						<li><p>&#187; <a href="#social">Entrevista online enquanto pr&aacute;tica social</a></p></li>
						<li><p>&#187; <a href="#educacao">Entrevista na educa&ccedil;&atilde;o</a></p></li>
			            <li><p>&#187; <a href="#papo">Entrevista por bate-papo</a></p></li>
			            <li><p>&#187; <a href="#tipo">Tipos de Entrevista</a></p></li>
			            <li><p>&#187; <a href="#processo">Processo</a></p></li>
			            <li><p>&#187; <a href="#preparacao">Prepara&ccedil;&atilde;o</a></p></li>
			            <li><p>&#187; <a href="#realizacao">Realiza&ccedil;&atilde;o</a></p></li>
			            <li><p>&#187; <a href="#analise">An&aacute;lise</a></p></li>
			            <li><p>&#187; <a href="#referencias">Refer&ecirc;ncias</a></p></li>
					</ul>
					<br/><br/><br/>
					<a name='social'></a>
					<p class="textGrande">Entrevista online enquanto pr&aacute;tica social</p>
					<ul>
						<li>
							<p align="justify">
								<strong>Entrevista</strong>  &eacute; uma pr&aacute;tica realizada para diferentes finalidades: na tv, entrevistadores convidam 
								celebridades para conversar sobre fofocas, cotidiano e projetos art&iacute;sticos, ou convidam especialistas para 
								conversar sobre algum assunto de interesse; rep&oacute;rteres entrevistam pessoas para obter declara&ccedil;ões sobre um 
								evento ocorrido que ser&aacute; transformado em not&iacute;cia; pesquisadores entrevistam para conseguir investigar a 
								subjetividade dos sujeitos com rela&ccedil;&atilde;o a um objeto de pesquisa.	 
								<br />
								<strong>Entrevista online </strong> j&aacute; se tornou uma pr&aacute;tica principalmente associada a programas de TV ou a 
								portais da Internet para o p&uacute;blico poder entrevistar celebridades e especialistas. Por exemplo, no portal Globo, 
								entrevistas online s&atilde;o realizadas diariamente, geralmente vinculadas a algum programa da TV Globo, como as 
								realizadas ao final de cada Fant&aacute;stico, ou ap&oacute;s a elimina&ccedil;&atilde;o de cada participante no BBB, ou em algum site ou 
								revista que fa&ccedil;a parte do conte&uacute;do disponibilizado naquele portal. Na se&ccedil;&atilde;o Bate-papo com convidados do 
								portal UOL s&atilde;o agendadas, realizadas e arquivadas entrevistas com personalidades, sendo algumas entrevistas 
								transmitidas ao vivo pela TV UOL- esse portal &eacute; um dos pioneiros na realiza&ccedil;&atilde;o de entrevistas online por meio de 
								bate-papo no Brasil, com registros de entrevistas a partir de 1996.
								<br />
								A entrevista online em compara&ccedil;&atilde;o com a entrevista face-a-face, identifica-se que ambam possibilitam o mesmo 
								entendimento da circunst&acirc;ncia social estudada, sendo que a entrevista on-line via bate-papo &eacute; t&atilde;o eficaz 
								quanto as entrevistas tradicionais
							</p>
						</li>
					</ul>
					<br/><br/>
					<a name='educacao'></a>
					<p class="textGrande">Entrevista na educa&ccedil;&atilde;o</p>
					<ul>
						<li>
							<p align="justify">
								A entrevista, &eacute; uma das t&eacute;cnicas mais conhecidas de trabalho em grupo. Nesse contexto, o grupo pode ser a turma 
								e o entrevistado, algum convidado especial ou o pr&oacute;prio professor. Essa din&acirc;mica educacional, auxilia o professor 
								a criar um ambiente de constru&ccedil;&atilde;o de conhecimento vivo, n&atilde;o estruturado e o aluno poder fazer perguntas sem ser 
								censurado. Al&eacute;m de estimular o racioc&iacute;nio, ela promove a colabora&ccedil;&atilde;o; desenvolve o senso questionador; a arte de 
								perguntar; contribui para o desenvolvimento da informalidade, espontaneidade, racioc&iacute;nio, aten&ccedil;&atilde;o, sendo considerado 
								um recurso did&aacute;tico de exposi&ccedil;&atilde;o de conte&uacute;do. Entretanto, &eacute; necess&aacute;rio que o professor prepare a turma antes da 
								sess&atilde;o para que ela esteja familiarizada com o tema que ser&aacute; abordado na entrevista
							</p>
						</li>
					</ul>
					<br/><br/>
					<a name='papo'></a>
					<p class="textGrande">Entrevista por bate-papo</p>
					<ul>
						<li>
							<p align="justify">
								As entrevistas <em>on-line</em> por meio de bate-papo, permitem  adotar um estilo cotidiano, informal, em que 
								os erros s&atilde;o mais facilmente aceitos. A Internet &eacute; vista como um ambiente seguro, evitando o embara&ccedil;o t&iacute;pico 
								das intera&ccedil;ões face &agrave; face. Essas entrevistas, encontram-se limitadas &agrave; habilidade e &agrave; velocidade de digita&ccedil;&atilde;o 
								dos participantes, a um tempo m&iacute;nimo de resposta e a uma competi&ccedil;&atilde;o pela aten&ccedil;&atilde;o do respondente, fazendo com 
								que seus esfor&ccedil;os sejam m&iacute;nimos resultando em postagens breves e com poucas palavras
							</p>
						</li>
					</ul>
					<br/><br/>
					<a name='tipo'></a>
					<p class="textGrande">Tipos de Entrevista</p>
					<ul>
						<li>
							<p align="justify">
								<strong>Entrevista para a produ&ccedil;&atilde;o de conte&uacute;do:</strong> A entrevista para a produ&ccedil;&atilde;o de conte&uacute;do pode trazer 
								in&uacute;meras contribui&ccedil;ões para a comunidade profissional. Em um mundo baseado em conhecimento como diferencial 
								competitivo, ter suas impressões e experi&ecirc;ncias dispon&iacute;veis pode oferecer aos clientes e parceiros uma porta 
								diferenciada para os neg&oacute;cios.
								<br /><br />
								<strong>Entrevista motivacional:</strong> &eacute; uma abordagem terap&ecirc;utica que auxilia na mudan&ccedil;a de comportamento. 
								Dentre algumas dessas mudan&ccedil;as podemos citar tentativas para abandonar algum tipo de v&iacute;cio como drogas, &aacute;lcool, 
								tabaco, bem como reduca&ccedil;&atilde;o alimentar, aux&iacute;lio &agrave; menores infratores, adolescentes e etc.
								<br /><br />
 									<strong>Entrevista para sele&ccedil;&atilde;o de pessoal:</strong> A entrevista para a sele&ccedil;&atilde;o de pessoal tem como objetivo 
 									fazer com que tanto o candidato como a empresa se conhe&ccedil;am melhor. Dessa forma, o entrevistado tem a chance de 
 									se apresentar oficialmente &agrave; empresa e a empresa tamb&eacute;m adquire um conhecimento mais aprofundado sobre o 
 									candidato ao cargo
 									<br /><br />
 									<strong>Entrevista para trabalhos cient&iacute;ficos:</strong> A entrevista, tem sido cada vez mais utilizada em 
 									trabalhos cient&iacute;ficos. Ela permite que pesquisadores  possam extrair  grande quantidades de dados e informa&ccedil;ões 
 									aumentando a riqueza de seu trabalho.Essa t&eacute;cnica, desempenha um papel vital para um trabalho cient&iacute;fico se 
 									combinada a outros m&eacute;todos de coleta de dados, intui&ccedil;ões e percep&ccedil;ões, melhorando a qualidade de um 
 									levantamento e de sua interpreta&ccedil;&atilde;o
 									<br /><br />
 									<strong>Entrevista de Oposi&ccedil;&atilde;o: </strong>Esse tipo de entrevista, muitas vezes conhecida como “advogado do 
 									diabo”, geralmente &eacute; uma  maneira r&aacute;pida de fazer com que o entrevistado aponte claramente a sua opini&atilde;o dando 
 									uma resposta bastante persuasiva.
 									<br /><br /><br/>
								<a name='processo'></a>
							</p>
						</li>
					</ul>
					<p class="textGrande">Processo</p>
					<ul>
						<li>
							<p align="justify">
								O entrevista online &eacute; composto por:
								<br /><br />
								<strong>Entrevistador (entrevistadores)</strong> – &Eacute; a pessoa que se encarrega de fazer as perguntas aos 
								especialistas<strong>. </strong>Tamb&eacute;m &eacute; respons&aacute;vel por dirigir o conte&uacute;do da entrevista, tomar a iniciativa 
								e reformular os question&aacute;rios de acordo com as respostas.
								<br /><br />
								<strong>Especialista ou entrevistado </strong>– &Eacute; a pessoa que deve responder &agrave;s questões propostas. Em geral, 
								faz-se a mesma pergunta a cada entrevistado. Ao final, se o tempo ou a estrutura da entrevista permitir, 
								perguntas podem ser feitas por interm&eacute;dio do coordenador.
								<br /><br />
								<strong>Coordenador – </strong>&Eacute; a pessoa que atua como moderador, que d&aacute; a palavra, decide procedimentos, 
								programa a hora e o momento de falar, o in&iacute;cio e t&eacute;rmino da sess&atilde;o.
							</p>
						</li>
					</ul>
					<br/><br/>
					<a name='preparacao'></a>
					<p class="textGrande">Prepara&ccedil;&atilde;o</p>
					<ul>
						<li>
							<p align="justify">
								O moderador do processo, tem como tarefas principais, convidar entrevistado e entrevistadores e preparar o 
								roteiro para o entrevistado (poss&iacute;veis perguntas e perfil dos entrevistadores) e para os entrevistadores, 
								fornecendo-lhes o conte&uacute;do b&aacute;sico para prepara-los sobre um determinado tema. Dessa forma, entrevistado e 
								entrevistadores podem se preparar adequadamente para a entrevista.
							</p>
						</li>
					</ul>
					<br/><br/>
					<a name='realizacao'></a>
					<p class="textGrande">Realiza&ccedil;&atilde;o</p>
					<ul>
						<li>
							<p align="justify">
								Para a realiza&ccedil;&atilde;o da sess&atilde;o entrevista, &eacute; utilizada uma ferramenta computacional. O mediador apresenta o 
								entrevistado, inicia e finaliza a sess&atilde;o. Os entrevistadores podem conversar com o entrevistado e entre si 
								durante a sess&atilde;o, como forma de socializa&ccedil;&atilde;o. Entrevistadores enviam perguntas diretamente para o entrevistado.
							</p>
						</li>
					</ul>
					<br/><br/>
					<a name='analise'></a>
					<p class="textGrande">An&aacute;lise</p>
					<ul>
						<li>
							<p align="justify">
								Ap&oacute;s o t&eacute;rmino da entrevista, &eacute; necess&aacute;rio realizar a sua transcri&ccedil;&atilde;o e an&aacute;lise. Este processo geralmente 
								demanda um tempo muito grande mas se faz necess&aacute;rio pois algumas informa&ccedil;ões podem passar desapercebidas ou 
								mesmo serem incompreendidas, o que poderia exigir uma nova entrevista com o entrevistado.
							</p>
						</li>
					</ul>
					<br/><br/>
					<a name='referencias'></a>
					<p class="textGrande">Refer&ecirc;ncias</p>
					<ul>
						<li>
							<p align="justify">
								Minicucci, A.,(2001) – T&eacute;cnicas de  Trabalho em grupo, Editora Atlas, 3. Edi&ccedil;&atilde;o.<br />
								OLIVEIRA, M.O.R., REGO, B.B., ALVES, D.A. Uma Compara&ccedil;&atilde;o entre Entrevistas Face-To-Face e Entrevistas On-Line 
								via Chat aplicando a t&eacute;cnica de laddering. 
								<a href="http://seer.uscs.edu.br/index.php/revista_gestao/article/view/195">
									http://seer.uscs.edu.br/index.php/revista_gestao/article/view/195
								</a>
								<img border="0" width="15" height="12" src="<s:url value='/includes/images/dinamicasEntrevista_clip_image001.png'/>" />
								Acessado em 21 outubro de 2012.<br/>
								J&uacute;nior, A., J&uacute;nior,  Nazer., (2011) –A utiliza&ccedil;&atilde;o da T&eacute;cnica da Entrevista em trabalhos Cient&iacute;ficos<br />
								Nunes,R., Barbosa, C.V., Pimentel, M. –T&eacute;cnica Entrevista como Din&acirc;mica Educacional – SBSI 2008 –XIX Simp&oacute;sio 
								Brasileiro de Inform&aacute;tica na Educa&ccedil;&atilde;o.<br />
								NICOLACI-DA-COSTA, A.M., ROMAO-DIAS, D., DI LUCCIO, F. Uso de entrevistas on-line no m&eacute;todo de explicita&ccedil;&atilde;o do 
								discurso subjacente (MEDS). Psicologia: Reflex&atilde;o e Cr&iacute;tica, v.22, n.1, 2009, p.36-43. 
								http://dx.doi.org/10.1590/S0102-79722009000100006 Acessado em 21 outubro de 2012.<br />
								<br/>             
 								</p>
						</li>
					</ul>
					<br/><br/>
				</section>
			</div>	
		</div>
	<br clear="all"/>
	</div>
</body>
</html>