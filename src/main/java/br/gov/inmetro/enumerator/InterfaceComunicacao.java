package br.gov.inmetro.enumerator;

public enum InterfaceComunicacao {
	SAIDA_PULSO("50 Hz"),
	SAIDA_USUARIO("60 Hz"),
	RS485("RS485"),
	RS232("RS232"),
	OUTRO("Outro");

	private String descricao;

	private InterfaceComunicacao(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao(){
		return descricao;
	}
}