package br.com.fsma.projeto_web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.fsma.projeto_web.business.BairroServiceImpl;
import br.com.fsma.projeto_web.business.CidadeServiceImpl;
import br.com.fsma.projeto_web.business.EstadoServiceImpl;
import br.com.fsma.projeto_web.entities.Bairro;
import br.com.fsma.projeto_web.entities.Cidade;
import br.com.fsma.projeto_web.entities.Estado;
import br.com.fsma.projeto_web.tx.Transacional;
import br.com.fsma.projeto_web.validators.BairroValidator;
import br.com.fsma.projeto_web.validators.NotificationClientService;

@Named
@ViewScoped
public class BairroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private BairroServiceImpl bairroService;

	@Inject
	private CidadeServiceImpl cidadeService;

	@Inject
	private EstadoServiceImpl estadoService;

	@Inject
	private EntityManager em;

	private Bairro bairro;
	private Cidade cidade;
	private Estado estado;

	private List<Bairro> bairros = new ArrayList<>();
	private List<Cidade> cidades = new ArrayList<>();
	private List<Estado> estados = new ArrayList<>();

	private boolean editForm = false;
	private boolean updateMode = false;

	private String criterio;
	private String nomeBairro;
	
	private NotificationClientService notificationClientService;
	private String alertClass;
	
	@Inject
	private BairroValidator bairroValidator;

	@PostConstruct
	public void init() {
		bairro = new Bairro();
		cidade = new Cidade();
		estado = new Estado();
		// this.bairros = this.estadoService.buscar();
	}

	@Transacional
	public void adiciona() {
		bairro.setCidade(cidade);
		bairro.getCidade().setEstado(estado);
		bairro.setNome(nomeBairro);
		
		this.notificationClientService = bairroValidator.adiciona(bairro);
		if (this.notificationClientService != null && notificationClientService.isStatus()) {
			alertClass = new AlertUtil(
					notificationClientService.getNotificationType()
					).select();
			return;
		}
		
		
		bairroService.adiciona(bairro);
		this.editForm = false;
		bairro = new Bairro();
		cidade = new Cidade();
		estado = new Estado();
	}

	@Transacional
	public String atualiza() {
		bairro.setCidade(cidade);
		bairro.getCidade().setEstado(estado);
		bairro.setNome(nomeBairro);
		
		this.notificationClientService = bairroValidator.adiciona(bairro);
		if (this.notificationClientService != null && notificationClientService.isStatus()) {
			alertClass = new AlertUtil(
					notificationClientService.getNotificationType()
					).select();
			return null;
		}
		
		
		bairroService.atualiza(bairro);
		this.editForm = false;
		bairro = new Bairro();
		cidade = new Cidade();
		estado = new Estado();
		nomeBairro = "";
		return "/view/endereco/bairro/index.xhtml?faces-redirect=true";
	}

	@Transacional
	public void remover(Bairro bairro) {
		bairroService.remove(bairro);
		bairros.remove(bairro);
	}

	public void selectOneMenuListener(ValueChangeEvent event) {
		Object newValue = event.getNewValue();
		System.out.println(newValue);
	}

	public boolean isShowForm() {
		return editForm;
	}

	public boolean isUpdateMode() {
		return this.updateMode;
	}

	public void processaCidades() {
		this.cidades = this.cidadeService.buscaPorEstado(estado);
	}

	public void processaCidadeSelect() {
		this.cidade = cidadeService.buscaPorId(cidade.getId());
	}

	public void initAdiciona() {
		this.updateMode = false;
		this.editForm = true;
		cidade = new Cidade();
		estado = new Estado();
		bairro = new Bairro();
	}

	public void toggleEditMode() {
		editForm = !editForm;
	}

	public void initAtualizar(Bairro bairro) {

		this.updateMode = true;
		this.editForm = true;
		nomeBairro = bairro.getNome();
		this.bairro = bairro;
	}

	public void buscarPorId(Long id) {
		bairro = bairroService.buscaPorId(id);
	}

	public void buscaBairroPorCriterioEmCidadeEstado() {
		
		if (nomeBairro == null) {
			criterio = "";			
		} else {
			criterio = nomeBairro;			
		}
		
		this.notificationClientService = bairroValidator.busca(criterio);
		if (this.notificationClientService != null && notificationClientService.isStatus()) {
			alertClass = new AlertUtil(
					notificationClientService.getNotificationType()
					).select();
			return;
		}
		
		this.bairros = this.bairroService.buscaBairroPorCriterioEmCidadeEstado(criterio, cidade);

		System.out.println(this.bairros);
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public List<Bairro> getBairros() {
		return bairros;
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

	public int getQuantidadeDeBairros() {
		return this.bairros.size();
	}

	public boolean hasBusca() {
		return criterio != null;
	}

	public boolean notResult() {
		return getQuantidadeDeBairros() == 0 && criterio != null;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() {
		return this.cidades;
	}

	public String getNomeBairro() {
		return nomeBairro;
	}

	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	public NotificationClientService getNotificationClientService() {
		return notificationClientService;
	}

	public String getAlertClass() {
		return alertClass;
	}
	
	

}
