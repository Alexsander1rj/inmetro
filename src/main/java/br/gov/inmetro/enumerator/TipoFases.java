package br.gov.inmetro.enumerator;

public enum TipoFases {
	MONOFASICO("Monofásico"),
	POLIFASICO("Polifásico");

	private String descricao;

	private TipoFases(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao(){
		return descricao;
	}
}