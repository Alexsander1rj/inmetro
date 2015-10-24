package br.gov.inmetro.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.gov.inmetro.enumerator.ClasseExatidao;
import br.gov.inmetro.enumerator.ConfiguracaoEletrica;
import br.gov.inmetro.enumerator.CorrenteMaxima;
import br.gov.inmetro.enumerator.CorrenteNominal;
import br.gov.inmetro.enumerator.DefinicaoSolicitacao;
import br.gov.inmetro.enumerator.GrandezaMedida;
import br.gov.inmetro.enumerator.InterfaceComunicacao;
import br.gov.inmetro.enumerator.SentidosFluxo;
import br.gov.inmetro.enumerator.TensaoNominal;
import br.gov.inmetro.enumerator.TipoAlimEletrica;
import br.gov.inmetro.enumerator.TipoFrequencia;
import br.gov.inmetro.enumerator.TipoInstalacao;
import br.gov.inmetro.enumerator.TipoMostrador;
import br.gov.inmetro.enumerator.TipoRegistrador;
import br.gov.inmetro.model.Medidor;
import br.gov.inmetro.model.Pais;
import br.gov.inmetro.model.Requerente;
import br.gov.inmetro.repository.MedidorRepository;
import br.gov.inmetro.repository.PaisRepository;
import br.gov.inmetro.repository.RequerenteRepository;
import br.gov.inmetro.service.CadastroMedidor;

@Named
@ViewScoped
public class CadastroMedidorBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Medidor medidor = new Medidor();
	private DataModel<Medidor> todosMedidores;
	private List<Requerente> todosRequerentes;
	private List<Pais> todosPaises;
	private UploadedFile uploadFile;
	
	private boolean exibeObsDefSolicitacao;
	private boolean exibeObsTipoInstalacao;	
	private boolean exibeObsConfigEletrica;
	private boolean exibeObsTensaoNominal;
	private boolean exibeObsTipoAlimentacao;
	private boolean exibeObsCorrenteNominal;
	private boolean exibeObsCorrenteMaxima;
	private boolean exibeObsInterfaceCom;
	private boolean exibeObsDispComplementares;
	
	@Inject
	private MedidorRepository medidores;

	@Inject
	private CadastroMedidor cadastro;
	
	@Inject
	private RequerenteRepository requerentes;
	
	@Inject
	private PaisRepository paises;	
	
	@PostConstruct 
	private void inicializar(){
		todosRequerentes = requerentes.todos();
		todosPaises = paises.todos();
	}

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

	public List<Requerente> getTodosRequerentes() {
		return todosRequerentes;
	}

	public void setTodosRequerentes(List<Requerente> todosRequerentes) {
		this.todosRequerentes = todosRequerentes;
	}

	public List<Pais> getTodosPaises() {
		return todosPaises;
	}

	public void setTodosPaises(List<Pais> todosPaises) {
		this.todosPaises = todosPaises;
	}
	
	public DefinicaoSolicitacao[] getDefinicaoSolicitacao(){
		return DefinicaoSolicitacao.values();
	}
	
	public ClasseExatidao[] getClasseExatidao(){
		return ClasseExatidao.values();
	}
	
	public GrandezaMedida[] getGrandezaMedida(){
		return GrandezaMedida.values();
	}
	
	public TipoInstalacao[] getTipoInstalacao(){
		return TipoInstalacao.values();
	}
	
	public ConfiguracaoEletrica[] getConfiguracaoEletrica(){
		return ConfiguracaoEletrica.values();
	}
	
	public SentidosFluxo[] getSentidosFluxo(){
		return SentidosFluxo.values();
	}
	
	public TensaoNominal[] getTensaoNominal(){
		return TensaoNominal.values();
	}
	
	public TipoAlimEletrica[] getTipoAlimentacao(){
		return TipoAlimEletrica.values();
	}
	
	public CorrenteNominal[] getCorrenteNominal(){
		return CorrenteNominal.values();
	}
	
	public CorrenteMaxima[] getCorrenteMaxima(){
		return CorrenteMaxima.values();
	}
	
	public TipoMostrador[] getTipoMostrador(){
		return TipoMostrador.values();
	}
	
	public TipoRegistrador[] getTipoRegistrador(){
		return TipoRegistrador.values();
	}

	public TipoFrequencia[] getTipoFrequencia(){
		return TipoFrequencia.values();
	}
	
	public InterfaceComunicacao[] getInterfaceComunicacao(){
		return InterfaceComunicacao.values();
	}

	public void verificaObsDefSolicitacao() {
		setExibeObsDefSolicitacao(medidor.getDefinicaoSolicitacao().equals(DefinicaoSolicitacao.MODIFICACAO));
	}	
	
	public void verificaObsTipoInstalacao() {
		setExibeObsTipoInstalacao(medidor.getTipoInstalacao().equals(TipoInstalacao.OUTRO));
	}
	
	public void verificaObsConfigEletrica() {
		setExibeObsConfigEletrica(medidor.getConfiguracaoEletrica().equals(ConfiguracaoEletrica.OUTRA));
	}
	
	public void verificaObsTensaoNominal() {
		setExibeObsTensaoNominal(medidor.getTensaoNominal().equals(TensaoNominal.OUTRA));
	}
	
	public void verificaObsCorrenteNominal() {
		setExibeObsCorrenteNominal(medidor.getCorrenteNominal().equals(CorrenteNominal.OUTRA));
	}
	
	public void verificaObsCorrenteMaxima() {
		setExibeObsCorrenteMaxima(medidor.getCorrenteMaxima().equals(CorrenteMaxima.OUTRA));
	}	
	
	public void verificaObsInterface() {
		setExibeObsInterfaceCom(medidor.getInterfaceComunicacao().equals(InterfaceComunicacao.OUTRO));
	}

	public boolean isExibeObsDefSolicitacao() {
		return exibeObsDefSolicitacao;
	}

	public void setExibeObsDefSolicitacao(boolean exibeObsDefSolicitacao) {
		this.exibeObsDefSolicitacao = exibeObsDefSolicitacao;
	}

	public boolean isExibeObsTipoInstalacao() {
		return exibeObsTipoInstalacao;
	}

	public void setExibeObsTipoInstalacao(boolean exibeObsTipoInstalacao) {
		this.exibeObsTipoInstalacao = exibeObsTipoInstalacao;
	}

	public boolean isExibeObsConfigEletrica() {
		return exibeObsConfigEletrica;
	}

	public void setExibeObsConfigEletrica(boolean exibeObsConfigEletrica) {
		this.exibeObsConfigEletrica = exibeObsConfigEletrica;
	}

	public boolean isExibeObsTensaoNominal() {
		return exibeObsTensaoNominal;
	}

	public void setExibeObsTensaoNominal(boolean exibeObsTensaoNominal) {
		this.exibeObsTensaoNominal = exibeObsTensaoNominal;
	}

	public boolean isExibeObsTipoAlimentacao() {
		return exibeObsTipoAlimentacao;
	}

	public void setExibeObsTipoAlimentacao(boolean exibeObsTipoAlimentacao) {
		this.exibeObsTipoAlimentacao = exibeObsTipoAlimentacao;
	}

	public boolean isExibeObsCorrenteNominal() {
		return exibeObsCorrenteNominal;
	}

	public void setExibeObsCorrenteNominal(boolean exibeObsCorrenteNominal) {
		this.exibeObsCorrenteNominal = exibeObsCorrenteNominal;
	}

	public boolean isExibeObsCorrenteMaxima() {
		return exibeObsCorrenteMaxima;
	}

	public void setExibeObsCorrenteMaxima(boolean exibeObsCorrenteMaxima) {
		this.exibeObsCorrenteMaxima = exibeObsCorrenteMaxima;
	}

	public boolean isExibeObsInterfaceCom() {
		return exibeObsInterfaceCom;
	}

	public void setExibeObsInterfaceCom(boolean exibeObsInterfaceCom) {
		this.exibeObsInterfaceCom = exibeObsInterfaceCom;
	}

	public boolean isExibeObsDispComplementares() {
		return medidor.isDispositivosComplementares();
	}

	public void setExibeObsDispComplementares(boolean exibeObsDispositivosComplementares) {
		this.exibeObsDispComplementares = exibeObsDispositivosComplementares;
	}	
}