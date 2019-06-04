package br.com.fsma.projeto_web.validators;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.fsma.projeto_web.entities.Estado;
import br.com.fsma.projeto_web.repositories.EstadoRepositoryImpl;


@Named
@RequestScoped
public class EstadoValidator implements Serializable, IValidator<Estado> {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstadoRepositoryImpl estadoRepository;
	
	@Override
	public NotificationClientService adiciona(Estado estado) {
		
		if (estado.getNome().isEmpty()) {
			return new NotificationClientService(
					"Seu campo de nome do estado está vázio",
					true,
					NotificationType.Warning);
		}
		
		if (estado.getNome().length() < 5 && estado.getNome().length() > 50) {
			return new NotificationClientService(
					"O campo nome do estado deve ter entre 5 à 50 caracteres.",
					true,
					NotificationType.Warning);
		}
		
		if (estado.getUf().isEmpty()) {
			return new NotificationClientService(
					"Seu campo UF esta vázio",
					true,
					NotificationType.Warning);
		}
		
		if (estado.getUf().length() > 2) {
			return new NotificationClientService(
					"O campo UF precisa ter 2 caracteres",
					true,
					NotificationType.Warning);
		}
		
		if (estadoRepository.existe(estado)) {
			return new NotificationClientService(
					"Esse estado já se encontra cadastrado",
					true,
					NotificationType.Danger);
		}
		
		return new NotificationClientService(
				"Estado cadastrado com sucesso",
				false,
				NotificationType.Success);
		
	}
	
	@Override
	public NotificationClientService atualiza(Estado estado) {
		return this.adiciona(estado);
	}
	
	@Override
	public NotificationClientService busca(String criterio) {
		if (criterio == null) {
			return null;
		}
		if (criterio.length() < 2 || criterio.length() > 50) {
			return new NotificationClientService(
					"O campo de busca precisa ter entre 2 à 50 caracteres.",
					true,
					NotificationType.Warning);
		}
		return null;
	}

	@Override
	public NotificationClientService remove(Estado t) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
