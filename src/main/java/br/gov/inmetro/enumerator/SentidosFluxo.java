package br.gov.inmetro.enumerator;

public enum SentidosFluxo {
	UNIDIRECIONAL("Unidirecional"),
	BIDIRECIONAL("Bidirecional");

	private String descricao;

	private SentidosFluxo(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao(){
		return descricao;
	}
}