package br.com.fsma.projeto_web.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class NavigationBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public String home() {
		return "/view/home/home.xhtml?faces-redirect=true";
	}

	public String empresa() {
		return "/view/empresa/index.xhtml?faces-redirect=true";
	}

	public String endereco() {
		return "/view/endereco/index.xhtml?faces-redirect=true";
	}

	public String fiscalizacao() {
		return "/view/fiscalizacao/index.xhtml?faces-redirect=true";
	}

	

}
