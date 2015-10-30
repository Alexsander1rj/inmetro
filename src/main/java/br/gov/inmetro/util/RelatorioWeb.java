package br.gov.inmetro.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import br.gov.inmetro.relatorio.RelatorioUtil;

public class RelatorioWeb {
	private FacesContext context;
	private String sourceFileName;
	private HashMap<String, Object> parameters;
	private List<?> dataSource;
	
	public RelatorioWeb(FacesContext context, String sourceFileName, HashMap<String, Object> parameters, List<?> dataSource) {
		this.context = context;
		this.sourceFileName = sourceFileName;
		this.parameters = parameters;
		this.dataSource = dataSource;
	}
	
	public void exportaArquivo(){
		byte[] bytes = RelatorioUtil.imprimir(sourceFileName, parameters, dataSource);
		
		if (bytes != null && bytes.length > 0) {

			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

			response.setContentType("application/pdf");

			response.setHeader("Content-disposition", "inline; filename=\"Relatorio_de_requerentes.pdf\"");

			response.setContentLength(bytes.length);

			try {

				ServletOutputStream outputStream = response.getOutputStream();

				outputStream.write(bytes, 0, bytes.length);

				outputStream.flush();

				outputStream.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}	
}
