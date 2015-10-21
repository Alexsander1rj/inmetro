package br.gov.inmetro.service;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.inmetro.model.Medidor;
import br.gov.inmetro.model.Requerente;
import br.gov.inmetro.repository.MedidorRepository;
import br.gov.inmetro.repository.RequerenteRepository;

@Named
@ViewScoped
public class CadastroMedidor implements Serializable{
	private static final long serialVersionUID = 4128959749735660173L;
	
	@Inject
	private MedidorRepository medidores;
	
	public void salvar(Medidor medidor){
		medidores.adicionar(medidor);
	}
	
	public void excluir(Medidor medidor){
		medidores.remover(medidor);
	}
	
	public void alterar(Medidor medidor){
		medidores.alterar(medidor);
	}
}
