package br.gov.inmetro.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import br.gov.inmetro.enumerator.ClasseExatidao;
import br.gov.inmetro.enumerator.ConfiguracaoEletrica;
import br.gov.inmetro.enumerator.CorrenteMaxima;
import br.gov.inmetro.enumerator.CorrenteNominal;
import br.gov.inmetro.enumerator.DefinicaoSolicitacao;
import br.gov.inmetro.enumerator.GrandezaMedida;
import br.gov.inmetro.enumerator.InterfaceComunicacao;
import br.gov.inmetro.enumerator.SentidosFluxo;
import br.gov.inmetro.enumerator.TensaoNominal;
import br.gov.inmetro.enumerator.TipoAlimEletrica;
import br.gov.inmetro.enumerator.TipoFases;
import br.gov.inmetro.enumerator.TipoFrequencia;
import br.gov.inmetro.enumerator.TipoInstalacao;
import br.gov.inmetro.enumerator.TipoMedidor;
import br.gov.inmetro.enumerator.TipoMostrador;
import br.gov.inmetro.enumerator.TipoRegistrador;

@Entity
public class Medidor implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private MedidorPK id = new MedidorPK();
	
	@ManyToOne
	@JoinColumn(name="id_requerente")
	private Requerente requerente;
	private String fabricante;
	private String marca;
	@ManyToOne
	private Pais pais;
	private String modelo;
	private String instrumento;
	
	@Enumerated(EnumType.STRING)
	private DefinicaoSolicitacao definicaoSolicitacao;
	@Enumerated(EnumType.STRING)
	private ClasseExatidao classeExatidao;

	@ElementCollection
	@Enumerated(EnumType.STRING)
	private List<GrandezaMedida> grandezaMedida;
	@Enumerated(EnumType.STRING)
	private TipoInstalacao tipoInstalacao;
	@Enumerated(EnumType.STRING)
	private TipoFases tipoFase;
	@Enumerated(EnumType.STRING)
	private ConfiguracaoEletrica configuracaoEletrica;
	@Enumerated(EnumType.STRING)
	private SentidosFluxo sentidosFluxo;
	@Enumerated(EnumType.STRING)
	private TensaoNominal tensaoNominal;
	@Enumerated(EnumType.STRING)
	private TipoAlimEletrica tipoAlimEletrica;
	@Enumerated(EnumType.STRING)
	private CorrenteNominal correnteNominal;
	@Enumerated(EnumType.STRING)
	private CorrenteMaxima correnteMaxima;
	@Enumerated(EnumType.STRING)
	private TipoMostrador tipoMostrador;
	@Enumerated(EnumType.STRING)
	private TipoRegistrador tipoRegistrador;
	@Enumerated(EnumType.STRING)
	private TipoFrequencia tipoFrequencia;
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private List<InterfaceComunicacao> interfaceComunicacao;
		
	private boolean medidorMultitensao;
	private boolean dispositivosComplementares;
	
	private String obsDefSolicitacao;
	private String obsTipoInstalacao;
	private String obsConfigEletrica;
	private String obsTensaoNominal;
	private String obsCorrenteNominal;
	private String obsCorrenteMaxima;
	private String obsTipoMosrador;
	private String obsIntfComunicacao;
	private String obsDispComplementares;
	private String obsDescricaoGeral;
	private String obsInfAdicionais;
	
	private double constCalibracaoWh;
	private double constCalibracaovarh;
	private double constEletronicaWh;
	private double constEletronicavarh;
	private double faixaTensaoInicial;
	private double faixaTensaoFinal;

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

	public Requerente getRequerente() {
		return requerente;
	}

	public void setRequerente(Requerente requerente) {
		this.requerente = requerente;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getInstrumento() {
		return instrumento;
	}

	public void setInstrumento(String instrumento) {
		this.instrumento = instrumento;
	}

	public DefinicaoSolicitacao getDefinicaoSolicitacao() {
		return definicaoSolicitacao;
	}

	public void setDefinicaoSolicitacao(DefinicaoSolicitacao definicaoSolicitacao) {
		this.definicaoSolicitacao = definicaoSolicitacao;
	}

	public ClasseExatidao getClasseExatidao() {
		return classeExatidao;
	}

	public void setClasseExatidao(ClasseExatidao classeExatidao) {
		this.classeExatidao = classeExatidao;
	}

	public TipoInstalacao getTipoInstalacao() {
		return tipoInstalacao;
	}

	public void setTipoInstalacao(TipoInstalacao tipoInstalacao) {
		this.tipoInstalacao = tipoInstalacao;
	}

	public ConfiguracaoEletrica getConfiguracaoEletrica() {
		return configuracaoEletrica;
	}

	public void setConfiguracaoEletrica(ConfiguracaoEletrica configuracaoEletrica) {
		this.configuracaoEletrica = configuracaoEletrica;
	}

	public SentidosFluxo getSentidosFluxo() {
		return sentidosFluxo;
	}

	public void setSentidosFluxo(SentidosFluxo sentidosFluxo) {
		this.sentidosFluxo = sentidosFluxo;
	}

	public TensaoNominal getTensaoNominal() {
		return tensaoNominal;
	}

	public void setTensaoNominal(TensaoNominal tensaoNominal) {
		this.tensaoNominal = tensaoNominal;
	}

	public TipoAlimEletrica getTipoAlimEletrica() {
		return tipoAlimEletrica;
	}

	public void setTipoAlimEletrica(TipoAlimEletrica tipoAlimEletrica) {
		this.tipoAlimEletrica = tipoAlimEletrica;
	}

	public CorrenteNominal getCorrenteNominal() {
		return correnteNominal;
	}

	public void setCorrenteNominal(CorrenteNominal correnteNominal) {
		this.correnteNominal = correnteNominal;
	}

	public CorrenteMaxima getCorrenteMaxima() {
		return correnteMaxima;
	}

	public void setCorrenteMaxima(CorrenteMaxima correnteMaxima) {
		this.correnteMaxima = correnteMaxima;
	}

	public TipoMostrador getTipoMostrador() {
		return tipoMostrador;
	}

	public void setTipoMostrador(TipoMostrador tipoMostrador) {
		this.tipoMostrador = tipoMostrador;
	}

	public TipoRegistrador getTipoRegistrador() {
		return tipoRegistrador;
	}

	public void setTipoRegistrador(TipoRegistrador tipoRegistrador) {
		this.tipoRegistrador = tipoRegistrador;
	}

	public TipoFrequencia getTipoFrequencia() {
		return tipoFrequencia;
	}

	public void setTipoFrequencia(TipoFrequencia tipoFrequencia) {
		this.tipoFrequencia = tipoFrequencia;
	}

	public boolean isMedidorMultitensao() {
		return medidorMultitensao;
	}

	public void setMedidorMultitensao(boolean medidorMultitensao) {
		this.medidorMultitensao = medidorMultitensao;
	}

	public boolean isDispositivosComplementares() {
		return dispositivosComplementares;
	}

	public void setDispositivosComplementares(boolean dispositivosComplementares) {
		this.dispositivosComplementares = dispositivosComplementares;
	}

	public String getObsDefSolicitacao() {
		return obsDefSolicitacao;
	}

	public void setObsDefSolicitacao(String obsDefSolicitacao) {
		this.obsDefSolicitacao = obsDefSolicitacao;
	}

	public String getObsTipoInstalacao() {
		return obsTipoInstalacao;
	}

	public void setObsTipoInstalacao(String obsTipoInstalacao) {
		this.obsTipoInstalacao = obsTipoInstalacao;
	}

	public String getObsConfigEletrica() {
		return obsConfigEletrica;
	}

	public void setObsConfigEletrica(String obsConfigEletrica) {
		this.obsConfigEletrica = obsConfigEletrica;
	}

	public String getObsTensaoNominal() {
		return obsTensaoNominal;
	}

	public void setObsTensaoNominal(String obsTensaoNominal) {
		this.obsTensaoNominal = obsTensaoNominal;
	}

	public String getObsCorrenteNominal() {
		return obsCorrenteNominal;
	}

	public void setObsCorrenteNominal(String obsCorrenteNominal) {
		this.obsCorrenteNominal = obsCorrenteNominal;
	}

	public String getObsCorrenteMaxima() {
		return obsCorrenteMaxima;
	}

	public void setObsCorrenteMaxima(String obsCorrenteMaxima) {
		this.obsCorrenteMaxima = obsCorrenteMaxima;
	}

	public String getObsTipoMosrador() {
		return obsTipoMosrador;
	}

	public void setObsTipoMosrador(String obsTipoMosrador) {
		this.obsTipoMosrador = obsTipoMosrador;
	}

	public String getObsIntfComunicacao() {
		return obsIntfComunicacao;
	}

	public void setObsIntfComunicacao(String obsIntfComunicacao) {
		this.obsIntfComunicacao = obsIntfComunicacao;
	}

	public String getObsDispComplementares() {
		return obsDispComplementares;
	}

	public void setObsDispComplementares(String obsDispComplementares) {
		this.obsDispComplementares = obsDispComplementares;
	}

	public String getObsDescricaoGeral() {
		return obsDescricaoGeral;
	}

	public void setObsDescricaoGeral(String obsDescricaoGeral) {
		this.obsDescricaoGeral = obsDescricaoGeral;
	}

	public String getObsInfAdicionais() {
		return obsInfAdicionais;
	}

	public void setObsInfAdicionais(String obsInfAdicionais) {
		this.obsInfAdicionais = obsInfAdicionais;
	}

	public double getConstCalibracaoWh() {
		return constCalibracaoWh;
	}

	public void setConstCalibracaoWh(double constCalibracaoWh) {
		this.constCalibracaoWh = constCalibracaoWh;
	}

	public double getConstCalibracaovarh() {
		return constCalibracaovarh;
	}

	public void setConstCalibracaovarh(double constCalibracaovarh) {
		this.constCalibracaovarh = constCalibracaovarh;
	}

	public double getConstEletronicaWh() {
		return constEletronicaWh;
	}

	public void setConstEletronicaWh(double constEletronicaWh) {
		this.constEletronicaWh = constEletronicaWh;
	}

	public double getConstEletronicavarh() {
		return constEletronicavarh;
	}

	public void setConstEletronicavarh(double constEletronicavarh) {
		this.constEletronicavarh = constEletronicavarh;
	}

	public double getFaixaTensaoInicial() {
		return faixaTensaoInicial;
	}

	public void setFaixaTensaoInicial(double faixaTensaoInicial) {
		this.faixaTensaoInicial = faixaTensaoInicial;
	}

	public double getFaixaTensaoFinal() {
		return faixaTensaoFinal;
	}

	public void setFaixaTensaoFinal(double faixaTensaoFinal) {
		this.faixaTensaoFinal = faixaTensaoFinal;
	}

	public int getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getComprimento() {
		return comprimento;
	}

	public void setComprimento(double comprimento) {
		this.comprimento = comprimento;
	}

	public double getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(double profundidade) {
		this.profundidade = profundidade;
	}

	public double getDimensaoOrificio() {
		return dimensaoOrificio;
	}

	public void setDimensaoOrificio(double dimensaoOrificio) {
		this.dimensaoOrificio = dimensaoOrificio;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public TipoMedidor getTipoMedidor() {
		return tipoMedidor;
	}

	public void setTipoMedidor(TipoMedidor tipoMedidor) {
		this.tipoMedidor = tipoMedidor;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public MedidorPK getId() {
		return id;
	}

	public void setId(MedidorPK id) {
		this.id = id;
	}

	public List<GrandezaMedida> getGrandezaMedida() {
		return grandezaMedida;
	}

	public void setGrandezaMedida(List<GrandezaMedida> grandezaMedida) {
		this.grandezaMedida = grandezaMedida;
	}

	public List<InterfaceComunicacao> getInterfaceComunicacao() {
		return interfaceComunicacao;
	}

	public void setInterfaceComunicacao(List<InterfaceComunicacao> interfaceComunicacao) {
		this.interfaceComunicacao = interfaceComunicacao;
	}

	public TipoFases getTipoFase() {
		return tipoFase;
	}

	public void setTipoFase(TipoFases tipoFase) {
		this.tipoFase = tipoFase;
	}
}