package br.com.fsma.projeto_web.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.fsma.projeto_web.entities.Empresa;

@FacesConverter(forClass = Empresa.class)
public class EmpresaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return null;
		}
		Long id = Long.valueOf(value);
		Empresa empresa = new Empresa();
		empresa.setId(id);
		return empresa;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}

		Empresa empresa = (Empresa) value;
		if (empresa.getId() == null) {
			return null;
		}
		
		return empresa.getId().toString();
	}

}
