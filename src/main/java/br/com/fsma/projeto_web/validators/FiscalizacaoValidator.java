package br.com.fsma.projeto_web.validators;

import java.io.Serializable;
import java.util.Date;

import br.com.fsma.projeto_web.entities.Fiscalizacao;

public class FiscalizacaoValidator implements Serializable, IValidator<Fiscalizacao> {	
	
	private static final long serialVersionUID = 1L;

	@Override
	public NotificationClientService busca(String criterio) {
		if (criterio.length() > 2 && criterio.length() < 150) {
			return new NotificationClientService(
					"Digite o nome da empresa",
					true,
					NotificationType.Warning
					);
		}
		return null;
	}
	
	
	// O Usuário pode puxar todas as empresas de uma data a outra...
	public NotificationClientService busca(Date dataInicio, Date dataFim) {
		if (dataInicio == null) {
			return new NotificationClientService(
					"Digite a data de inicio da busca",
					true,
					NotificationType.Warning
					);
		}
		
		if (dataFim == null) {
			return new NotificationClientService(
					"Digite a data de Fim da busca",
					true,
					NotificationType.Warning
					);
		}
		
		return null;
	}

	@Override
	public NotificationClientService adiciona(Fiscalizacao fiscalizacao) {
		if (fiscalizacao.getEstado() == null || fiscalizacao.getEstado().getId() == null) {
			return new NotificationClientService(
					"Selecione um estado",
					true,
					NotificationType.Warning);
		}
		
		if (fiscalizacao.getCidade() == null) {
			return new NotificationClientService(
					"Selecione uma cidade",
					true,
					NotificationType.Warning);
		}
		
		if (fiscalizacao.getBairro() == null) {
			return new NotificationClientService(
					"Selecione um bairro",
					true,
					NotificationType.Warning);
		}
		
		if(fiscalizacao.getNome() == null || fiscalizacao.getNome().isEmpty()) {
			return new NotificationClientService(
					"O campo nome está vazio",
					true,
					NotificationType.Warning);			
		}
		
		if (fiscalizacao.getNome().length() < 3 && fiscalizacao.getNome().length() > 150) {
			return new NotificationClientService(
					"O campo nome deve ter entre 3 a 150 caracteres",
					true,
					NotificationType.Warning);						
		}
		
		if(fiscalizacao.getLogradouro() == null || fiscalizacao.getLogradouro().isEmpty()) {
			return new NotificationClientService(
					"O campo Logradouro está vazio",
					true,
					NotificationType.Warning);			
		}
		
		if (fiscalizacao.getLogradouro().length() < 3 && fiscalizacao.getLogradouro().length() > 150) {
			return new NotificationClientService(
					"O campo Logradouro deve ter entre 3 a 150 caracteres",
					true,
					NotificationType.Warning);						
		}
		
		if(fiscalizacao.getCep() == null || fiscalizacao.getCep().isEmpty()) {
			return new NotificationClientService(
					"O campo CEP está vazio",
					true,
					NotificationType.Warning);			
		}
		
		fiscalizacao.setCep(fiscalizacao.getCep().trim().replace(".", "").replace("-", ""));
		if (fiscalizacao.getCep().length() != 8) {
			return new NotificationClientService(
					"O campo CEP deve ter apenas 8 caracteres.",
					true,
					NotificationType.Warning);						
		}
		
		return null;
	}

	@Override
	public NotificationClientService atualiza(Fiscalizacao fiscalizacao) {
		return this.adiciona(fiscalizacao);
	}


	@Override
	public NotificationClientService remove(Fiscalizacao t) {
		// TODO Auto-generated method stub
		return null;
	}

}
