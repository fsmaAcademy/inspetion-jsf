package br.com.fsma.projeto_web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.fsma.projeto_web.business.CidadeServiceImpl;
import br.com.fsma.projeto_web.business.EstadoServiceImpl;
import br.com.fsma.projeto_web.entities.Cidade;
import br.com.fsma.projeto_web.entities.Estado;
import br.com.fsma.projeto_web.tx.Transacional;

@Named
@ViewScoped
public class CidadeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CidadeServiceImpl cidadeService;

	@Inject
	private EstadoServiceImpl estadoService;
	
	@Inject
	private EntityManager em;
	
	private Cidade cidade;
	private Estado estado;
	
	private List<Cidade> cidades = new ArrayList<>();
	private List<Estado> estados = new ArrayList<>();
	
	private boolean editForm = false;
	private boolean updateMode = false;
	
	private String criterio;
	

	@PostConstruct
	public void init() {
		if (cidade == null) {
			cidade = new Cidade();
		}
		
		if (estado == null) {
			estado = new Estado();
		}
		
		this.estados = this.estadoService.buscar();
	}
	@Transacional
	public void adiciona() {
		estado = estadoService.buscarPorId(estado.getId());
		cidade.setEstado(estado);
		cidadeService.adiciona(cidade);
		cidade = new Cidade();
		this.editForm = false;
	}
	
	@Transacional
	public String atualiza() {
		cidadeService.atualiza(cidade);
		this.editForm = false;
		return "/view/endereco/estado/index.xhtml?faces-redirect=true";
	}
	
	@Transacional
	public void remover(Cidade cidade) {
		cidadeService.remove(cidade);
		cidades.remove(cidade);
	}
	
	public boolean isShowForm() {
		return editForm;
	}
	
	public boolean isUpdateMode() {
		return this.updateMode;
	}
	
	public void initAdiciona() {
		this.updateMode = false;
		this.editForm = true;
		cidade = new Cidade();
		estado = new Estado();
	}
	
	public void toggleEditMode() {
		editForm = !editForm;
	}

	public void initAtualizar(Cidade cidade) {
		this.updateMode = true;
		this.editForm = true;
		this.cidade = cidade;
	}

	public void buscarPorId(Long id) {
		cidade = cidadeService.buscaPorId(id);
	}
	
	public void buscaPorCidadeEmEstadoPorCriterio() {
		this.estado = this.estadoService.buscarPorId(estado.getId());
		this.cidades = this.cidadeService.buscaCidadePorEstadoPorCriterio(criterio, estado);
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estadoService.buscar();
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}
	
	public int getQuantidadeDeCidades() {
		return this.cidades.size();
	}
	
	public boolean hasBusca() {
		return  criterio != null;
	}
	
	public boolean notResult() {
		return getQuantidadeDeCidades() == 0 && criterio != null;
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
