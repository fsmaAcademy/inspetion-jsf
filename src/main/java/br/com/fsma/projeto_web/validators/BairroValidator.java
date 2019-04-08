package br.com.fsma.projeto_web.validators;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.fsma.projeto_web.entities.Bairro;
import br.com.fsma.projeto_web.entities.Cidade;
import br.com.fsma.projeto_web.entities.Estado;

@Named
@RequestScoped
public class BairroValidator implements Serializable, IValidator<Bairro> {

	private static final long serialVersionUID = 1L;

	@Override
	public NotificationClientService busca(String criterio) {
		if (criterio == null || criterio.length() < 3) {
			return new NotificationClientService(
					"Preencha os campos de pesquisa",
					true,
					NotificationType.Warning
					);
		}
		return null;
	}

	public NotificationClientService buscaPorEstadoCidade(String criterio, Estado estado, Cidade cidade) {

		if (estado == null || estado.getId() == null) {
			return new NotificationClientService(
					"Selecione o estado",
					true,
					NotificationType.Warning
					);
		}
		
		if (cidade == null || cidade.getId() == null) {
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
					NotificationType.Warning
					);
		}
		
		return null;
	}

	@Override
	public NotificationClientService adiciona(Bairro bairro) {
		
		if (bairro.getCidade().getEstado() == null) {
			return new NotificationClientService(
					"Selecione um estado, por favor.",
					true,
					NotificationType.Warning
					);
		}
		
		if (bairro.getCidade() == null) {
			return new NotificationClientService(
					"Selecione um estado, por favor.",
					true,
					NotificationType.Warning
					);
		}
		
		if (bairro.getNome() == null ||
			bairro.getNome().length() > 100 &&
			bairro.getNome().length() < 3
			) {
				return new NotificationClientService(
						"Campo nome da cidade está vazio",
						true,
						NotificationType.Warning
						);
		}
		
		
		return null;
	}

	@Override
	public NotificationClientService atualiza(Bairro bairro) {
		return this.adiciona(bairro);
	}

}
