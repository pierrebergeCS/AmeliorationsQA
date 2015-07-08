package LHDVectors;

import modele.Element;

public class Vecteur extends Element {
	int[] coord;
	
	public Vecteur(int[] coord){
		this.coord = coord;
	}
	
	public int[] getCoord(){
		return this.coord;
	}
	
	public int getDimension(){
		return this.coord.length;
	}
	
	@Override
	public boolean equals(Element elt) {
		Vecteur autre = (Vecteur) elt;
		//On regarde si les coordonn�es du vecteur sont identiques aux coordonn�es de l'autre
		int i =0;
		while( (this.coord[i]==autre.coord[i]) && (i<this.coord.length) ){
			i++;
		}
		return (i==this.coord.length);
	}
	
	public String toString(){
		String s = "(";
		for (int i = 0; i < this.getDimension()-1; i++){
			s += this.coord[i] + ",";
		}
		s += this.coord[this.getDimension()-1] + ")";
		return s;
	}
	
	public Vecteur clone(){
		//On recopie les anciennes coordonn�es dans un nouveau tableau
		int[] tab = new int[this.getDimension()];
		for (int i = 0; i < this.getDimension(); i++){
			tab[i] = this.getCoord()[i];
		}
		//A partir de ce tableau, on cr�e un nouveau vecteur
		return new Vecteur(tab);
	}


}
