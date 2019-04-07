package br.com.fsma.projeto_web.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.fsma.projeto_web.entities.Empresa;
import br.com.fsma.projeto_web.entities.Fiscalizacao;

@FacesConverter(forClass = Fiscalizacao.class)
public class FiscalizacaoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return null;
		}
		Long id = Long.valueOf(value);
		Fiscalizacao fiscalizacao = new Fiscalizacao();
		fiscalizacao.setId(id);
		return fiscalizacao;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}

		Fiscalizacao fiscalizacao = (Fiscalizacao) value;
		if (fiscalizacao.getId() == null) {
			return null;
		}
		
		return fiscalizacao.getId().toString();
	}

}
