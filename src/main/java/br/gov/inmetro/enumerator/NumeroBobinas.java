package br.gov.inmetro.enumerator;

public enum NumeroBobinas {
	DOIS_BOBINAS("2 Bobinas"),
	TRES_BOBINAS("3 Bobinas");

	private String descricao;

	private NumeroBobinas(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao(){
		return descricao;
	}
}