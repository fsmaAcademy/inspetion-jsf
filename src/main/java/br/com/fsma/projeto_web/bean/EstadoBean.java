package br.com.fsma.projeto_web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.fsma.projeto_web.business.EstadoServiceImpl;
import br.com.fsma.projeto_web.entities.Estado;
import br.com.fsma.projeto_web.tx.Transacional;

@Named
@ViewScoped
public class EstadoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstadoServiceImpl estadoService;
	@Inject
	private EntityManager em;
	private Estado estado;
	private List<Estado> estados = new ArrayList<>();
	
	private boolean editForm = false;
	private boolean updateMode = false;
	
	private String criterio;
	

	@PostConstruct
	public void init() {
		if (estado == null) {
			estado = new Estado();
		}
		System.out.println("----------------->>>> EstadoBean.Init()");
	}

	@Transacional
	public void adiciona() {
		estadoService.adiciona(estado);
		estado = new Estado();
		this.editForm = false;
	}
	
	@Transacional
	public String atualiza() {
		estadoService.atualizar(estado);
		this.editForm = false;
		return "/view/endereco/estado/index.xhtml?faces-redirect=true";
	}
	
	@Transacional
	public void remover(Estado estado) {
		estadoService.remover(estado);
		estados.remove(estado);
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
		estado = new Estado();
	}
	
	public void toggleEditMode() {
		editForm = !editForm;
	}

	public void initAtualizar(Estado estado) {
		this.updateMode = true;
		this.editForm = true;
		this.estado = estado;
	}

	public void buscarPorId(Long id) {
		estado = estadoService.buscarPorId(id);
	}	
	
	public List<Estado> getEstadosPorNomeOuUf() {
		return estadoService.buscarPorNomeOuUf(this.criterio);
	}
	
	public void buscaPorCriterio() {		
		this.estados = this.getEstadosPorNomeOuUf();
	}

	public void buscarPorUf(String uf) {
		estado = estadoService.buscarPorUf(uf);
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
	
	public int size() {
		return this.estados.size();
	}
	
	
}
