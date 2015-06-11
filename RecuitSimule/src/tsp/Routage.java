package tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import modele.Etat;


public class Routage extends Etat {

	ArrayList<Integer> route; 
	Graphe g;
	
	public Routage (Graphe g){
		this.g = g;
		this.setListe(routeInitiale());
	}
	
	public ArrayList<Integer> getListe(){
		return this.route;
	}

	public void setListe(ArrayList<Integer> liste){
		this.route = liste;
	}

	public Routage (Graphe g, ArrayList<Integer> liste){
		this.g=g;
		this.setListe(liste);
	}
	
	public ArrayList<Integer> routeInitiale() {
		int n = this.g.nombreDeNoeuds();
		ArrayList<Integer> liste = new ArrayList<Integer>();
		for (int index = 0; index < n; index++) {
			liste.add(new Integer(index));
		}
		// Réorganise aléatoirement l'ordre de visite
		Collections.shuffle(liste);
		
		return liste;
	}
	
	
	public Routage clone()
	{
		ArrayList<Integer> liste = new ArrayList<Integer>();
		for (int i = 0; i < g.nombreDeNoeuds(); i++){
			liste.add(this.route.get(i));
		}
		
		Routage clone = new Routage(this.g,liste);
		return clone;
	}

	
	public Graphe getGraphe(){
		return this.g;
	}
	
	public int getNextIndex(int index){
		if (index==(this.g.nombreDeNoeuds()-1)) {
			return 0;
		} else {
			return (index+1);
		}
	}
	public int getPreviousIndex(int index){
		if (index==0) {
			return (this.g.nombreDeNoeuds() - 1);
		} else {
			return (index-1);
		}
	}
	public String toString() {
		int n = this.g.nombreDeNoeuds();
		String s = "";
		for (int index = 0; index < n; index++){
			s += this.getListe().get(index).toString() + " ";
		}
		return s;
	}

	public double getEnergie(){
		double cpt=0.0;
		int L = this.g.nombreDeNoeuds();
		for(int i=0;i<L-1;i++){
			cpt+=this.g.longueurEntre(this.getListe().get(i),this.getListe().get(i+1));
		}
		cpt+=this.g.longueurEntre(this.getListe().get(L-1),this.getListe().get(0));
		return cpt;
	}


	public int getTaille(){
		return this.g.nombreDeNoeuds();
	}



}
