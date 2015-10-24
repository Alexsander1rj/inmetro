package br.gov.inmetro.enumerator;

public enum TensaoNominal {
	CENTO_VINTE("120 V"),
	DUZENTOS_QUARENTA("240 V"),
	CENTO_VINTE_DUZENTOS_QUARENTA("120 V/240 V"),
	OUTRA("Outra");

	private String descricao;

	private TensaoNominal(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao(){
		return descricao;
	}
}