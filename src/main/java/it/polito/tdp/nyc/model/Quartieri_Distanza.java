package it.polito.tdp.nyc.model;

public class Quartieri_Distanza implements Comparable<Quartieri_Distanza> {

	private String nome;
	private Double distanza;
	public Quartieri_Distanza(String nome, Double distanza) {
		super();
		this.nome = nome;
		this.distanza = distanza;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getDistanza() {
		return distanza;
	}
	public void setDistanza(Double distanza) {
		this.distanza = distanza;
	}
	@Override
	public int compareTo(Quartieri_Distanza o) {
		return this.getDistanza().compareTo(o.getDistanza());
	}
	@Override
	public String toString() {
		return "Quartieri_Distanza [nome=" + nome + ", distanza=" + distanza + "]";
	}
	
	
	
}
