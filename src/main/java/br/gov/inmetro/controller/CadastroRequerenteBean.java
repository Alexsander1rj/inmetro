package br.gov.inmetro.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import br.gov.inmetro.model.Cidade;
import br.gov.inmetro.model.Estado;
import br.gov.inmetro.model.Requerente;
import br.gov.inmetro.repository.CidadeRepository;
import br.gov.inmetro.repository.EstadoRepository;
import br.gov.inmetro.repository.RequerenteRepository;
import br.gov.inmetro.service.CadastroRequerente;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

@Named(value="cadastroRequerenteBean")
@ViewScoped
public class CadastroRequerenteBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Requerente requerente = new Requerente();
	private DataModel<Requerente> todosRequerentes;
	private List<Estado> todosEstados;
	private List<Cidade> cidadesPorEstado;
	private Estado estado;

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

		for (int i = 0; i < this.todosRequerentes.getRowCount(); i++) {
			this.todosRequerentes.setRowIndex(i);
			listaRequerentes.add((Requerente) this.todosRequerentes
					.getRowData());
		}

		try {
			// requerenteRel.imprimir(listaRequerentes, context);

			context.responseComplete();

			HashMap<String, Object> parameters = new HashMap<String, Object>();

			ServletContext scontext = (ServletContext) context
					.getExternalContext().getContext();

			JasperPrint jasperPrint = JasperFillManager.fillReport(scontext
					.getRealPath("/WEB-INF/classes/jasper/requerentes.jasper"),
					parameters,
					new JRBeanCollectionDataSource(listaRequerentes));

			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			JRPdfExporter exporter = new JRPdfExporter();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

			exporter.exportReport();

			byte[] bytes = baos.toByteArray();

			if (bytes != null && bytes.length > 0) {

				HttpServletResponse response = (HttpServletResponse) context
						.getExternalContext().getResponse();

				response.setContentType("application/pdf");

				response.setHeader("Content-disposition",
						"inline; filename=\"Relatorio_de_requerentes.pdf\"");

				response.setContentLength(bytes.length);

				ServletOutputStream outputStream = response.getOutputStream();

				outputStream.write(bytes, 0, bytes.length);

				outputStream.flush();

				outputStream.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
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