package br.com.fsma.projeto_web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.fsma.projeto_web.business.EstadoServiceImpl;
import br.com.fsma.projeto_web.entities.Estado;
import br.com.fsma.projeto_web.tx.Transacional;
import br.com.fsma.projeto_web.validators.EstadoValidator;
import br.com.fsma.projeto_web.validators.NotificationClientService;

@Named
@ViewScoped
public class EstadoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstadoServiceImpl estadoService;
	
	private Estado estado;
	private List<Estado> estados = new ArrayList<>();
	
	private boolean editForm = false;
	private boolean updateMode = false;
	
	private String criterio;
	
	private NotificationClientService notificationClientService;
	
	@Inject
	private EstadoValidator estadoValidator;
	private String alertClass;
	private boolean hasBusca = false;
	

	@PostConstruct
	public void init() {
		estado = new Estado();
		
	}

	@Transacional
	public void adiciona() {
		notificationClientService = estadoValidator.adiciona(estado);
		if (notificationClientService != null && notificationClientService.isStatus()) {
			alertClass = new AlertUtil(
					notificationClientService.getNotificationType()
					).select();
			return;
		} else {
			alertClass = new AlertUtil(
					notificationClientService.getNotificationType()
					).select();
		}
		estadoService.adiciona(estado);
		estado = new Estado();
		this.editForm = false;
	}

	@Transacional
	public String atualiza() {
		notificationClientService = estadoValidator.atualiza(estado);
		if (notificationClientService != null && notificationClientService.isStatus()) {
			alertClass = new AlertUtil(
					notificationClientService.getNotificationType()
					).select();
			return null;
		}
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
		hasBusca = false;
		updateMode = false;
		editForm = true;
		estado = new Estado();
	}
	
	public void toggleEditMode() {
		editForm = !editForm;
	}

	public void initAtualizar(Estado estado) {
		hasBusca = false;
		updateMode = true;
		editForm = true;
		this.estado = estado;
	}
	
	public boolean hasBusca() {
		return this.hasBusca;
	}	
	
	public List<Estado> getEstadosPorNomeOuUf() {
		return estadoService.buscarPorNomeOuUf(criterio);
	}
	
	public void busca() {
		notificationClientService = estadoValidator.busca(criterio);
		if (notificationClientService != null && notificationClientService.isStatus()) {
			alertClass = new AlertUtil(
					notificationClientService.getNotificationType()
					).select();
			return;
		}
		hasBusca = true;
		estados = estadoService.buscarPorNomeOuUf(criterio);
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return this.estados;
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

	public NotificationClientService getNotificationClientService() {
		return notificationClientService;
	}
	
	public String getAlertClass() {
		return alertClass;
	}
	
	
}
