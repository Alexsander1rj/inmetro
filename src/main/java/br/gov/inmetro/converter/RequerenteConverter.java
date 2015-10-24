package br.gov.inmetro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.gov.inmetro.model.Requerente;
import br.gov.inmetro.repository.RequerenteRepository;

@FacesConverter(forClass=Requerente.class)
public class RequerenteConverter implements Converter {
	@Inject
	private RequerenteRepository requerentes;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Requerente requerente = null;
		
		if(value != null && !value.equals(""))
			requerente = requerentes.porId(new Long(value));		
		
		return requerente;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String id = "";
		
		if(value != null)
			id = ((Requerente)value).getId().toString();
		
		return id;
	}
}