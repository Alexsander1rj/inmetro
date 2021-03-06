package br.gov.inmetro.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import br.gov.inmetro.model.Cidade;
import br.gov.inmetro.model.Estado;
import br.gov.inmetro.model.Requerente;
import br.gov.inmetro.repository.CidadeRepository;
import br.gov.inmetro.repository.EstadoRepository;
import br.gov.inmetro.repository.RequerenteRepository;
import br.gov.inmetro.service.CadastroRequerente;
import br.gov.inmetro.util.RelatorioWeb;

@Named(value="cadastroRequerenteBean")
@ApplicationScoped
public class CadastroRequerenteBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Requerente requerente = new Requerente();
	private DataModel<Requerente> todosRequerentes;
	private List<Estado> todosEstados;
	private List<Cidade> cidadesPorEstado;
	private Estado estado;
	private User usuario;

	@Inject
	private RequerenteRepository requerentes;

	@Inject
	private CadastroRequerente cadastro;
	
	@Inject
	private EstadoRepository estados;

	@Inject
	private CidadeRepository cidades;
	
	@PostConstruct
	public void carregaLista() {
		if(usuario == null)
			usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		estado = null;
		
		todosRequerentes = new ListDataModel<Requerente>(requerentes.todos());
		
		todosEstados = estados.todos();
		
		if (!FacesContext.getCurrentInstance().isPostback()) {
			todosEstados = estados.todos();
		}
	}

	public void salvar(ActionEvent actionEvent) {
		Date dtHoje = Calendar.getInstance().getTime();

		requerente.setDataInclusao(dtHoje);

		cadastro.salvar(requerente);

		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("TESTE, TESTE"));

		requerente = new Requerente();

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("gerenciaRequerente.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent actionEvent) {
		cadastro.excluir(requerente);

		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Requerente " + requerente.getRazaoNome()
						+ " exclu�do com sucesso!"));

		requerente = new Requerente();

		carregaLista();
	}

	public void alterar(ActionEvent actionEvent) {
		cadastro.alterar(requerente);

		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null,
				new FacesMessage("Requerente " + requerente.getRazaoNome() + " alterado com sucesso!"));

		requerente = new Requerente();

		carregaLista();
	}

	public void imprimir() {
		List<Requerente> listaRequerentes = new ArrayList<Requerente>();
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext scontext = (ServletContext) context.getExternalContext().getContext();
		
		String sourceFileName = scontext.getRealPath("/WEB-INF/classes/br/gov/inmetro/jasper/requerentes.jasper");

		for(Requerente requerente : todosRequerentes)
			listaRequerentes.add(requerente);

		context.responseComplete();     

		HashMap<String, Object> parameters = new HashMap<String, Object>();
		
		InputStream image = this.getClass().getResourceAsStream("inmetro_rel.jpg");
		
		parameters.put("LOGO_REPORT", image);

		RelatorioWeb relatorio = new RelatorioWeb(context, sourceFileName, parameters, listaRequerentes);
		
		relatorio.exportaArquivo();
		
		System.out.println(scontext.getContextPath());
		System.out.println(scontext.getRealPath("/figura/"));
		System.out.println(scontext.getServletContextName());
	}

	public Requerente getRequerente() {
		return requerente;
	}

	public void setRequerente(Requerente requerente) {
		this.requerente = requerente;
	}

	public DataModel<Requerente> getTodosRequerentes() {
		return todosRequerentes;
	}

	public void setTodosRequerentes(DataModel<Requerente> todosRequerentes) {
		this.todosRequerentes = todosRequerentes;
	}

	public List<Estado> getTodosEstados() {
		return todosEstados;
	}

	public void setTodosEstados(List<Estado> todosEstados) {
		this.todosEstados = todosEstados;
	}

	public List<Cidade> getCidadesPorEstado() {
		return cidadesPorEstado;
	}

	public void setCidadesPorEstado(List<Cidade> cidadesPorEstado) {
		this.cidadesPorEstado = cidadesPorEstado;
	}	
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public void naMudancaEstado(){
		cidadesPorEstado = null;
		
		if( requerente.getEstado() != null )
			cidadesPorEstado = cidades.todasCidadesPorEstado(requerente.getEstado());
	}
}