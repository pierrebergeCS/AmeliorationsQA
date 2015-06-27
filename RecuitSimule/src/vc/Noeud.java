package vc;

public class Noeud {
	int index = -1;
	int colorIndex; //Inutile de retenir toute la couleur, un indicateur entier suffit
	
	public Noeud(int index){
		this.index = index;
	}
	
	public Noeud(int index, int c){
		this.index = index;
		this.colorIndex = c;
	}
	
	public int getInt(){
		return this.index;
	}
	
	public int getCouleur(){
		return this.colorIndex;
	}
	
	public void setCouleur(int index){
		this.colorIndex = index;
	}
	
	public Noeud clone(){
		return new Noeud(this.index,this.colorIndex);
	}

}
