package br.gov.inmetro.enumerator;

public enum TipoMedicao {
	DIRETA("Direta"),
	INDIRETA("Indireta");

	private String descricao;

	private TipoMedicao(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao(){
		return descricao;
	}
}