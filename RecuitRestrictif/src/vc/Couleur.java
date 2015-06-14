package vc;

import java.util.ArrayList;

import modele.Element;

public class Couleur extends Element {
	ArrayList<Integer> noeuds;
	
	public Couleur(ArrayList<Integer> noeuds){
		this.noeuds = noeuds;
	}

	public ArrayList<Integer> getNoeuds(){
		return this.noeuds;
	}
	
	public int getTaille(){
		return this.noeuds.size();
	}
	
	@Override
	public boolean equals(Element autre) {
		Couleur autrecouleur = (Couleur) autre;
		boolean b = false;
		if (autrecouleur.getTaille()==this.getTaille()){
			boolean b2 = true;
			int k = 0;
			while(k < this.getTaille() && b2){
				b2 = this.getNoeuds().get(k)==autrecouleur.getNoeuds().get(k);
				k++;
			}
			b = b2;
		}
		return b;
	}

}
