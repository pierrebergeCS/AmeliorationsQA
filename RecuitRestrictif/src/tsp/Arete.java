package tsp;

import modele.Element;

public class Arete extends Element{
	
	int noeud1;
	int noeud2;
	
	public Arete(int n1, int n2){
		this.noeud1 = n1;
		this.noeud2 = n2;
	}
	
	public int getNoeud1(){
		return this.noeud1;
	}
	
	public int getNoeud2(){
		return this.noeud2;
	}
	
	public void setNoeud1(int n1){
		this.noeud1 = n1;
	}
	
	public void setNoeud2(int n2){
		this.noeud1 = n2;
	}
	
	
	
	public boolean equals(Element autre){
		Arete autreArete = (Arete) autre;
		return ((this.getNoeud1()==autreArete.getNoeud1()) && (this.getNoeud2()==autreArete.getNoeud2()));
	}
	
	public Arete clone(){
		return new Arete(this.noeud1,this.noeud2);
	}
	
	public String toString(){
		return this.noeud1 + " -> " + this.noeud2;
	}
	
	public double longueur(Graphe g){
		if (this.noeud1 < this.noeud2) return g.getdists()[this.noeud1][this.noeud2];
		return g.getdists()[this.noeud2][this.noeud1];
	}
	
	public Arete reverse(){
		int temp1 = this.getNoeud1();
		int temp2 = this.getNoeud2();
		return new Arete(temp2,temp1);
	}
}
