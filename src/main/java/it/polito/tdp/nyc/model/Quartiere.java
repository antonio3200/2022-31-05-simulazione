package it.polito.tdp.nyc.model;

import com.javadocmd.simplelatlng.LatLng;

public class Quartiere {
	
	private String nome;
 	private LatLng posizione;
 	private int numeroHotspot;
	public Quartiere(String nome, LatLng posizione, int numeroHotspot) {
		super();
		this.nome = nome;
		this.posizione = posizione;
		this.numeroHotspot = numeroHotspot;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LatLng getPosizione() {
		return posizione;
	}
	public void setPosizione(LatLng posizione) {
		this.posizione = posizione;
	}
	public int getNumeroHotspot() {
		return numeroHotspot;
	}
	public void setNumeroHotspot(int numeroHotspot) {
		this.numeroHotspot = numeroHotspot;
	}
	@Override
	public String toString() {
		return nome;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Quartiere other = (Quartiere) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
 	
 	
 	

}
