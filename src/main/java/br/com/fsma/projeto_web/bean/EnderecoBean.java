package br.com.fsma.projeto_web.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.fsma.projeto_web.arguments.EnderecoVM;
import br.com.fsma.projeto_web.business.CidadeServiceImpl;
import br.com.fsma.projeto_web.business.EstadoServiceImpl;
import br.com.fsma.projeto_web.business.interfaces.IEstadoService;
import br.com.fsma.projeto_web.entities.Cidade;
import br.com.fsma.projeto_web.entities.Estado;
import br.com.fsma.projeto_web.enums.TipoEndereco;
import br.com.fsma.projeto_web.tx.Transacional;

@Named
@ViewScoped
public class EnderecoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String categoria;
	private String valorDaBusca;
	private Estado estado;
	private List<Estado> estados;
	private List<Cidade> cidades;
	
	@Inject
	private EstadoServiceImpl estadoService;

	@Inject
	private CidadeServiceImpl cidadeService;
	
	@Inject
	private EntityManager em;
	
	@PostConstruct
	public void init() {
	}
	
	public String busca() {
		System.out.println(categoria + " " + valorDaBusca);
		
		switch (categoria) {
		case "bairro":
			break;

		case "cidade":
			cidades = cidadeService.busca(valorDaBusca);
			break;

		case "estado":
			estados = estadoService.buscarPorNomeOuUf(valorDaBusca);;
			break;
			

		default:
			System.out.println("DEU MERDAAAA");
		}
		
		return null;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getValorDaBusca() {
		return valorDaBusca;
	}

	public void setValorDaBusca(String valorDaBusca) {
		this.valorDaBusca = valorDaBusca;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
		
}
