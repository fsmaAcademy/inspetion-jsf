package br.com.fsma.projeto_web.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.fsma.projeto_web.entities.Bairro;

@FacesConverter(forClass = Bairro.class)
public class BairroConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return null;
		}
		Long id = Long.valueOf(value);
		Bairro bairro = new Bairro();
		bairro.setId(id);
		return bairro;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}

		Bairro bairro = (Bairro) value;
		if (bairro.getId() == null) {
			return null;
		}
		
		return bairro.getId().toString();
	}

}
