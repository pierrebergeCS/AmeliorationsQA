package LHD_1fixed;

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
		
		//Choix de dimension
		ArrayList<Integer> lc0 = new ArrayList<Integer>();
		ArrayList<Integer> lc1 = new ArrayList<Integer>();
		ArrayList<Integer> bestDim = new ArrayList<Integer>();
		int bestDist = 0;
		for (int d = 0; d < g.getListe().get(0).getDimension(); d++){
			int currentBest = bestDist;
			Grille gt = (Grille) g.clone();
			MutationLH m = new MutationLH(this.index,this.criticalIndex,d);
			m.faire(gt);
			if (gt.getdmin() > currentBest){
				bestDist = gt.getdmin();
				lc0.clear();
				lc0.add(gt.getCriticalPoints().get(0));
				lc1.clear();
				lc1.add(gt.getCriticalPoints().get(1));
				bestDim.clear();
				bestDim.add(d);
			}
			if (gt.getdmin() == currentBest){
				lc0.add(gt.getCriticalPoints().get(0));
				lc1.add(gt.getCriticalPoints().get(1));
				bestDim.add(d);
			}
		}
		this.dimension = bestDim.get((int) (Math.random()*bestDim.size()));
		this.bestDist = bestDist;
		int k = (int) (Math.random()*lc0.size());
		ArrayList<Integer> l = new ArrayList<Integer>();
		l.add(lc0.get(k));
		l.add(lc1.get(k));
		this.criticalPoints = l;
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
				
				//Choix de dimension
				ArrayList<Integer> lc0 = new ArrayList<Integer>();
				ArrayList<Integer> lc1 = new ArrayList<Integer>();
				ArrayList<Integer> bestDim = new ArrayList<Integer>();
				int bestDist = 0;
				for (int d = 0; d < g.getListe().get(0).getDimension(); d++){
					int currentBest = bestDist;
					Grille gt = (Grille) g.clone();
					MutationLH m = new MutationLH(this.index,this.criticalIndex,d);
					m.faire(gt);
					if (gt.getdmin() > currentBest){
						bestDist = gt.getdmin();
						lc0.clear();
						lc0.add(gt.getCriticalPoints().get(0));
						lc1.clear();
						lc1.add(gt.getCriticalPoints().get(1));
						bestDim.clear();
						bestDim.add(d);
					}
					if (gt.getdmin() == currentBest){
						lc0.add(gt.getCriticalPoints().get(0));
						lc1.add(gt.getCriticalPoints().get(1));
						bestDim.add(d);
					}
				}
				this.dimension = bestDim.get((int) (Math.random()*bestDim.size()));
				this.bestDist = bestDist;
				int k = (int) (Math.random()*lc0.size());
				ArrayList<Integer> l = new ArrayList<Integer>();
				l.add(lc0.get(k));
				l.add(lc1.get(k));
				this.criticalPoints = l;
	}

}
