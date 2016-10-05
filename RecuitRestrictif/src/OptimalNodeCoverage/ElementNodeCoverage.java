package OptimalNodeCoverage;

import modele.Element;

public class ElementNodeCoverage extends Element{
	private Anchor anchor;
	private Set set;
	public ElementNodeCoverage(Anchor a, Set s) {
		this.anchor = a;
		this.set = s;
	}
	public Anchor getAnchor() {
		return anchor;
	}
	public void setAnchor(Anchor anchor) {
		this.anchor = anchor;
	}
	public Set getSet() {
		return set;
	}
	public void setSet(Set set) {
		this.set = set;
	}


	public boolean equals(Element e) {
		ElementNodeCoverage e1= ( ElementNodeCoverage)e;
		boolean b1=this.getAnchor().equals(e1.getAnchor());
		boolean b2=this.getSet().equals(e1.getSet());
		return b1&b2;
	}
	public ElementNodeCoverage clone(){
		Anchor a= this.anchor.clone();
		Set s= this.set.clone();
		return new ElementNodeCoverage(a,s);
	}






}
