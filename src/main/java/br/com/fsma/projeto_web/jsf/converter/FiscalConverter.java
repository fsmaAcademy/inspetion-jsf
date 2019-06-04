package br.com.fsma.projeto_web.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.fsma.projeto_web.entities.Bairro;
import br.com.fsma.projeto_web.entities.Fiscal;

@FacesConverter(forClass = Fiscal.class)
public class FiscalConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return null;
		}
		Long id = Long.valueOf(value);
		Fiscal fiscal = new Fiscal();
		fiscal.setId(id);
		return fiscal;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}

		Fiscal fiscal = (Fiscal) value;
		if (fiscal.getId() == null) {
			return null;
		}
		
		return fiscal.getId().toString();
	}

}
