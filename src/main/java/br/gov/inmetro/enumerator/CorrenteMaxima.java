package br.gov.inmetro.enumerator;

public enum CorrenteMaxima {
	DEZ("10 A"),
	VINTE("20 A"),
	CEM("100 A"),
	CENTO_VINTE("120 A"),
	OUTRA("Outra");

	private String descricao;

	private CorrenteMaxima(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao(){
		return descricao;
	}
}