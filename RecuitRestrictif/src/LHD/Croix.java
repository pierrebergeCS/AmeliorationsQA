package LHD;

import modele.Element;

public class Croix extends Element{
	public int[] coord;
	
	public Croix(int[] coord){
		this.coord = coord;
	}
	
	public int[] getCoord(){
		return this.coord;
	}
	
	public Croix clone(){
		int[] tab = new int[this.getDimension()];
		for (int i = 0; i < this.getDimension(); i++){
			tab[i] = this.getCoord()[i];
		}
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

	@Override
	public boolean equals(Element elt) {
		Croix autre = (Croix) elt;
		int i =0;
		while( (i<this.coord.length) && (this.coord[i]==autre.getCoord()[i])){
			i++;
		}
		return (i==this.coord.length);
	}
	
	public int getDimension(){
		return this.coord.length;
	}

}
