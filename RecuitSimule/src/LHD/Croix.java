package LHD;

public class Croix {
	public int[] coord;
	
	public Croix(int[] coord){
		this.coord = coord;
	}
	
	public int[] getCoord(){
		return this.coord;
	}
	
	public boolean equals(Croix autre) {
		int i =0;
		while( (this.coord[i]==autre.coord[i]) && (i<this.coord.length) ){
			i++;
		}
		return (i==this.coord.length);
	}
	
	public int getDimension(){
		return this.coord.length;
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

}
