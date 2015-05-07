package knapsackP;

import modele.Element;

public class ObjetDisponible extends Element {

	public int ref;
	public double poids;
	public double prix;
	
	public ObjetDisponible(int ref, double poids, double prix){
		this.ref = ref;
		this.poids = poids;
		this.prix = prix;
	}
	
	public int getRef(){
		return this.ref;
	}
	
	public double getPoids(){
		return this.poids;
	}
	
	public double getPrix(){
		return this.prix;
	}
	@Override
	public boolean equals(Element autre) {
		ObjetDisponible o = (ObjetDisponible) autre;
		return this.ref==o.getRef();
	}

}
