package br.com.fsma.projeto_web.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.fsma.projeto_web.business.BairroServiceImpl;
import br.com.fsma.projeto_web.business.CidadeServiceImpl;
import br.com.fsma.projeto_web.business.EmpresaServiceImpl;
import br.com.fsma.projeto_web.business.EstadoServiceImpl;
import br.com.fsma.projeto_web.entities.Bairro;
import br.com.fsma.projeto_web.entities.Cidade;
import br.com.fsma.projeto_web.entities.Empresa;
import br.com.fsma.projeto_web.entities.Estado;
import br.com.fsma.projeto_web.tx.Transacional;
import br.com.fsma.projeto_web.validators.EmpresaValidator;
import br.com.fsma.projeto_web.validators.NotificationClientService;
import br.com.fsma.projeto_web.validators.NotificationType;

@Named
@ViewScoped
public class EmpresaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EmpresaServiceImpl empresaService;

	@Inject
	private BairroServiceImpl bairroService;

	@Inject
	private CidadeServiceImpl cidadeService;

	@Inject
	private EstadoServiceImpl estadoService;
	
	@Inject
	private EmpresaValidator empresaValidator;

	private Empresa empresa;
	private Bairro bairro;
	private Cidade cidade;
	private Estado estado;

	private List<Empresa> empresas;
	private List<Bairro> bairros;
	private List<Cidade> cidades;
	private List<Estado> estados;

	private boolean editForm = false;
	private boolean updateMode = false;
	private String criterio;
	private String alertClass;
	private NotificationClientService notificationClientService;

	@PostConstruct
	void init() {
		if (empresa == null) {
			empresa = new Empresa();
		}

		if (cidade == null) {
			cidade = new Cidade();
		}

		if (estado == null) {
			estado = new Estado();
		}

		if (bairro == null) {
			bairro = new Bairro();
		}
	}

	public void initAdiciona() {
		notificationClientService = null;
		this.estados = estadoService.buscar();
		editForm = true;
	}

	@Transacional
	public void adiciona() {
		notificationClientService = empresaValidator.adiciona(empresa);
		if (notificationClientService.isStatus()) {
			if (notificationClientService.getNotificationType() == NotificationType.Success) {
				alertClass = "success";
			} else if (notificationClientService.getNotificationType() == NotificationType.Warning) {
				alertClass = "warning";				
			} else {
				alertClass = "danger";				
			}
			return;
		}
		
		empresa.setCidade(cidade);
		empresa.setEstado(estado);
		
		empresaService.adiciona(empresa);
		
		notificationClientService = new NotificationClientService(
				"Empresa Adicionada com sucesso",
				false,
				NotificationType.Success
				);

		restartForm();
	}
	
	public boolean hasNotification() {
		return this.notificationClientService != null;
	}
	
	public void initAtualiza(Empresa empresa) {
		updateMode = true;
		editForm = true;
		this.bairro = this.empresa.getBairro();
		this.cidade = this.empresa.getCidade();
		this.estado = this.empresa.getEstado();
		this.empresa = empresa;
	}
	
	@Transacional
	public String atualiza() {
		empresa.setBairro(bairro);
		empresa.setCidade(cidade);
		empresa.setEstado(estado);
		empresaService.atualiza(empresa);
		restartForm();
		return "/view/empresa/index.xhtml?faces-redirect=true";
	}
	
	@Transacional
	public void remover(Empresa empresa) {
		empresaService.remove(empresa);
		empresas.remove(empresa);
	}
	
	
	
	private void restartForm() {
		alertClass = null;
		editForm = false;
		bairro = new Bairro();
		cidade = new Cidade();
		estado = new Estado();
		empresa = new Empresa();
	}
	
	public void toggleEditMode() {
		editForm = false;
	}
	
	public void processaCidades() {
		empresa.setEstado(estado);
		this.cidades = this.cidadeService.buscaPorEstado(estado);
	}

	public void processaCidadeSelect() {
		cidade = cidadeService.buscaPorId(cidade.getId());
		empresa.setCidade(cidade);
		bairros = this.bairroService.buscaPorCidade(cidade);
	}
	
	public void processaBairroSelect() {
		this.bairro = bairroService.buscaPorId(bairro.getId());
		empresa.setBairro(bairro);
	}
	
	public void processaBairros() {
		this.bairros = this.bairroService.buscaPorCidade(cidade);
	}
	
	public void busca() {
		empresas = empresaService.busca(criterio);
		System.out.println(empresas);
	}
	
	public void buscaPorId() {
		empresa = empresaService.buscaPorId(empresa.getId());
	}
	
	public void buscaPorCriterioEmBairroCidadeEstado() {
		empresas = empresaService.buscaPorCriterioEmBairroCidadeEstado(criterio, bairro, cidade, estado);
	}
	
	public Integer size() {
		return this.empresas.size();
	}
	
	public boolean isMaiorQuerZero() {
		return size() > 0;
	}
	
	public boolean hasBusca() {
		return criterio != null;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public boolean isEditForm() {
		return editForm;
	}


	public boolean isUpdateMode() {
		return updateMode;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public List<Bairro> getBairros() {
		return bairros;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}
	
	public NotificationClientService getNotificationClientService() {
		return notificationClientService;
	}

	public String getAlertClass() {
		return alertClass;
	}

}
