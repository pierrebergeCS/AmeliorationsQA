package sat3;
import java.util.ArrayList;

import modele.Element;
public class ElementSat extends Element {
	private int xi;
	private boolean assignation;
	ArrayList<Integer> clauses;
	public ElementSat(int i, boolean b){
		this.xi=i;
		this.assignation=b;
		this.clauses=new ArrayList<Integer>();
	}
	public void ajouteClause(Minterme m){
		this.clauses.add(m.getNumber());
	}
	
	@Override
	public boolean equals(Element autre) {
		ElementSat a = (ElementSat) autre;
		
		return ((a.getxi()==this.xi)&&(a.getassignation()==this.assignation));
	}
	
	public int getxi() {
		// TODO Auto-generated method stub
		return this.xi;
	}
	
	public ArrayList<Integer> getMintermes(){		
		return this.clauses;
	}
	
	public boolean getassignation() {
		return this.assignation;
	}
	public void setMintermes(ArrayList<Integer> mintermes) {

		this.clauses=mintermes;
	}
	public void change() {
		this.assignation= !(this.assignation);
		
	}

}
