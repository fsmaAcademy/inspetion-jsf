package br.com.fsma.projeto_web.validators;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.fsma.projeto_web.entities.Empresa;
import br.com.fsma.projeto_web.repositories.BairroRepositoryImpl;
import br.com.fsma.projeto_web.repositories.CidadeRepositoryImpl;
import br.com.fsma.projeto_web.repositories.EmpresaRepositoryImpl;
import br.com.fsma.projeto_web.repositories.EstadoRepositoryImpl;

@Named
@RequestScoped
public class EmpresaValidator implements Serializable, IValidator<Empresa> {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstadoRepositoryImpl estadoRepository;

	@Inject
	private CidadeRepositoryImpl cidadeRepository;

	@Inject
	private BairroRepositoryImpl bairroRepository;

	@Inject
	private EmpresaRepositoryImpl empresaRepository;

	@Override
	public NotificationClientService adiciona(Empresa empresa) {
		
		if (empresa.getEstado() == null) {
			return new NotificationClientService(
					"Selecione um estado",
					true,
					NotificationType.Warning);
		}
		
		if (empresa.getCidade() == null) {
			return new NotificationClientService(
					"Selecione uma cidade",
					true,
					NotificationType.Warning);
		}
		
		if (empresa.getBairro() == null) {
			return new NotificationClientService(
					"Selecione um bairro",
					true,
					NotificationType.Warning);
		}

		if (empresa.getCnpj() == null
				|| empresa.getCnpj().isEmpty()
				|| empresa.getCnpj().length() < 14
				|| empresa.getCnpj().length() > 18
				) {
			return new NotificationClientService(
					"Digite um com tamanho válido",
					true,
					NotificationType.Warning);
		}
		
		CnpjValidator cnpjValidator = new CnpjValidator(empresa.getCnpj());
		if(!cnpjValidator.isCNPJ()) {
			return new NotificationClientService(
					"Este não é um CNPJ Válido pela receita federal",
					true,
					NotificationType.Warning);					
		}
		
		if (empresa.getNome() == null
				|| empresa.getNome().isEmpty()
				|| empresa.getNome().length() > 150) {
			return new NotificationClientService(
					"Digite um nome válido para a empresa",
					true,
					NotificationType.Warning
					);
		}

		if (empresa.getLogradouro() == null
				|| empresa.getLogradouro().isEmpty()
				|| empresa.getLogradouro().length() > 150) {
			return new NotificationClientService(
					"Digite o logradouro válido da empresa",
					true,
					NotificationType.Warning
					);
		}
		
		if (empresa.getCep() == null
				|| empresa.getCep().isEmpty()
				|| empresa.getCep().length() > 150) {
			return new NotificationClientService(
					"Digite um cep válido para a empresa",
					true,
					NotificationType.Warning
					);
		}

		if (empresaRepository.existe(empresa)) {
			return new NotificationClientService("Esta empresa já consta em nosso cadastro.", true,
					NotificationType.Danger);
		}

		if (!estadoRepository.existePorId(empresa.getEstado().getId())) {
			return new NotificationClientService("Erro ao tentar cadastrar com esse estado.", true,
					NotificationType.Danger);
		}

		if (!cidadeRepository.existePorId(empresa.getCidade().getId())) {
			return new NotificationClientService("Erro ao tentar cadastrar com essa cidade.", true,
					NotificationType.Danger);
		}

		if (!bairroRepository.existePorId(empresa.getBairro().getId())) {
			return new NotificationClientService("Erro ao tentar cadastrar com este bairro", true,
					NotificationType.Danger);
		}

		return null;
	}
	
	@Override
	public NotificationClientService atualiza(Empresa empresa) {
		return this.adiciona(empresa);
	}

	@Override
	public NotificationClientService busca(String criterio) {
		if (criterio == null || criterio.length() < 1) {
			return new NotificationClientService("Digite o nome da empresa que deseja buscar", true,
					NotificationType.Danger);
		}
		return null;
	}

	@Override
	public NotificationClientService remove(Empresa t) {
		// TODO Auto-generated method stub
		return null;
	}
}