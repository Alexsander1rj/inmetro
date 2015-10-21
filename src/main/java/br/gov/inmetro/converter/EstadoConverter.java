package br.gov.inmetro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.gov.inmetro.model.Estado;
import br.gov.inmetro.repository.EstadoRepository;

@FacesConverter(forClass = Estado.class)
public class EstadoConverter implements Converter{
	@Inject
	private EstadoRepository estados;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		Estado estado = null;

		if (valor != null && !valor.equals("")) {
			Long id = new Long(valor);
			estado = estados.estadoPorId(id);
		}		

		return estado;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {
		if (valor != null && !valor.equals("")) {
			Estado estado = (Estado) valor;
			return estado.getId() == null ? null : estado.getId().toString();
		}

		return "";
	}
}