package br.gov.inmetro.enumerator;

public enum NumeroFios {
	DOIS_FIOS("2 Fios"),
	TRES_FIOS("3 Fios"),
	QUATRO_FIOS("4 Fios"),
	CINCO_FIOS("5 Fios");

	private String descricao;

	private NumeroFios(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao(){
		return descricao;
	}
}