package br.gov.inmetro.enumerator;

public enum ConfigEletricaMonofasico {
	DOIS_FIOS("2 Fios"),
	TRES_FIOS("3 Fios");

	private String descricao;

	private ConfigEletricaMonofasico(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao(){
		return descricao;
	}
}