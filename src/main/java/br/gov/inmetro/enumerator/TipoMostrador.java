package br.gov.inmetro.enumerator;

public enum TipoMostrador {
	LCD("LCD"),
	CICLOMETRICO("Ciclométrico"),
	OUTRO("Outro");

	private String descricao;

	private TipoMostrador(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao(){
		return descricao;
	}
}