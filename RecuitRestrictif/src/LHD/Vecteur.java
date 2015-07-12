package LHD;

public class Vecteur{
	int[] coord;
	
	public Vecteur(int[] coord){
		this.coord = coord;
	}
	
	//Vecteur c1 -> c2
	public Vecteur(Croix c1, Croix c2){
		int dim = c1.getDimension();
		int[] tabV = new int[dim];
		for (int d = 0; d < dim; d++){
			tabV[d] = c2.getCoord()[d] - c1.getCoord()[d];
		}
		this.coord = tabV;
	}
	
	public int[] getCoord(){
		return this.coord;
	}
	
	public int getDimension(){
		return this.coord.length;
	}
	
	public boolean equals(Vecteur autre) {
		//On regarde si les coordonnées du vecteur sont identiques aux coordonnées de l'autre
		int i =0;
		while( (this.coord[i]==autre.coord[i]) && (i<this.coord.length) ){
			i++;
		}
		return (i==this.coord.length);
	}
	
	
	//Calcule l'angle de la projection des 2 vecteurs sur les dimensions 1 et 2
	public Angle calculeAngle(Vecteur autre, int dimension1, int dimension2){
		double pScalaire = 0;
		double pVectoriel = 0;
		double normeThis = 0;
		double normeAutre = 0;
		
		pScalaire += this.coord[dimension1]*autre.getCoord()[dimension1];
		pScalaire += this.coord[dimension2]*autre.getCoord()[dimension2];
		pVectoriel += this.coord[dimension1]*autre.getCoord()[dimension2];
		pVectoriel -= this.coord[dimension2]*autre.getCoord()[dimension1];
		normeThis += this.coord[dimension1]*this.coord[dimension1];
		normeThis += this.coord[dimension2]*this.coord[dimension2];
		normeAutre += autre.getCoord()[dimension1]*autre.getCoord()[dimension1];
		normeAutre += autre.getCoord()[dimension2]*autre.getCoord()[dimension2];
		
		double racineNormes = Math.sqrt(normeThis*normeAutre);
			
		return new Angle((pScalaire/racineNormes),(pVectoriel/racineNormes)) ;
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
		//On recopie les anciennes coordonnées dans un nouveau tableau
		int[] tab = new int[this.getDimension()];
		for (int i = 0; i < this.getDimension(); i++){
			tab[i] = this.getCoord()[i];
		}
		//A partir de ce tableau, on crée un nouveau vecteur
		return new Vecteur(tab);
	}


}
