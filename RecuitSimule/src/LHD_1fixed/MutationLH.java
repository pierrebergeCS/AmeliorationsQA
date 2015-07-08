package LHD_1fixed;

import java.util.ArrayList;

import modele.Etat;
import mutation.IMutation;

public class MutationLH extends IMutation {
	
	
	//Mutation m2 d'Arpad
	
	
	int index;
	int criticalIndex;
	int dimension; // dimension sur laquelle on mute
	
	public MutationLH(Grille g){
		this.criticalIndex = g.getCriticalPoints().get((int) (Math.random()*2));// la croix est fixee
		while (((Croix)g.getListe().get(this.criticalIndex)).isFixed()){
			this.criticalIndex = g.getCriticalPoints().get((int) (Math.random()*2));
		}
		
		int index = this.criticalIndex;
		while (index == this.criticalIndex || g.getListe().get(index).isFixed()){
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
		//Mutation
		Grille g = (Grille) e;
		int randDim = this.dimension;
		Croix cc = g.getListe().get(this.criticalIndex);
		Croix c = g.getListe().get(this.index);
		int coordCC = cc.getCoord()[randDim];
		cc.getCoord()[randDim] = c.getCoord()[randDim];
		c.getCoord()[randDim] = coordCC;
		
		//Points critiques et dmin actualisés
		
		int indexcc0 = -1;
		int indexcc1 = -1;
		
		int dminCC = g.getTaille()*g.getTaille()*g.getListe().get(0).getDimension();
		int i1 = this.criticalIndex;
		for (int j1 = i1+1; j1<g.getTaille(); j1++){
			g.getMatrice()[i1][j1] = g.getEval().distance(g.getListe().get(i1),g.getListe().get(j1));
			if (g.getMatrice()[i1][j1] <= dminCC){
				indexcc0 = i1;
				indexcc1 = j1;
				dminCC = g.getMatrice()[i1][j1];
			}
		}
		
		int j2 = this.criticalIndex;
		for (int i2 = 0; i2<j2; i2++){
			g.getMatrice()[i2][j2] = g.getEval().distance(g.getListe().get(i2),g.getListe().get(j2));
			if (g.getMatrice()[i2][j2] <= dminCC){
				indexcc0 = i2;;
				indexcc1 = j2;
				dminCC = g.getMatrice()[i2][j2];
			}
		}
		
		int i3 = this.index;
		for (int j3 = i3+1; j3<g.getTaille(); j3++){
			g.getMatrice()[i3][j3] = g.getEval().distance(g.getListe().get(i3),g.getListe().get(j3));
			if (g.getMatrice()[i3][j3] <= dminCC){
				indexcc0 = i3;
				indexcc1 = j3;
				dminCC = g.getMatrice()[i3][j3];
			}
		}
		
		int j4 = this.index;
		for (int i4 = 0; i4<j4; i4++){
			g.getMatrice()[i4][j4] = g.getEval().distance(g.getListe().get(i4),g.getListe().get(j4));
			if (g.getMatrice()[i4][j4] <= dminCC){
				indexcc0 = i4;
				indexcc1 = j4;
				dminCC = g.getMatrice()[i4][j4];
			}
		}
		
		if (dminCC <= g.getdmin()){
			g.setDmin(dminCC);
			ArrayList<Integer> l = new ArrayList<Integer>(2);
			l.add(indexcc0);
			l.add(indexcc1);
			g.setCriticalPoints(l);
		} else {
			g.findCriticalPoints();
		}
		
	}

	@Override
	public double calculerdeltaEp(Etat e) {
		Grille g = (Grille) e;
		return g.getEval().calculerdelta(g,this);
	}

	@Override
	public void maj(Etat e) {
		Grille g = (Grille) e;
		this.criticalIndex = 0;// la croix est fixee
		while (g.getListe().get(this.criticalIndex).isFixed()){
			this.criticalIndex = g.getCriticalPoints().get((int) (Math.random()*2));
		}
		
		int index = this.criticalIndex;
		while (index == this.criticalIndex || g.getListe().get(index).isFixed()){
			index = (int) (Math.random()*g.getTaille());
		}
		this.index = index;
		this.dimension = (int) (Math.random()*g.getListe().get(0).getDimension());
	}

}
