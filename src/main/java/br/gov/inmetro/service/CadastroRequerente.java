package br.gov.inmetro.service;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.inmetro.model.Requerente;
import br.gov.inmetro.repository.RequerenteRepository;

@Named
@ViewScoped
public class CadastroRequerente implements Serializable{
	private static final long serialVersionUID = 4128959749735660173L;
	
	@Inject
	private RequerenteRepository requerentes;
	
	public void salvar(Requerente requerente){
		requerentes.adicionar(requerente);
	}
	
	public void excluir(Requerente requerente){
		requerentes.remover(requerente);
	}
	
	public void alterar(Requerente requerente){
		requerentes.alterar(requerente);
	}
}
