package br.com.fsma.projeto_web.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.fsma.projeto_web.entities.Estado;

@FacesConverter(forClass = Estado.class)
public class EstadoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return null;
		}
		Long id = Long.valueOf(value);
		Estado estado = new Estado();
		estado.setId(id);
		return estado;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}

		Estado estado = (Estado) value;
		if (estado.getId() == null) {
			return null;
		}
		
		return estado.getId().toString();
	}

}
