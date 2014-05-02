package com.br.portaltagarelas.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.br.portaltagarelas.facade.UsuarioFacadeIntf;
import com.br.portaltagarelas.model.Usuario;
import com.br.portaltagarelas.utils.EmailUtils;

@Controller
@Scope("request")
public class UsuarioAction extends BaseAction{

	private static final long serialVersionUID = 4215401297034317557L;
	private File fotoUsuario;
	private String fotoUsuarioContentType;
	private String fotoUsuarioFileName;
	private Usuario usuario;
	private String confirmarSenha;
	
	@Autowired private UsuarioFacadeIntf usuarioFacade;
	@Autowired private ServletContext servletContext;
	
	public String cadastrarUsuario(){
		try {
			boolean temFoto = false;
			
			if(validarImagem(fotoUsuario, fotoUsuarioContentType)){
				if(!verificaPreenchimentoFormUsuario(usuario, "cadastro")){
					addActionError(CAMPOS_OBRIGATORIOS);
					return ERROR;
				} else if(!validarFormatoEmail(usuario.getEmail())){
					addActionError("Email "+usuario.getEmail()+" inválido!");
					return ERROR;
				} else if(usuarioFacade.retornaUsuarioPorEmailFacade(usuario.getEmail()) != null){
					addActionError("Email já cadastrado na base de dados!");
					return ERROR;
				} else if(!confirmarSenha.equals(usuario.getSenha())){
					addActionError(SENHAS_NAO_CONFEREM);
					return ERROR;
				} else{
					if(fotoUsuario != null){
						usuario.setFoto("includes/images/usuarios/" + usuario.getEmail() + "/" + fotoUsuarioFileName);
						temFoto = true;
					}else{
						usuario.setFoto("includes/images/sem-imagem.jpg");
						temFoto = false;
					}
					
					if(usuarioFacade.cadastrarUsuarioFacade(usuario)){
						if(temFoto){
							String caminhoUploadCurso = servletContext.getRealPath("/includes") + "/images/usuarios/" + usuario.getEmail() + "/";
							copiarImagem(fotoUsuario, fotoUsuarioFileName, caminhoUploadCurso);
						}
						addActionMessage("Usuario cadastrado com sucesso!");
						return SUCCESS;
					}else{
						addActionError("Erro ao cadastrar usuário. Tente novamente mais tarde!");
						return ERROR;
					}
				}
			}else{
				return ERROR;
			}
		} catch (Exception e) {
			addActionError(ERRO_INESPERADO);
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String alterarUsuario(){
		try {
			Usuario usuarioLogado = getUsuarioLogado();
			boolean temFoto = false;
			
			if(validarImagem(fotoUsuario, fotoUsuarioContentType)){
				if(!verificaPreenchimentoFormUsuario(usuario, "alteracao")){
					addActionError(CAMPOS_OBRIGATORIOS);
					return ERROR;
				} else if(!validarFormatoEmail(usuario.getEmail())){
					addActionError("Email "+usuario.getEmail()+" inválido!");
					return ERROR;
				} else if(((!"".equals(usuario.getSenha()) && usuario.getSenha() != null) || (!"".equals(confirmarSenha) && confirmarSenha != null)) && (!confirmarSenha.equals(usuario.getSenha()))){
					addActionError(SENHAS_NAO_CONFEREM);
					return ERROR;
				} else{
					if(!usuarioLogado.getEmail().equals(usuario.getEmail())){
						if(usuarioFacade.retornaUsuarioPorEmailFacade(usuario.getEmail()) != null){
							addActionError("Email já cadastrado na base de dados!");
							return ERROR;
						}
					}
					
					if("".equals(usuario.getSenha()) || usuario.getSenha() == null){
						usuario.setSenha(usuarioLogado.getSenha());
					}
					
					if(fotoUsuario != null){
						usuario.setFoto("includes/images/usuarios/" + usuario.getEmail() + "/" + fotoUsuarioFileName);
						temFoto = true;
					} else{
						usuario.setFoto(usuarioLogado.getFoto());
						temFoto = false;
					}
					
					if(usuarioFacade.alterarUsuarioFacade(usuario)){
						if(temFoto){
							String caminhoUploadCurso = servletContext.getRealPath("/includes") + "/images/usuarios/" + usuario.getEmail() + "\\";
							copiarImagem(fotoUsuario, fotoUsuarioFileName, caminhoUploadCurso);
						}
						
						setUsuarioLogado(usuario);
						
						addActionMessage("Dados alterados com sucesso!");
						return SUCCESS;
					}else{
						addActionError("Erro ao alterar dados. Tente novamente mais tarde!");
						return ERROR;
					}
				}
			} else{
				return ERROR;
			}
		} catch (Exception e) {
			addActionError(ERRO_INESPERADO);
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String enviarSenhaParaUsuario(){
		try {
			if(!validarFormatoEmail(usuario.getEmail())){
				addActionError("Email "+usuario.getEmail()+" inválido!");
				return ERROR;
			} else {
				usuario = usuarioFacade.retornaUsuarioPorEmailFacade(usuario.getEmail());
				
				if(usuario == null){
					addActionError("Usuário não encontrado!");
					return ERROR;
				}else{
					EmailUtils email = new EmailUtils();
					String mensagem = "Prezado(a) " + usuario.getNome() + " sua senha é: " + usuario.getSenha();
					
					String retorno = email.enviaEmailSimples(usuario.getEmail(), usuario.getNome(), "Recuperação de senha!", mensagem);
					
					if(retorno.equals(email.RETORNO_ERRO_ENVIO_EMAIL)){
						addActionError("Ocorreu um erro no envio do email, favor tente novamente mais tarde!");
						return ERROR;
					} else if(retorno.equals(email.RETORNO_ERRO_INESPERADO)){
						addActionError(ERRO_INESPERADO);
						return ERROR;
					} else{
						addActionMessage("Email enviado com sucesso!");
						return SUCCESS;
					}
				}
			}
		} catch (Exception e) {
			addActionError(ERRO_INESPERADO);
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public boolean verificaPreenchimentoFormUsuario(Usuario usuario, String acao){
		if("cadastro".equals(acao)){
			if((usuario.getEmail() != null && !"".equals(usuario.getEmail())) && (usuario.getNome() != null && !"".equals(usuario.getNome())) 
					&& (usuario.getSenha() != null && !"".equals(usuario.getSenha())) && (usuario.getSobrenome() != null && !"".equals(usuario.getSobrenome()))){
				return true;
			}else{
				return false;
			}
		}else{
			if((usuario.getEmail() != null && !"".equals(usuario.getEmail())) && (usuario.getNome() != null && !"".equals(usuario.getNome())) 
					&& (usuario.getSobrenome() != null && !"".equals(usuario.getSobrenome()))){
				return true;
			}else{
				return false;
			}
		}
	}
	
	public boolean validarFormatoEmail(String email) {
		Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
		Matcher m = p.matcher(email);
		if (m.find()) {
			return true;
		} else {
			return false;
		}
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
			addActionError("A imagem não pode ter mais que 4 MB (4096 KB)!");
			return false;
		} else if (!listContentTypes.contains(contentType)) {
			addActionError("Formato de arquivo inválido. Por favor, utilize somente imagens nos formatos JPG, JPEG, GIF, BMP ou PNG!");
			return false;
		} else
			return true;
	}
	
	@SuppressWarnings("resource")
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
			}

			fis = new FileInputStream(arquivo);
			fos = new FileOutputStream(arqOutput);

			origem = fis.getChannel();
			destino = fos.getChannel();

			origem.transferTo(0, origem.size(), destino);
		} catch (Exception e) {
			throw e;
		}
	}

	public File getFotoUsuario() {
		return fotoUsuario;
	}

	public void setFotoUsuario(File fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}

	public String getFotoUsuarioContentType() {
		return fotoUsuarioContentType;
	}

	public void setFotoUsuarioContentType(String fotoUsuarioContentType) {
		this.fotoUsuarioContentType = fotoUsuarioContentType;
	}

	public String getFotoUsuarioFileName() {
		return fotoUsuarioFileName;
	}

	public void setFotoUsuarioFileName(String fotoUsuarioFileName) {
		this.fotoUsuarioFileName = fotoUsuarioFileName;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}
}