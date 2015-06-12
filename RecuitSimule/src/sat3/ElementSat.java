package sat3;
import java.util.ArrayList;

public class ElementSat{
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
	
	public boolean equals(ElementSat autre) {
		return ((autre.getxi()==this.xi)&&(autre.getassignation()==this.assignation));
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
