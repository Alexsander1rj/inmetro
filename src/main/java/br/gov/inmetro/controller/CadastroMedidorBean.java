package br.gov.inmetro.controller;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
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
import br.gov.inmetro.enumerator.TipoFases;
import br.gov.inmetro.enumerator.TipoFrequencia;
import br.gov.inmetro.enumerator.TipoInstalacao;
import br.gov.inmetro.enumerator.TipoMostrador;
import br.gov.inmetro.enumerator.TipoRegistrador;
import br.gov.inmetro.model.Medidor;
import br.gov.inmetro.model.MedidorPK;
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

		cadastro.salvar(medidor);

		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage(
				ResourceBundle.getBundle("messages_labels").getString("labels.medidor") + " " + 
				        medidor.getModelo() + " " + 
					    ResourceBundle.getBundle("messages_labels").getString("labels.msg.AddSucesso") ) );

		medidor = new Medidor();
		
		context.getExternalContext().getFlash().setKeepMessages(true);

		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("gerenciaMedidor.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void uploadImagem(FileUploadEvent evento){
		uploadFile = evento.getFile();
		
		medidor.setFoto(uploadFile.getContents());
	}

	public void excluir(ActionEvent actionEvent) {
		cadastro.excluir(medidor);

		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage(
				ResourceBundle.getBundle("messages_labels").getString("labels.medidor") + " " + 
		        medidor.getModelo() + " " + 
			    ResourceBundle.getBundle("messages_labels").getString("labels.msg.ExclusaoSucesso") ) );

		medidor = new Medidor();
	}
	
	public StreamedContent getImagem() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();        
        
        ServletContext scontext = (ServletContext) context.getExternalContext().getContext();
        
		String sourceFileName = scontext.getRealPath("/resources/figura/sem-foto.jpg");
        
        byte[] imagem;
        
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String medidorId = context.getExternalContext().getRequestParameterMap().get("medidorId");
            
            Medidor medidor = medidores.porId(new MedidorPK(medidorId));
            
			if (medidor.getFoto() == null)
				imagem = imagemToByte(sourceFileName);
			else
				imagem = medidor.getFoto();					
            		
            return new DefaultStreamedContent(new ByteArrayInputStream(imagem));
        }
    }
	
	public byte[] imagemToByte(String image) throws IOException {
	    InputStream is = null;
	    byte[] buffer = null;
	    is = new FileInputStream(image);
	    buffer = new byte[is.available()];
	    is.read(buffer);
	    is.close();
	    return buffer;
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
	
	public TipoFases[] getTipoFases(){
		return TipoFases.values();
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
		boolean interfaceOutro = medidor.getInterfaceComunicacao().contains(InterfaceComunicacao.OUTRO);
		
		if(interfaceOutro)
			RequestContext.getCurrentInstance().update("form:obsInterface");

		setExibeObsInterfaceCom(interfaceOutro);
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
}