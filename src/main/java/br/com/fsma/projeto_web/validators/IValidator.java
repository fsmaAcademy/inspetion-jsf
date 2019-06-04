package br.com.fsma.projeto_web.validators;

public interface IValidator<T> {
	
	public NotificationClientService busca(String criterio);
	public NotificationClientService adiciona(T t);
	public NotificationClientService atualiza(T t);
	public NotificationClientService remove(T t);

}
