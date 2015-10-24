package br.gov.inmetro.enumerator;

public enum TipoInstalacao {
	SOBREPOR("Sobrepor"),
	EMBUTIR_PAINEL("Embutir em painel"),
	SOCKET("Socket"),
	OUTRO("Outro");

	private String descricao;

	private TipoInstalacao(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao(){
		return descricao;
	}
}