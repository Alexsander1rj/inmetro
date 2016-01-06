package br.gov.inmetro.enumerator;

public enum TipoLigacao {
	NEUTRO_CENTRAL("Neutro Central"),
	TRIANGULO("Triânguo"),
	ESTRELA("Estrela");

	private String descricao;

	private TipoLigacao(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao(){
		return descricao;
	}
}