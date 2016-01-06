package br.gov.inmetro.repository;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import br.gov.inmetro.model.Medidor;
import br.gov.inmetro.model.MedidorPK;

@Named
@ApplicationScoped
public class MedidorRepository implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public void adicionar(Medidor medidor) {
		EntityTransaction trx = manager.getTransaction();	
		
		trx.begin();
		
		manager.persist(medidor);	

		trx.commit();
	}
	
	public void remover(Medidor medidor) {
		EntityTransaction trx = manager.getTransaction();

		trx.begin();
		
		medidor = manager.find(Medidor.class, medidor.getId());

		manager.remove(medidor);

		trx.commit();
	}	

	public Medidor porId(MedidorPK id) {
		return manager.find(Medidor.class, id);
	}

	public List<Medidor> todos() {
		TypedQuery<Medidor> query = manager.createQuery("from Medidor", Medidor.class);
		
		return query.getResultList();
	}
	
	public void alterar(Medidor medidor) {
		EntityTransaction trx = manager.getTransaction();

		trx.begin();
		
		medidor = manager.merge(medidor);

		manager.remove(medidor);

		trx.commit();
	}	
}