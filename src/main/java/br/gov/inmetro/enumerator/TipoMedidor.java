package br.gov.inmetro.enumerator;

public enum TipoMedidor {
	ELETROMECANICO("Eletromecânico"),
	ELETONICO("Eletrônico"),
	MULTIPLATARIFACAO("Múltipla Tarifação");

	private String descricao;

	private TipoMedidor(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao(){
		return descricao;
	}
}