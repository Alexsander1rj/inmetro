package br.gov.inmetro.enumerator;

public enum ConfigEletricaPolifasico {
	DOIS_ELE_3FIOS("2 Elementos/3 Fios"),
	TRES_ELE_4FIOS("3 Elementos/4 Fios"),
	OUTRA("Outra");
	
	private String descricao;

	private ConfigEletricaPolifasico(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao(){
		return descricao;
	}
}