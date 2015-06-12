package latinHypercubeMaximin;

import modele.Element;

public class Croix extends Element {
	public int[] coord;
	
	
	@Override
	public boolean equals(Element autre) {
		Croix c = (Croix) autre;
		int i =0;
		while(this.coord[i]==c.coord[i]&i<this.coord.length){
			i++;
		}
		return !(i==0)&&(this.coord[i-1]==c.coord[i-1]);
	}

}
