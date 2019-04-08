package br.com.fsma.projeto_web.validators;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.fsma.projeto_web.entities.Cidade;
import br.com.fsma.projeto_web.entities.Estado;
import br.com.fsma.projeto_web.repositories.CidadeRepositoryImpl;

@Named
@RequestScoped
public class CidadeValidator implements Serializable, IValidator<Cidade> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	CidadeRepositoryImpl cidadeRepository;

	@Override
	public NotificationClientService busca(String criterio) {
		if (criterio == null || criterio.length() < 3) {
			return new NotificationClientService(
					"O campo de pesquisa esta vázio",
					true,
					NotificationType.Warning);
		}
		return null;
	}
	
	public NotificationClientService buscaPorEstado(String criterio, Estado estado) {
		
		if (estado == null || estado.getId() == null) {
			return new NotificationClientService(
					"Selecione o estado",
					true,
					NotificationType.Warning
					);
		}
		
		if (criterio == null || criterio.length() < 3) {
			return new NotificationClientService(
					"O campo de pesquisa esta vázio",
					true,
					NotificationType.Warning);
		}
		return null;
	}

	@Override
	public NotificationClientService adiciona(Cidade cidade) {
		
		if (cidade.getEstado() == null || cidade.getEstado().getId() == null) {
			return new NotificationClientService(
					"Selecione um estado, por gentileza.",
					true,
					NotificationType.Warning);
		}
		
		if (cidade.getNome() == null || cidade.getNome().length() < 3) {
			return new NotificationClientService(
					"Seu campo de nome da cidade está vázio",
					true,
					NotificationType.Warning);
		}
		
		if (cidadeRepository.existe(cidade)) {
			return new NotificationClientService(
					"Esta cidade ja esta cadastrada.",
					true,
					NotificationType.Warning);
		}
		
		return new NotificationClientService(
				"Cidade adicionada com sucesso",
				false,
				NotificationType.Success);
	}

	@Override
	public NotificationClientService atualiza(Cidade cidade) {
		return this.adiciona(cidade);
	}

}
