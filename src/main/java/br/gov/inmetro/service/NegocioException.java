package br.gov.inmetro.service;

public class NegocioException extends Exception {
	private static final long serialVersionUID = 1719546889415406161L;
	
	public NegocioException(String message) {
		super(message);
	}
}
