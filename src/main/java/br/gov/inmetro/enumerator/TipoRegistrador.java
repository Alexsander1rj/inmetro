package br.gov.inmetro.enumerator;

public enum TipoRegistrador {
	UNIDIRECIONAL("UNIDIRECIONAL"),
	BIDIRECIONAL("BIDIRECIONAL"),
	COM_CATRACA("COM CATRACA");

	private String descricao;

	private TipoRegistrador(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao(){
		return descricao;
	}
}