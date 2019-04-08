package br.com.fsma.projeto_web.bean;

import br.com.fsma.projeto_web.validators.NotificationType;

public class AlertUtil {

	private String alertClass;
	private NotificationType notificationType;
	
	public AlertUtil(NotificationType notificationType) {
		this.notificationType = notificationType;
	}

	public String select() {
		if (notificationType == NotificationType.Success) {
			alertClass = "success";
		} else if (notificationType == NotificationType.Warning) {
			alertClass = "warning";				
		} else {
			alertClass = "danger";								
		}
		
		return alertClass;
	}

	public String getAlertClass() {
		return alertClass;
	}

}