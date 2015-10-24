package br.gov.inmetro.enumerator;

public enum TipoFrequencia {
	CINQUENTA("50 Hz"),
	SESSENTA("60 Hz");

	private String descricao;

	private TipoFrequencia(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao(){
		return descricao;
	}
}