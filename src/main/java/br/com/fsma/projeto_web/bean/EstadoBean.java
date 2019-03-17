package br.com.fsma.projeto_web.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.fsma.projeto_web.business.EstadoServiceImpl;
import br.com.fsma.projeto_web.entities.Estado;

@Named
@ViewScoped
public class EstadoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EstadoServiceImpl _service;
	private Estado estado;
	private List<Estado> estados;
	
	@PostConstruct
	public void init() {
		if (estado == null) {
			estado = new Estado();
		}
	}

	public String criar(Estado estado) {
		estado = _service.criar(estado);
		return "";
	}
	
	public String buscar() {
		estados = _service.buscar();
		return "";
	}
	
	public String buscarPorId(Long id) {
		estado = _service.buscarPorId(id);
		return "";
	}
	
	public String atualizar(Estado estado) {
		estado = _service.atualizar(estado);
		return "";
	}
	
	public String remover(Estado estado) {
		estado = _service.remover(estado);
		return "";
	}
	
	public String buscarPorUf(String uf) {
		estado = _service.buscarPorUf(uf);
		return "";
	}

	public Estado get_estado() {
		return estado;
	}

	public void set_estado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	
}
