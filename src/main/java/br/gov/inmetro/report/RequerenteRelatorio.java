package br.gov.inmetro.report;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import br.gov.inmetro.model.Requerente;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class RequerenteRelatorio {
	private String path; // Caminho base

	private String pathToReportPackage; // Caminho para o package onde est�o
										// armazenados os relatorios Jarper

	// Recupera os caminhos para que a classe possa encontrar os relat�rios
	public RequerenteRelatorio() {
		FacesContext context = FacesContext.getCurrentInstance();

		this.path = context.getExternalContext()
				.getRealPath("/WEB-INF/classes");
		this.pathToReportPackage = this.path + "/jasper/";
	}

	// Imprime/gera uma lista de Clientes
	public void imprimir(List<Requerente> requerente, FacesContext context)
			throws Exception {
		JasperReport report = JasperCompileManager.compileReport(this.getPathToReportPackage() + "requerentes.jrxml");

		JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(requerente));

		JasperExportManager.exportReportToPdfFile(print, "d:/Relatorio_de_requerentes.pdf");

		context.responseComplete();
		
		HashMap<String, Object> parameters = new HashMap<String, Object>();

		ServletContext scontext = (ServletContext) context.getExternalContext().getContext();

		JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/classes/jasper/requerentes.jasper"), parameters, new JRBeanCollectionDataSource(requerente));

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		JRPdfExporter exporter = new JRPdfExporter();
		
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));

		exporter.exportReport();

		byte[] bytes = baos.toByteArray();

		if (bytes != null && bytes.length > 0) {

			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "attachment; filename=\"Relatorio_de_requerentes.pdf\"");
			response.setContentLength(bytes.length);

			ServletOutputStream outputStream = response.getOutputStream();

			outputStream.write(bytes, 0, bytes.length);
			outputStream.flush();
			outputStream.close();
		}
	}

	public String getPathToReportPackage() {
		return this.pathToReportPackage;
	}

	public String getPath() {
		return this.path;
	}
}
