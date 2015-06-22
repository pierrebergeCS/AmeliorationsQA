package vc;

import java.util.ArrayList;

import modele.Element;

public class Couleur extends Element {
	int index;
	ArrayList<Integer> noeuds;// Indices des noeuds concernés par la couleur
	
	public Couleur(ArrayList<Integer> noeuds){
		this.noeuds = noeuds;
	}
	
	public Couleur(int index, ArrayList<Integer> noeuds){
		this.index = index;
		this.noeuds = noeuds;
	}
	
	public Couleur(int index){
		this.index = index;
		this.noeuds = new ArrayList<Integer>();
	}

	public ArrayList<Integer> getNoeuds(){
		return this.noeuds;
	}
	
	public int getTaille(){
		return this.noeuds.size();
	}
	
	//Ajoute un noeud à la couleur
	public void addNode(Integer k){
		this.noeuds.add(k);
	}
	
	//Retire un noeud de la couleur
	public void removeNode(Integer k){
		this.noeuds.remove(k);
	}
	
	@Override
	public boolean equals(Element autre) {
		Couleur autrecouleur = (Couleur) autre;
		boolean b = false;
		if (autrecouleur.getTaille()==this.getTaille()){
			boolean b2 = true;
			int k = 0;
			while(k < this.getTaille() && b2){
				b2 = autrecouleur.getNoeuds().indexOf(this.getNoeuds().get(k))!=-1;
				k++;
			}
			b = b2;
		}
		return b;
	}
	
	public Couleur clone(){
		ArrayList<Integer> liste = new ArrayList<Integer>();
		int n = this.noeuds.size();
		for (int i = 0; i < n; i++){
			liste.add(this.noeuds.get(i));
		}
		return new Couleur(this.index,liste);
	}

}
