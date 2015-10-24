package br.gov.inmetro.enumerator;

public enum CorrenteNominal {
	DOIS_CINCO("2,5 A"),
	CINCO("5 A"),
	QUINZE("15 A"),
	TRINTA("30 A"),
	OUTRA("Outra");

	private String descricao;

	private CorrenteNominal(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao(){
		return descricao;
	}
}