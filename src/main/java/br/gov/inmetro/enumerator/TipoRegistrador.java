package br.gov.inmetro.enumerator;

public enum TipoRegistrador {
	UNIDIRECIONAL("Unidirecional"),
	BIDIRECIONAL("Bidirecional"),
	COM_CATRACA("Com catraca");

	private String descricao;

	private TipoRegistrador(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao(){
		return descricao;
	}
}