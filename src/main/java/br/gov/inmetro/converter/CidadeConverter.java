package br.gov.inmetro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.gov.inmetro.model.Cidade;
import br.gov.inmetro.repository.CidadeRepository;

@FacesConverter(forClass=Cidade.class)
public class CidadeConverter implements Converter{
	@Inject
	private CidadeRepository cidades;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		Cidade cidade = null;
		
		if( valor != null && !valor.equals(""))
			cidade = cidades.cidadePorId(new Long(valor));
		
		return cidade;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {
		if( valor != null && !valor.equals(""))
		{
			Cidade cidade = (Cidade)valor;
			
			return cidade.getId().toString();
		}
		
		return "";
	}
}