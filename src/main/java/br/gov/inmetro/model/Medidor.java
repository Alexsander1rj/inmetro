package br.gov.inmetro.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import br.gov.inmetro.enumerator.TipoMedidor;

@Entity
public class Medidor {
	@Id
	@GeneratedValue
	private Long id;
	
	private String fabricante;
	private String modelo;
	private String numeroSerie;
	
	private int frequencia;
	private int tensaoNominal;
	private int correnteNominalMax;
	private int numeroFios;

	private int anoFabricacao;	
	
	private double altura;
	private double comprimento;
	private double profundidade;
	private double dimensaoOrificio;
	
	private Date dataInclusao;
	
	private TipoMedidor tipoMedidor;
	
	@Lob
	@Column(columnDefinition="LONGBLOB")
	private byte[] foto;
	
	public TipoMedidor getTipoMedidor() {
		return tipoMedidor;
	}

	public String getFabricante() {
		return fabricante;
	}

	public String getModelo() {
		return modelo;
	}

	public int getFrequencia() {
		return frequencia;
	}

	public int getTensaoNominal() {
		return tensaoNominal;
	}

	public int getCorrenteNominalMax() {
		return correnteNominalMax;
	}

	public int getNumeroFios() {
		return numeroFios;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public int getAnoFabricacao() {
		return anoFabricacao;
	}

	public double getAltura() {
		return altura;
	}

	public double getComprimento() {
		return comprimento;
	}

	public double getProfundidade() {
		return profundidade;
	}

	public double getDimensaoOrificio() {
		return dimensaoOrificio;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setFrequencia(int frequencia) {
		this.frequencia = frequencia;
	}

	public void setTensaoNominal(int tensaoNominal) {
		this.tensaoNominal = tensaoNominal;
	}

	public void setCorrenteNominalMax(int correnteNominalMax) {
		this.correnteNominalMax = correnteNominalMax;
	}

	public void setNumeroFios(int numeroFios) {
		this.numeroFios = numeroFios;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public void setComprimento(double comprimento) {
		this.comprimento = comprimento;
	}

	public void setProfundidade(double profundidade) {
		this.profundidade = profundidade;
	}

	public void setDimensaoOrificio(double dimensaoOrificio) {
		this.dimensaoOrificio = dimensaoOrificio;
	}

	public void setDataInclusao(Date data) {

	}	

	public void setTipoMedidor(TipoMedidor tipoMedidor) {
		this.tipoMedidor = tipoMedidor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
}