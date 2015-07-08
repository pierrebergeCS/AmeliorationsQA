package LHD_1fixed;

import modele.Element;

public class Croix extends Element{
	public int[] coord; //coordonn�es de la croix
	// la taille du tableau est la dimension du probl�me
	boolean estFixee;
	
	public Croix(int[] coord){
		this.coord = coord;
	}
	
	public Croix(int[] coord, boolean b){
		this.coord = coord;
		this.estFixee = b;
	}
	
	public int[] getCoord(){
		return this.coord;
	}
	
	public boolean isFixed(){
		return this.estFixee;
	}
	
	//Permet de cloner une croix
	public Croix clone(){
		int[] tab = new int[this.getDimension()];
		//On va chercher chaque coordonn�e de la croix courante
		for (int i = 0; i < this.getDimension(); i++){
			//On la copie dans un nouveau tableau
			tab[i] = this.getCoord()[i];
		}
		//On cr�e la nouvelle croix � partir de ce tableau
		return new Croix(tab,this.estFixee);
	}
	
	//Repr�sente une croix sous forme de cha�ne
	public String toString(){
		//Repr�sentation sous forme de ligne avec un espace entre chaque coordonn�e
		String s = "";
		int k = this.getDimension();
		for (int i = 0; i < k; i++){
			s += this.coord[i] + " ";
		}
		return s;
	}

	//V�rifie l'�galit� entre deux croix au niveau des coordonn�es
	@Override
	public boolean equals(Element elt) {
		Croix autre = (Croix) elt;
		int i =0;
		//On regarde si les coordonn�es � la dimension i sont �gales
		//On arrete lorsqu'il y a une in�galit�
		while( (i<this.coord.length) && (this.coord[i]==autre.getCoord()[i])){
			i++;
		}
		return (i==this.coord.length);
	}
	
	public int getDimension(){
		return this.coord.length;
	}

}
