package br.gov.inmetro.repository;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import br.gov.inmetro.model.Requerente;

@Named
@ViewScoped
public class RequerenteRepository implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public void adicionar(Requerente requerente) {
		EntityTransaction trx = manager.getTransaction();	
		
		trx.begin();
		
		manager.persist(requerente);	

		trx.commit();
	}
	
	public void remover(Requerente requerente) {
		EntityTransaction trx = manager.getTransaction();

		trx.begin();
		
		requerente = manager.find(Requerente.class, requerente.getId());

		manager.remove(requerente);

		trx.commit();
	}	

	public Requerente porId(Long id) {
		return manager.find(Requerente.class, id);
	}

	public List<Requerente> todos() {
		TypedQuery<Requerente> query = manager.createQuery(
				"from Requerente", Requerente.class);
		return query.getResultList();
	}
	
	public void alterar(Requerente requerente) {
		EntityTransaction trx = manager.getTransaction();

		trx.begin();
		
		requerente = manager.merge(requerente);

		manager.remove(requerente);

		trx.commit();
	}	

}