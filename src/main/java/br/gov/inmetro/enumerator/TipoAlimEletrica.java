package br.gov.inmetro.enumerator;

public enum TipoAlimEletrica {
	CORRENTE_CONTINUA("Corrente Contínua"),
	CORRENTE_ALTERNADA("Corrente Alternada"),
	FONTE_EXTERNA("Fonte Externa"),
	BATERIA_PROPRIA("Bateria Própria");

	private String descricao;

	private TipoAlimEletrica(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao(){
		return descricao;
	}
}