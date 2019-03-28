package br.com.fsma.projeto_web.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.fsma.projeto_web.entities.Cidade;

@FacesConverter(forClass = Cidade.class)
public class CidadeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return null;
		}
		Long id = Long.valueOf(value);
		Cidade cidade = new Cidade();
		cidade.setId(id);
		return cidade;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}

		Cidade cidade = (Cidade) value;
		if (cidade.getId() == null) {
			return null;
		}
		
		return cidade.getId().toString();
	}

}
