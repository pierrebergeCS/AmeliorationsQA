package LHD;

public class Croix {
	public int[] coord; //les coordonnées de la croix
	//La dimension de la grille correspondra à la longueur de ce tableau
	
	public Croix(int[] coord){
		this.coord = coord;
	}
	
	public int[] getCoord(){
		return this.coord;
	}
	
	public boolean equals(Croix autre) {
		//On regarde si les coordonnées de la croix courante sont identiques aux coordonnées de l'autre
		int i =0;
		while( (this.coord[i]==autre.coord[i]) && (i<this.coord.length) ){
			i++;
		}
		return (i==this.coord.length);
	}
	
	public int getDimension(){
		return this.coord.length;
	}
	
	public Croix clone(){
		//On recopie les anciennes coordonnées dans un nouveau tableau
		int[] tab = new int[this.getDimension()];
		for (int i = 0; i < this.getDimension(); i++){
			tab[i] = this.getCoord()[i];
		}
		//A partir de ce tableau, on crée une nouvelle croix
		return new Croix(tab);
	}
	
	public String toString(){
		String s = "";
		int k = this.getDimension();
		for (int i = 0; i < k; i++){
			s += this.coord[i] + " ";
		}
		return s;
	}

}
