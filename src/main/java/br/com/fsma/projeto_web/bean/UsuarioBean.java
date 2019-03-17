package br.com.fsma.projeto_web.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;
//import javax.enterprise.context.ApplicationScoped;
//import javax.faces.view.ViewScoped;
//import javax.enterprise.context.RequestScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.fsma.projeto_web.entities.Usuario;
import br.com.fsma.projeto_web.repositories.UsuarioRepository;
import br.com.fsma.projeto_web.tx.Transacional;

@Named
@ViewScoped
public class UsuarioBean implements Serializable {


	@Inject
	private HttpSession session;
	@Inject
	private UsuarioRepository _repository;
	private Usuario _usuario;
	
	public Usuario getUsuario() {
		return _usuario;
	}

	@PostConstruct
	public void init() {
		if (_usuario == null) {
			_usuario = new Usuario();
		}
	}

	public String adiciona() throws Exception {
		try {
			_repository.adiciona(_usuario);			
		} catch (Exception e) {
			throw new Exception("Erro ao tentar adicionar um usuário");
		}
		return "/view/login/login.xhtml?faces-redirect=true";		
	}
	
}
