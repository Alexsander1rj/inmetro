package br.gov.inmetro.enumerator;

public enum DefinicaoSolicitacao {
	APROVACAO("Aprovação"),
	MODIFICACAO("Modificação");

	private String descricao;

	private DefinicaoSolicitacao(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao(){
		return descricao;
	}
}