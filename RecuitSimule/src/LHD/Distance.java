package LHD;

public class Distance {

	int dist;//valeur de la distance entre les deux croix
	int index0;//indice de la 1e croix
	int index1;//indice de la 2e croix
	
	public Distance(Grille g, int i, int j){
		this.index0 = i;
		this.index1 = j;
		this.dist = g.getEval().distance(g.getListe().get(i),g.getListe().get(j));
	}
	
	public Distance(int dist, int i, int j){
		this.dist = dist;
		this.index0 = i;
		this.index1 = j;
	}
	
	public int getValue(){
		return this.dist;
	}
	
	public int getI(){
		return this.index0;
	}
	
	public int getJ(){
		return this.index1;
	}
}
