package br.com.fsma.projeto_web.validators;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.fsma.projeto_web.entities.Fiscal;
import br.com.fsma.projeto_web.repositories.FiscalRepositoryImpl;

@Named
@RequestScoped
public class FiscalValidator implements Serializable, IValidator<Fiscal> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FiscalRepositoryImpl fiscalRepository;

	@Override
	public NotificationClientService busca(String criterio) {
		
		if (criterio == null) {
			return new NotificationClientService(
					"O campo de pesquisa esta nulo",
					true,
					NotificationType.Warning
					);
		}
		
		if (criterio.length() < 3) {
			return new NotificationClientService(
					"Você precisa ter mais de 3 caracteres para sua pesquisa",
					true,
					NotificationType.Warning
					);
		}
		
		return null;
	}

	@Override
	public NotificationClientService adiciona(Fiscal fiscal) {
		
		if (fiscal == null) {
			return new NotificationClientService(
					"Você está tentando adicionar um fiscal fantasma!",
					true,
					NotificationType.Warning
					);
		}
		
		if (fiscal.getNome().length() < 5) {
			return new NotificationClientService(
					"Ops... o nome do usuário precisa ter pelo menos 5 caracteres!",
					true,
					NotificationType.Warning
					);
		}
		
		if (fiscal.getCpf().length() != 11) {
			return new NotificationClientService(
					"Ops... O Cpf precisa ter 11 caracteres",
					true,
					NotificationType.Warning
					);
		}
		
		if (this.fiscalRepository.buscar(fiscal.getCpf()) != null) {
			return new NotificationClientService(
					"CPF já esta cadastrado no sistema",
					true,
					NotificationType.Warning
					);
		}
		
		if (fiscal.getDataNascimento() == null) {
			return new NotificationClientService(
					"Digite uma data de nascimento",
					true,
					NotificationType.Warning
					);
		}
		
		return null;
	}

	@Override
	public NotificationClientService atualiza(Fiscal t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotificationClientService remove(Fiscal t) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
