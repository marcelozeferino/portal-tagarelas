package com.br.portaltagarelas.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.br.portaltagarelas.model.Usuario;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;

@Controller
@Scope("request")
public class BaseAction extends ActionSupport implements SessionAware, Preparable {

	
	private static final long serialVersionUID = -1821018637463765102L;
	protected static final String ERRO_INESPERADO = "Ocorreu um erro inesperado!";
	protected static final String CAMPOS_OBRIGATORIOS = "Preencha todos os campos obrigatÃ³rios!";
	protected static final String ALTERACAO_DADOS_SUCESSO = "Dados alterados com sucesso!";
	protected static final String ALTERACAO_DADOS_SEM_SUCESSO = "Ocorreu um erro na alteração de seus dados!";
	protected static final String LIMITE_TAMANHO_IMAGEM = "A imagem não pode ter mais que 4 MB (4096 KB)!";
	protected static final String FORMATO_IMAGEM_INVALIDO = "Formato de arquivo inválido. Por favor, utilize somente imagens nos formatos JPG, JPEG, GIF, BMP ou PNG!";
	protected static final String SENHAS_NAO_CONFEREM = "Os campos SENHA e CONFIRMAR SENHA não conferem!";
	
	protected static final String CONTENT_JPG = "image/jpeg";
	protected static final String CONTENT_PJPG = "image/pjpeg"; //content type do JPEG para o IE.
	protected static final String CONTENT_BMP = "image/bmp";
	protected static final String CONTENT_XPNG = "image/x-png";
	protected static final String CONTENT_PNG = "image/png";
	protected static final String CONTENT_GIF = "image/gif";
	
	private Usuario usuarioLogado;
	
	@SuppressWarnings("unused")
	private HttpServletRequest request;
	@SuppressWarnings("unused")
	private HttpServletResponse response;

	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}

	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}

	public String retornaJSON(Object obj) {
		String respostaJSON = getJson(obj);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/x-json");
			PrintWriter out = response.getWriter();
			out.write(respostaJSON);
			out.close();

		} catch (IOException e) {

			//throw new IntegrationException(e);
		}

		return null;
	}
	
	public boolean validarImagem(File imagem, String contentType) {
		ArrayList<String> listContentTypes = new ArrayList<String>();
		listContentTypes.add(CONTENT_JPG);
		listContentTypes.add(CONTENT_PJPG);
		listContentTypes.add(CONTENT_BMP);
		listContentTypes.add(CONTENT_PNG);
		listContentTypes.add(CONTENT_GIF);
		listContentTypes.add(CONTENT_XPNG);

		if (null == imagem)
			return true;
		else if (imagem.length() > 4194304) {
			addActionError(LIMITE_TAMANHO_IMAGEM);
			return false;
		} else if (!listContentTypes.contains(contentType)) {
			addActionError(FORMATO_IMAGEM_INVALIDO);
			return false;
		} else
			return true;
	}
	
	protected boolean validarFormatoEmail(String email, String permissao) {
		Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
		Matcher m = p.matcher(email);
		if (m.find()) {
			String[] arrEmail = email.split("@");
			
			if(!"sebrae.com.br".equals(arrEmail[1]) && !"administrador".equals(permissao)){
				addActionError("O email cadastrado precisa estar no padrão xxx@sebrae.com.br");
				return false;
			} else {
				return true;
			}
		} else {
			addActionError("Email " + email + " inválido!");
			return false;
		}
	}

	protected boolean validarCpf(String strCpf) {
		strCpf = strCpf.replaceAll("\\.", "").replace("-", "");

		//CPFs considerados inválidos pela Receita Federal
		ArrayList<String> listaCPFsInvalidos = new ArrayList<String>();
		listaCPFsInvalidos.add("00000000000");
		listaCPFsInvalidos.add("11111111111");
		listaCPFsInvalidos.add("22222222222");
		listaCPFsInvalidos.add("33333333333");
		listaCPFsInvalidos.add("44444444444");
		listaCPFsInvalidos.add("55555555555");
		listaCPFsInvalidos.add("66666666666");
		listaCPFsInvalidos.add("77777777777");
		listaCPFsInvalidos.add("88888888888");
		listaCPFsInvalidos.add("99999999999");

		if (listaCPFsInvalidos.contains(strCpf)) {
			addActionError("CPF " + strCpf + " inválido!");
			return false;
		} else {
			int d1, d2;
			int digito1, digito2, resto;
			int digitoCPF;
			String nDigResult;
			boolean retorno = false;

			d1 = d2 = 0;
			digito1 = digito2 = resto = 0;

			for (int nCount = 1; nCount < strCpf.length() - 1; nCount++) {
				digitoCPF = Integer.valueOf(strCpf.substring(nCount - 1, nCount)).intValue();

				//multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
				d1 = d1 + (11 - nCount) * digitoCPF;

				//para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
				d2 = d2 + (12 - nCount) * digitoCPF;
			}
			;

			//Primeiro resto da divisão por 11.
			resto = (d1 % 11);

			//Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
			if (resto < 2)
				digito1 = 0;
			else
				digito1 = 11 - resto;

			d2 += 2 * digito1;

			//Segundo resto da divisão por 11.
			resto = (d2 % 11);

			//Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
			if (resto < 2)
				digito2 = 0;
			else
				digito2 = 11 - resto;

			//Digito verificador do CPF que está sendo validado.
			String nDigVerific = strCpf.substring(strCpf.length() - 2, strCpf.length());

			//Concatenando o primeiro resto com o segundo.
			nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

			//comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
			retorno = nDigVerific.equals(nDigResult);

			if (!retorno) {
				addActionError("CPF " + strCpf + " inválido!");
			}

			return retorno;
		}
	}
	
	public void copiarImagem(File arquivo, String nomeArquivo, String caminhoUpload) throws Exception {
		File dirOutput = null;
		File arqOutput = null;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		FileChannel origem = null;
		FileChannel destino = null;

		try {
			dirOutput = new File(caminhoUpload);
			arqOutput = new File(caminhoUpload + nomeArquivo);

			if (!dirOutput.exists()) {
				dirOutput.mkdirs();
			} else {
				File[] arquivosExistentes = dirOutput.listFiles();

				if (arquivosExistentes.length > 0) {
					for (int i = 0; i < arquivosExistentes.length; i++) {
						arquivosExistentes[i].delete();
					}
				}
			}

			fis = new FileInputStream(arquivo);
			fos = new FileOutputStream(arqOutput);

			origem = fis.getChannel();
			destino = fos.getChannel();

			origem.transferTo(0, origem.size(), destino);
		} catch (Exception e) {
			throw e;
		}

		finally {
			try {
				if (origem != null)
					origem.close();
				if (destino != null)
					destino.close();

				if (fis != null)
					fis.close();
				if (fos != null)
					fos.close();
			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	public void deletarDiretorio(String caminhoUpload) throws Exception {
		File dirOutput = null;

		try {
			dirOutput = new File(caminhoUpload);

			if (dirOutput.exists()) {
				File[] arquivosExistentes = dirOutput.listFiles();

				if (arquivosExistentes.length > 0) {
					for (int i = 0; i < arquivosExistentes.length; i++) {
						Files.delete(arquivosExistentes[i].toPath());
					}
				}

				Files.delete(dirOutput.toPath());
			}
		} catch (Exception e) {
			throw e;
		}
	}

//	public String retornaArquivoParaDownload(Arquivo arquivo) {
//		try {
//			HttpServletResponse response = ServletActionContext.getResponse();
//			byte[] dados = IOUtils.toByteArray(arquivo.getArquivo());
//			BufferedOutputStream bos = null;
//
//			response.setContentType(arquivo.getContentType());
//			response.setHeader("Content-Disposition", "attachment; filename=\"" + arquivo.getNome() + "\"");
//			bos = new BufferedOutputStream(response.getOutputStream());
//			bos.write(dados, 0, dados.length);
//			bos.flush();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return null;
//	}

	public String getJson(Object p) {
		final XStream xstream = new XStream(new JsonHierarchicalStreamDriver() {
			public HierarchicalStreamWriter createWriter(final Writer writer) {
				return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
			}
		});
		return xstream.toXML(p);
	}

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	protected void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	protected void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	protected Usuario getUsuarioLogado() {
		usuarioLogado = (Usuario)getRequest().getSession().getAttribute("usuarioLogado");
		return usuarioLogado;
	}

	protected void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
		getRequest().getSession().setAttribute("usuarioLogado", usuarioLogado);
	}
}