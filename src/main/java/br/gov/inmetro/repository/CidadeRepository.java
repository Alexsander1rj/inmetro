package br.gov.inmetro.repository;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.inmetro.model.Cidade;
import br.gov.inmetro.model.Estado;

@Named
@ViewScoped
public class CidadeRepository implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public List<Cidade> todasCidadesPorEstado(Estado estado){
		TypedQuery<Cidade> query = manager.createQuery("from Cidade c where c.estado = :estado", Cidade.class)
				.setParameter("estado", estado);
		
		return query.getResultList();
	}
	
	public Cidade cidadePorId(Long id){
		return manager.find(Cidade.class, id);		
	}
}
