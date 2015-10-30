package br.gov.inmetro.relatorio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class RelatorioUtil {
	public static byte[] imprimir(String sourceFileName, HashMap<String, Object> parameters, List<?> dataSource) {
		byte[] bytes = null;

		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(sourceFileName, parameters,
					new JRBeanCollectionDataSource(dataSource));

			bytes = exportaArquivoPDF(jasperPrint);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return bytes;
	}

	private static byte[] exportaArquivoPDF(JasperPrint jasperPrint) throws JRException, IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		JRPdfExporter exporter = new JRPdfExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

		exporter.exportReport();

		byte[] bytes = baos.toByteArray();

		return bytes;
	}
}