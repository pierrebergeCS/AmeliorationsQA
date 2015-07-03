package LHD;

import java.util.ArrayList;

import modele.Etat;
import mutation.IMutation;

public class ImprovedMutationLH extends IMutation {
	
	//Mutation m3 d'Arpad
	
	
	int index;
	int criticalIndex;
	int dimension; // dimension sur laquelle on mute
	
	int bestDist;
	ArrayList<Integer> criticalPoints = new ArrayList<Integer>();
	
	public ImprovedMutationLH(int index, int criticalIndex, int dimension){
		this.index = index;
		this.criticalIndex = criticalIndex;
		this.dimension = dimension;
	}
	
	public ImprovedMutationLH(Grille g){
		//Choix des 2 indices
		this.criticalIndex = g.getCriticalPoints().get((int) (Math.random()*2));
		int index = this.criticalIndex;
		while (index == this.criticalIndex){
			index = (int) (Math.random()*g.getTaille());
		}
		this.index = index;
		
		//Choix de dimension
		int bestDist = 0;
		int bestDim = -1;
		int cc0 = -1;
		int cc1 = -1;
		for (int d = 0; d < g.getListe().get(0).getDimension(); d++){
			Grille gt = (Grille) g.clone();
			MutationLH m = new MutationLH(this.index,this.criticalIndex,d);
			m.faire(gt);
			if (gt.getdmin() > bestDist){
				bestDist = gt.getdmin();
				bestDim = d;
				cc0 = gt.getCriticalPoints().get(0);
				cc1 = gt.getCriticalPoints().get(1);
			}
		}
		this.dimension = bestDim;
		this.bestDist = bestDist;
		this.criticalPoints.add(cc0);
		this.criticalPoints.add(cc1);
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
	
	public int getbestDist(){
		return this.bestDist;
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
				g.findCriticalPoints();
	}

	@Override
	public double calculerdeltaEp(Etat e) {
		MutationLH m = new MutationLH(this.index,this.criticalIndex,this.dimension);
		return m.calculerdeltaEp(e);
	}

	@Override
	public void maj(Etat e) {
		Grille g = (Grille) e;
		//Choix des 2 indices
				this.criticalIndex = g.getCriticalPoints().get((int) (Math.random()*2));
				int index = this.criticalIndex;
				while (index == this.criticalIndex){
					index = (int) (Math.random()*g.getTaille());
				}
				this.index = index;
				
				//Choix de dimension
				int bestDist = 0;
				int bestDim = -1;
				int cc0 = -1;
				int cc1 = -1;
				for (int d = 0; d < g.getListe().get(0).getDimension(); d++){
					Grille gt = (Grille) g.clone();
					MutationLH m = new MutationLH(this.index,this.criticalIndex,d);
					m.faire(gt);
					
					if (gt.getdmin() > bestDist){
						bestDist = gt.getdmin();
						bestDim = d;
						cc0 = gt.getCriticalPoints().get(0);
						cc1 = gt.getCriticalPoints().get(1);
						
					}
				}
				this.dimension = bestDim;
				this.bestDist = bestDist;
				this.criticalPoints.add(cc0);
				this.criticalPoints.add(cc1);
	}

}
