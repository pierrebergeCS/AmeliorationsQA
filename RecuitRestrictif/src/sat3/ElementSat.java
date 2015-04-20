package sat3;
import modele.Element;
public class ElementSat extends Element {
	private int xi;
	private boolean assignation;
	
	public ElementSat(int i, boolean b){
		this.xi=i;
		this.assignation=b;
	}
	
	
	@Override
	public boolean equals(Element autre) {
		ElementSat a = (ElementSat) autre;
		
		
		return ((a.getxi()==this.xi)&&(a.getassignation()==this.assignation));
	}
	
	private int getxi() {
		// TODO Auto-generated method stub
		return this.xi;
	}
	private boolean getassignation() {
		return this.assignation;
	}

}
