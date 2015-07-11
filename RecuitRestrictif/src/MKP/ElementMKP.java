package MKP;

import modele.Element;

public class ElementMKP extends Element {
	Objet o;
	int[] appartenance;// taille = nombre de sacs
	
	public ElementMKP(Objet o, int[] appartenance){
		this.o = o;
		this.appartenance = appartenance;
	}
	
	public Objet getObjet(){
		return this.o;
	}
	
	public int[] getAppartenance(){
		return this.appartenance;
	}

	public int getAppartenance(int k){
		return this.appartenance[k];
	}

	@Override
	public boolean equals(Element autre) {
		ElementMKP elt = (ElementMKP) autre;
		
		//Egalité sur les utilités
		boolean b1 = this.o.getUtility() == elt.getObjet().getUtility();
		
		//Egalité sur les poids
		int index = 0;
		while ((this.o.getWeight()[index] == elt.getObjet().getWeight()[index]) && (index < this.o.getWeight().length)){
			index++;
		}
		boolean b2 = index==this.o.getWeight().length;
		
		//Egalité sur l'appartenance
		index = 0;
		while ((this.appartenance[index] == elt.getAppartenance(index)) && (index < this.appartenance.length)){
			index++;
		}
		boolean b3 = index==this.appartenance.length;
		
		return b1 && b2 && b3;
	}
	
	public ElementMKP clone(){
		Objet o2 = this.o; //Comme un objet est jamais touché, pas la peine de cloner ce qu'il y a dedans
		int[] appartenance2 = new int[this.appartenance.length];
		for(int i = 0; i < this.appartenance.length; i++){
			appartenance2[i] = this.appartenance[i];
		}
		return new ElementMKP(o2,appartenance2);
	}
	
	
}
