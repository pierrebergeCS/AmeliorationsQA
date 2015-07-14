package MKP;

import modele.Element;

public class ElementMKP extends Element {
	Objet o;
	boolean appartenance;// true = est Dedans
	
	public ElementMKP(Objet o, boolean appartenance){
		this.o = o;
		this.appartenance = appartenance;
	}
	
	public Objet getObjet(){
		return this.o;
	}
	
	public boolean getAppartenance(){
		return this.appartenance;
	}
	
	public void setAppartenance(boolean b){
		this.appartenance = b;
	}
	
	public void changeAppartenance(){
		boolean b = !this.appartenance;
		this.appartenance = b;
	}

	@Override
	public boolean equals(Element autre) {
		ElementMKP elt = (ElementMKP) autre;
		
		//Egalité sur les utilités
		boolean b1 = this.o.getUtility() == elt.getObjet().getUtility();
		
		//Egalité sur les poids
		int index = 0;
		while ((index < this.o.getWeight().length) && (this.o.getWeight()[index] == elt.getObjet().getWeight()[index])){
			index++;
		}
		boolean b2 = index==this.o.getWeight().length;
		
		//Egalité sur l'appartenance
		boolean b3 = (this.appartenance && elt.getAppartenance()) || (!this.appartenance && !elt.getAppartenance());
		
		return b1 && b2 && b3;
	}
	
	public ElementMKP clone(){
		Objet o2 = this.o; //Comme un objet est jamais touché, pas la peine de cloner ce qu'il y a dedans
		return new ElementMKP(o2,this.appartenance);
	}
	
	
}
