package br.gov.inmetro.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.inmetro.model.Usuario;

public class UsuarioRepository implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public List<Usuario> todos(){
		TypedQuery<Usuario> query = manager.createQuery("from usuario", Usuario.class);
		
		return query.getResultList();
	}
	
	public Usuario usuarioPorId(String id){
		return manager.find(Usuario.class, id);		
	}
}