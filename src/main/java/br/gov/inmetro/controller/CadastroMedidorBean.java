package br.gov.inmetro.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.gov.inmetro.model.Medidor;
import br.gov.inmetro.repository.MedidorRepository;
import br.gov.inmetro.service.CadastroMedidor;

@Named
@ViewScoped
public class CadastroMedidorBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Medidor medidor = new Medidor();
	private DataModel<Medidor> todosMedidores;
	private UploadedFile uploadFile;
	
	@Inject
	private MedidorRepository medidores;

	@Inject
	private CadastroMedidor cadastro;

	public void salvar(ActionEvent actionEvent) {
		Date dtHoje = Calendar.getInstance().getTime();

		medidor.setDataInclusao(dtHoje);
		
		medidor.setFoto(uploadFile.getContents());

		cadastro.salvar(medidor);

		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("TESTE, TESTE"));

		medidor = new Medidor();

		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("gerenciaMedidor.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent actionEvent) {
		cadastro.excluir(medidor);

		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Medidor " + medidor.getModelo() + " exclu�do com sucesso!"));

		medidor = new Medidor();
	}

	public Medidor getMedidor() {
		return medidor;
	}

	public void setMedidor(Medidor medidor) {
		this.medidor = medidor;
	}

	public DataModel<Medidor> getTodosMedidores() {
		todosMedidores = new ListDataModel<Medidor>(medidores.todos());

		return todosMedidores;
	}

	public void setTodosMedidores(DataModel<Medidor> todosMedidores) {
		this.todosMedidores = todosMedidores;
	}

	public UploadedFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(UploadedFile uploadFile) {
		this.uploadFile = uploadFile;
	}	
}