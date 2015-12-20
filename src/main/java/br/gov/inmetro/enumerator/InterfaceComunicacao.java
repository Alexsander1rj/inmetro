package br.gov.inmetro.enumerator;

public enum InterfaceComunicacao {
	SAIDA_PULSO("Saída de Pulso"),
	SAIDA_USUARIO("Saída de Usuário"),
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