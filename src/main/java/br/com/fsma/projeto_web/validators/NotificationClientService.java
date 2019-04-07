package br.com.fsma.projeto_web.validators;

public class NotificationClientService {

	private String message;
	private boolean status;
	private NotificationType notificationType;
	
	
	/**
	 * 
	 * @param message Mensagem da notificação
	 * @param status O status atual da notificação. Se tem notificação, este parâmetro deve ser True.
	 * @param notificationType Esta classe vai encunhar o tipo de alerta a ser emitido ao cliente.
	 */
	public NotificationClientService(
			String message,
			boolean status,
			NotificationType notificationType
			) {
		this.message = message;
		this.status = status;
		this.notificationType = notificationType;
	}

	public String getMessage() {
		return message;
	}

	public boolean isStatus() {
		return status;
	}
	
	public NotificationType getNotificationType() {
		return notificationType;
	}
	
}
