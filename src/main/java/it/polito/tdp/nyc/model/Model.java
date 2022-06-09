package it.polito.tdp.nyc.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.nyc.db.NYCDao;

public class Model {
	
	NYCDao dao;
	SimpleWeightedGraph<Quartiere,DefaultWeightedEdge> grafo;
	List<Quartiere> vertici;
	
	public Model() {
		dao= new NYCDao();
	}

	public List<String> getProviders() {
		List<String> result=this.dao.getProvider();
		Collections.sort(result);
		return result;
	}
	
	public void creaGrafo(String provider) {
		vertici= dao.getQuartieri(provider);
		grafo=new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		//Aggiungo veritic
		Graphs.addAllVertices(this.grafo, vertici);
		//aggiungo archi
		for(Quartiere q1 : vertici) {
			for(Quartiere q2 : vertici) {
				if(!q1.equals(q2)) {
					double peso=LatLngTool.distance(q1.getPosizione(), q2.getPosizione(),LengthUnit.KILOMETER);
					Graphs.addEdge(this.grafo, q1, q2, peso);
				}
			}
		}
		System.out.format("Inseriti: %d vertici, %d archi\n", grafo.vertexSet().size(), grafo.edgeSet().size());
	}
	
	public int getNumeroVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int getNumeroArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<Quartiere> getQuartieri(){
		return this.vertici;
	}
	
	public List<Quartieri_Distanza> getVicini(Quartiere selezionato){
		List<Quartieri_Distanza> result= new ArrayList<>();
		List<Quartiere> vicini= Graphs.neighborListOf(this.grafo, selezionato);
		for(Quartiere q : vicini) {
			Quartieri_Distanza qd= new Quartieri_Distanza(q.getNome(),this.grafo.getEdgeWeight(this.grafo.getEdge(selezionato, q)));
			result.add(qd);
		}
		Collections.sort(result);
		System.out.println(result+"\n");
		return result;
	}
	
	
	
	
}
