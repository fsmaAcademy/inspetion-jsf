package br.com.fsma.projeto_web.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.fsma.projeto_web.business.CidadeServiceImpl;
import br.com.fsma.projeto_web.entities.Cidade;
import br.com.fsma.projeto_web.tx.Transacional;

@Named
@SessionScoped
public class CidadeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	@Inject
	private CidadeServiceImpl cidadeService;

	private Cidade cidade;
	private List<Cidade> cidades;
	
	@PostConstruct
	public void init() {
		if (cidade == null)
			cidade = new Cidade();
	}
	
	public String initAtualiza(Long id) {
		this.cidade = cidadeService.buscaPorId(id);
		return "/view/cidade/atualizaCidade.xhtml?faces-redirect=true";
	}
	
	@Transacional
	public void salvar() {
		cidadeService.adiciona(cidade);
	}
	
	@Transacional
	public void remove(Cidade cidade) {
		cidadeService.remove(cidade);
		cidade = null;
	}
	
	@Transacional
	public String atualiza() {
		cidadeService.atualiza(cidade);
		cidade = null;
		return "/view/cidade/index.xhtml?faces-redirect=true";
	}
	

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

}
