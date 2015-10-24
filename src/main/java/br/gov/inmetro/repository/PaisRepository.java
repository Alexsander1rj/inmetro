package br.gov.inmetro.repository;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.inmetro.model.Pais;

@Named
@ViewScoped
public class PaisRepository implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public Pais porId(Long id) {
		return manager.find(Pais.class, id);
	}

	public List<Pais> todos() {
		TypedQuery<Pais> query = manager.createQuery("from Pais", Pais.class);
		
		return query.getResultList();
	}
}