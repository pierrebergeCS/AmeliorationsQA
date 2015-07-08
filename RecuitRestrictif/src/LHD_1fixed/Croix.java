package LHD_1fixed;

import modele.Element;

public class Croix extends Element{
	public int[] coord; //coordonnées de la croix
	// la taille du tableau est la dimension du problème
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
		//On va chercher chaque coordonnée de la croix courante
		for (int i = 0; i < this.getDimension(); i++){
			//On la copie dans un nouveau tableau
			tab[i] = this.getCoord()[i];
		}
		//On crée la nouvelle croix à partir de ce tableau
		return new Croix(tab,this.estFixee);
	}
	
	//Représente une croix sous forme de chaîne
	public String toString(){
		//Représentation sous forme de ligne avec un espace entre chaque coordonnée
		String s = "";
		int k = this.getDimension();
		for (int i = 0; i < k; i++){
			s += this.coord[i] + " ";
		}
		return s;
	}

	//Vérifie l'égalité entre deux croix au niveau des coordonnées
	@Override
	public boolean equals(Element elt) {
		Croix autre = (Croix) elt;
		int i =0;
		//On regarde si les coordonnées à la dimension i sont égales
		//On arrete lorsqu'il y a une inégalité
		while( (i<this.coord.length) && (this.coord[i]==autre.getCoord()[i])){
			i++;
		}
		return (i==this.coord.length);
	}
	
	public int getDimension(){
		return this.coord.length;
	}

}
