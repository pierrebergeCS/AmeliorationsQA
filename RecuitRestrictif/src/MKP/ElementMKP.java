package MKP;

public class ElementMKP {
	Objet o;
	int[] appartenance;
	
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
	
	
}
