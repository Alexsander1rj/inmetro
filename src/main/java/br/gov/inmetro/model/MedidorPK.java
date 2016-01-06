package br.gov.inmetro.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class MedidorPK implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private String codSITAD;
	private int numeroSerie;	
	
	public MedidorPK() {
	}
	
	public MedidorPK(String pk) {
		String[] idComposto = pk.split(":");
		
		setCodSITAD(idComposto[0]);
		setNumeroSerie(new Integer(idComposto[1]));		
	}

	public String getCodSITAD() {
		return codSITAD;
	}
	
	public void setCodSITAD(String codSITAD) {
		this.codSITAD = codSITAD;
	}
	
	public int getNumeroSerie() {
		return numeroSerie;
	}
	
	public void setNumeroSerie(int numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codSITAD == null) ? 0 : codSITAD.hashCode());
		result = prime * result + numeroSerie;
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
		
		MedidorPK other = (MedidorPK) obj;
		
		if (codSITAD == null) {
			if (other.codSITAD != null)
				return false;
		} else if (!codSITAD.equals(other.codSITAD))
			return false;
		if (numeroSerie != other.numeroSerie)
			return false;
		
		return true;
	}
	
	@Override
	public String toString() {		
				
		return codSITAD + ":" + numeroSerie;
	}
}
