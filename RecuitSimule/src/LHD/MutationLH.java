package LHD;

import modele.Etat;
import mutation.IMutation;

public class MutationLH extends IMutation {
	int index;
	int criticalIndex;
	int dimension; // dimension sur laquelle on mute
	
	public MutationLH(Grille g){
		this.criticalIndex = g.getCriticalPoints().get((int) (Math.random()*2));
		int index = this.criticalIndex;
		while (index == this.criticalIndex){
			index = (int) (Math.random()*g.getTaille());
		}
		this.index = index;
		this.dimension = (int) (Math.random()*g.getListe().get(0).getDimension());
	}

	public MutationLH(int index, int criticalIndex, int dimension){
		this.index = index;
		this.criticalIndex = criticalIndex;
		this.dimension = dimension;
	}
	
	public int getIndex(){
		return this.index;
	}
	
	public int getCriticalIndex(){
		return this.criticalIndex;
	}
	
	public int getDim(){
		return this.dimension;
	}
	
	@Override
	public void faire(Etat e) {
		Grille g = (Grille) e;
		int randDim = this.dimension;
		Croix cc = g.getListe().get(this.criticalIndex);
		Croix c = g.getListe().get(this.index);
		int coordCC = cc.getCoord()[randDim];
		cc.getCoord()[randDim] = c.getCoord()[randDim];
		c.getCoord()[randDim] = coordCC;
		
		g.findCriticalPoints();
	}

	@Override
	public double calculerdeltaEp(Etat e) {
		Grille g = (Grille) e;
		return g.getEval().calculerdelta(g,this);
	}

	@Override
	public void maj(Etat e) {
		Grille g = (Grille) e;
		this.criticalIndex = g.getCriticalPoints().get((int) (Math.random()*2));
		int index = this.criticalIndex;
		while (index == this.criticalIndex){
			index = (int) (Math.random()*g.getTaille());
		}
		this.index = index;
		this.dimension = (int) (Math.random()*g.getListe().get(0).getDimension());
	}

}
