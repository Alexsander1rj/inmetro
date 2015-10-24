package br.gov.inmetro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.gov.inmetro.model.Pais;
import br.gov.inmetro.repository.PaisRepository;

@FacesConverter(forClass=Pais.class)
public class PaisConverter implements Converter{
	@Inject
	private PaisRepository paises;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pais pais = null;
		
		if(value != null && !value.equals(""))
			pais = paises.porId(new Long(value));
			
		return pais;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String id = "";
		
		if(value != null)
			id = ((Pais)value).getId().toString();
			
		return id;
	}
}