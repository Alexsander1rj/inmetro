package br.gov.inmetro.enumerator;

public enum ConfiguracaoEletrica {
	DOIS_FIOS("2 Fios"),
	TRES_FIOS("3 Fios"),
	QUATRO_FIOS("4 Fios"),
	CINCO_FIOS("5 Fios"),
	DOIS_ELE("2 Elementos"),
	TRES_ELE("2 Elementos"),
	DOIS_BOBINAS("2 Bobinas"),
	TRES_BOBINAS("3 Bobinas"),
	QUATRO_BOBINAS("4 Bobinas"),
	OUTRA("Outra");

	private String descricao;

	private ConfiguracaoEletrica(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao(){
		return descricao;
	}
}