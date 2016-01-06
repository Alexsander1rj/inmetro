package br.gov.inmetro.enumerator;

public enum NumeroElementos {
	DOIS_ELE("2 Elementos"),
	TRES_ELE("2 Elementos");

	private String descricao;

	private NumeroElementos(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao(){
		return descricao;
	}
}