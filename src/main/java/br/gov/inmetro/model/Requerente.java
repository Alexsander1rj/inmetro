package br.gov.inmetro.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Requerente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String tipo;
	private String razaoNome;
	private String CNPJ;
	private String endereco;
	private String bairroDistrito;
	private String cep;
	private String pais;
	private String tel;
	private String telOpcional;
	private String telOutro;
	private String email;
	private Date dataInclusao;
	@OneToOne
	private Estado estado;
	@OneToOne
	private Cidade cidade;
	
	@OneToMany(mappedBy="requerente")
	private List<Medidor> medidores;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getRazaoNome() {
		return razaoNome;
	}
	
	public void setRazaoNome(String razaoNome) {
		this.razaoNome = razaoNome;
	}
	
	public String getCNPJ() {
		return CNPJ;
	}
	
	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getBairroDistrito() {
		return bairroDistrito;
	}
	
	public void setBairroDistrito(String bairroDistrito) {
		this.bairroDistrito = bairroDistrito;
	}
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getPais() {
		return pais;
	}
	
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getTelOpcional() {
		return telOpcional;
	}
	
	public void setTelOpcional(String telOpcional) {
		this.telOpcional = telOpcional;
	}
	
	public String getTelOutro() {
		return telOutro;
	}
	
	public void setTelOutro(String telOutro) {
		this.telOutro = telOutro;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getDataInclusao() {
		return dataInclusao;
	}
	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
	
	public Estado getEstado() {
		return estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public Cidade getCidade() {
		return cidade;
	}
	
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Requerente other = (Requerente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}