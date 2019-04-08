package br.com.fsma.projeto_web.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.fsma.projeto_web.business.BairroServiceImpl;
import br.com.fsma.projeto_web.business.CidadeServiceImpl;
import br.com.fsma.projeto_web.business.EmpresaServiceImpl;
import br.com.fsma.projeto_web.business.EstadoServiceImpl;
import br.com.fsma.projeto_web.business.FiscalizacaoServiceImpl;
import br.com.fsma.projeto_web.entities.Bairro;
import br.com.fsma.projeto_web.entities.Cidade;
import br.com.fsma.projeto_web.entities.Empresa;
import br.com.fsma.projeto_web.entities.Estado;
import br.com.fsma.projeto_web.entities.Fiscalizacao;
import br.com.fsma.projeto_web.tx.Transacional;
import br.com.fsma.projeto_web.util.DateUtils;
import br.com.fsma.projeto_web.validators.FiscalizacaoValidator;
import br.com.fsma.projeto_web.validators.NotificationClientService;

@Named
@ViewScoped
public class FiscalizacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FiscalizacaoServiceImpl fiscalizacaoService;

	@Inject
	private EmpresaServiceImpl empresaService;

	@Inject
	private BairroServiceImpl bairroService;

	@Inject
	private CidadeServiceImpl cidadeService;

	@Inject
	private EstadoServiceImpl estadoService;
	
	private Fiscalizacao fiscalizacao;
	private Empresa empresa;
	private Bairro bairro;
	private Cidade cidade;
	private Estado estado;
	
	private List<Fiscalizacao> fiscalizacoes = new ArrayList<>();;
	private List<Empresa> empresas = new ArrayList<>();;
	private List<Bairro> bairros = new ArrayList<>();;
	private List<Cidade> cidades = new ArrayList<>();;
	private List<Estado> estados = new ArrayList<>();;
	
	private boolean editForm = false;
	private boolean updateMode = false;
	private String criterio;
	private String alertClass;
	private NotificationClientService notificationClientService;
	private Date dataInicio;
	private Date dataFim;
	private boolean temBusca = false;

	@Inject
	private FiscalizacaoValidator fiscalizacaoValidator;
	
	@PostConstruct
	void init() {
		if (fiscalizacao == null) {
			fiscalizacao = new Fiscalizacao();
		}

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
		fiscalizacao.setData(LocalDate.now());
		this.estados = estadoService.buscar();
		editForm = true;
	}
	
	@Transacional
	public void adiciona() {
		
		fiscalizacao.setEstado(
				estadoService.buscarPorId(estado.getId())
				);
		fiscalizacao.setCidade(cidade);
		fiscalizacao.setBairro(bairro);
		fiscalizacao.setEmpresa(empresa);
		
		
		
		setTemBusca(false);
		
		fiscalizacaoService.adiciona(fiscalizacao);
		bairro = new Bairro();
		cidade = new Cidade();
		estado = new Estado();
		empresa = new Empresa();
		this.editForm = false;
	}
	
	public void busca() {
		setTemBusca(true);
		
		notificationClientService = fiscalizacaoValidator.busca(dataInicio, dataFim);
		if (notificationClientService != null && notificationClientService.isStatus()) {
			alertClass = new AlertUtil(
					notificationClientService.getNotificationType()
					).select();
			return;
		}
		
		if (criterio.isEmpty() || criterio == null) {
			this.fiscalizacoes = fiscalizacaoService.busca(
					DateUtils.asLocalDate(dataInicio),
					DateUtils.asLocalDate(dataFim)
					);
		} else {
			empresa = empresaService.buscaPorNome(criterio);
			this.fiscalizacoes = fiscalizacaoService.busca(
					empresa,
					DateUtils.asLocalDate(dataInicio),
					DateUtils.asLocalDate(dataFim)
					);
		}
		System.out.println("Fiscalizações: " + fiscalizacoes);
	}
	
	public void initAtualiza(Fiscalizacao fiscalizacao) {
		this.fiscalizacao = fiscalizacao;
		this.empresa = this.fiscalizacao.getEmpresa();
		this.bairro = this.fiscalizacao.getBairro();
		this.cidade = this.fiscalizacao.getCidade();
		this.estado = this.fiscalizacao.getEstado();
		this.editForm = true;
		this.updateMode = true;
		
	}
	
	@Transacional
	public String atualiza() {
		fiscalizacao.setEstado(estado);
		fiscalizacao.setCidade(cidade);
		fiscalizacao.setBairro(bairro);
		fiscalizacao.setEmpresa(empresa);
		
		System.out.println("atualizando..." + fiscalizacao);
		fiscalizacaoService.atualiza(fiscalizacao);
		return "/view/fiscalizacao/index.xhtml?faces-redirect=true";
	}
	
	@Transacional
	public void remover(Fiscalizacao fiscalizacao) {
		System.out.println("Removendo..." + fiscalizacao);
		fiscalizacaoService.remove(fiscalizacao);
	}
	
	public void processaCidades() {
		empresa.setEstado(estado);
		cidades = cidadeService.buscaPorEstado(estado);
	}

	public void processaCidadeSelect() {
		cidade = cidadeService.buscaPorId(cidade.getId());
		empresa.setCidade(cidade);
		bairros = bairroService.buscaPorCidade(cidade);
	}
	
	public void processaBairroSelect() {
		bairro = bairroService.buscaPorId(bairro.getId());
		empresa.setBairro(bairro);
		empresas = empresaService.buscaPorBairro(bairro); 
	}
	
	public void processaEmpresaSelect() {
		empresa = empresaService.buscaPorId(empresa.getId());
	}
	
	public boolean hasNotification() {
		return this.notificationClientService != null;
	}
	
	public void toggleEditMode() {
		this.editForm = false;
	}
	
	public boolean isEditMode( ) {
		return this.editForm;
	}
	
	public boolean hasBusca() {
		return isTemBusca();
	}
	
	public Fiscalizacao getFiscalizacao() {
		return fiscalizacao;
	}

	public void setFiscalizacao(Fiscalizacao fiscalizacao) {
		this.fiscalizacao = fiscalizacao;
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

	public void setEditForm(boolean editForm) {
		this.editForm = editForm;
	}

	public boolean isUpdateMode() {
		return updateMode;
	}

	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	public List<Fiscalizacao> getFiscalizacoes() {
		return fiscalizacoes;
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

	public String getAlertClass() {
		return alertClass;
	}

	public NotificationClientService getNotificationClientService() {
		return notificationClientService;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public boolean isTemBusca() {
		return temBusca;
	}

	public void setTemBusca(boolean temBusca) {
		this.temBusca = temBusca;
	}

	
	
	
	
	
	
}
