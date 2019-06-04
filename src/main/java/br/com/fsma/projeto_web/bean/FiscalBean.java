package br.com.fsma.projeto_web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.fsma.projeto_web.business.FiscalServiceImpl;
import br.com.fsma.projeto_web.entities.Fiscal;
import br.com.fsma.projeto_web.tx.Transacional;
import br.com.fsma.projeto_web.validators.FiscalValidator;
import br.com.fsma.projeto_web.validators.NotificationClientService;

@Named
@ViewScoped
public class FiscalBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FiscalServiceImpl fiscalService;
	
	private Fiscal fiscal;
	private List<Fiscal> fiscais = new ArrayList<>();
	private NotificationClientService notificationClientService;
	
	@Inject
	private FiscalValidator fiscalValidator;

	private boolean editForm = false;
	private boolean updateMode = false;
	private String alertClass;
	private String criterio;
	private boolean hasBusca = false;

	@PostConstruct
	void init() {
		fiscal = new Fiscal();
	}
	
	public void initAdiciona() {
		hasBusca = false;
		editForm = true;
		this.updateMode = false;
		fiscal = new Fiscal();
	}
	
	@Transacional
	public void adiciona() {
		notificationClientService = fiscalValidator.adiciona(fiscal);
		if (hasNotification()) {
			alertClass = new AlertUtil(notificationClientService.getNotificationType()).select();
			return;
		}
		this.fiscalService.adiciona(fiscal);
		restartForm();
	}
	
	public void initAtualiza(Fiscal fiscal) {
		hasBusca = false;
		this.updateMode = true;
		editForm = true;
		this.fiscal = fiscal;
	}
	
	@Transacional
	public String atualiza() {
		System.out.println(this.fiscal);
		fiscalService.atualiza(this.fiscal);
		restartForm();
		return "/view/fiscal/index.xhtml?faces-redirect=true";
	}
	
	@Transacional
	public void remover(Fiscal fiscal) {
		Fiscal fiscalSelecionado = fiscalService.busca(fiscal);
		fiscalService.remove(fiscalSelecionado);
		fiscais.remove(fiscal);
	}
	
	public void busca() {
		notificationClientService = fiscalValidator.busca(criterio);
		if (hasNotification()) {
			alertClass = new AlertUtil(notificationClientService.getNotificationType()).select();
			return;
		}
		hasBusca = true;
		fiscais = fiscalService.buscar(criterio);
	}
	
	private void restartForm() {
		this.editForm = false;
	}
	
	private boolean hasNotification() {
		return notificationClientService != null && notificationClientService.isStatus();
	}

	public Fiscal getFiscal() {
		return fiscal;
	}

	public NotificationClientService getNotificationClientService() {
		return notificationClientService;
	}

	public boolean isEditForm() {
		return editForm;
	}
	
	public String getAlertClass() {
		return alertClass;
	}
	
	public String getCriterio() {
		return criterio;
	}
	
	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}
	
	public boolean hasBusca() {
		return criterio != null;
	}
	
	public void toggleEditMode() {
		editForm = !editForm;
	}
	
	public boolean isNotUpdateMode() {
		return isUpdateMode() == false;
	}
	
	public List<Fiscal> getFiscais() {
		return this.fiscais;
	}

	public boolean isUpdateMode() {
		return updateMode;
	}
	
}
