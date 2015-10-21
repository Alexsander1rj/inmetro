package br.gov.inmetro.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;

import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class RelatorioUtil {
	public static final int RELATORIO_PDF = 1;

	public StreamedContent geraRelatorio(HashMap parametrosRelatorio,
			String nomeRelatorioJasper, String nomeRelatorioSaida, int tipoRelatorio) {
		
		StreamedContent arquivoRetorno = null;

		try {
			FacesContext context = FacesContext.getCurrentInstance();
			Connection conexao = getConexao();
			String caminhoRelatorio = context.getExternalContext().getRealPath("relatorios");
			String caminhoArquivoJasper = caminhoRelatorio + File.separator + nomeRelatorioJasper + ".jasper";
			String caminhoArquivoRelatorio = null;

			JasperReport relatorioJasper = (JasperReport) JRLoader.loadObject(getClass().getResource(caminhoArquivoJasper));
			JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio, conexao);
			
			JRExporter tipoArquivoExportado = new JRPdfExporter();
			String extensaoArquivoExportado = "pdf";
			
			caminhoArquivoRelatorio = caminhoRelatorio + File.separator + nomeRelatorioSaida + "." + extensaoArquivoExportado;
			
			File arquivoGerado = new File(caminhoArquivoRelatorio);
			
			tipoArquivoExportado.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
			tipoArquivoExportado.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
			tipoArquivoExportado.exportReport();
			
			arquivoGerado.deleteOnExit();
			
			InputStream conteudoRelatorio = new FileInputStream(arquivoGerado);
			
			arquivoRetorno = new DefaultStreamedContent(conteudoRelatorio, "application/" + extensaoArquivoExportado, nomeRelatorioSaida +"." + extensaoArquivoExportado);		 
			
		} catch (JRException e) {
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return arquivoRetorno;
	}

	private Connection getConexao() {
		Connection conexao = null;

		return conexao;
	}
}
