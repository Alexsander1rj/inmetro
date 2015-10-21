package br.gov.inmetro.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.inmetro.model.Estado;

public class EstadoRepository implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public List<Estado> todos(){
		TypedQuery<Estado> query = manager.createQuery("from Estado", Estado.class);
		
		return query.getResultList();
	}
	
	public Estado estadoPorId(Long id){
		return manager.find(Estado.class, id);		
	}
}
