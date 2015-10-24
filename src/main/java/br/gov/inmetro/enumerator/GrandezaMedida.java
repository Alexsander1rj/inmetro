package br.gov.inmetro.enumerator;

public enum GrandezaMedida {
	ENERGIA_ATIVA("Energia Ativa"),
	ENERGIA_REATIVA("Energia Reativa");

	private String descricao;

	private GrandezaMedida(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao(){
		return descricao;
	}
}