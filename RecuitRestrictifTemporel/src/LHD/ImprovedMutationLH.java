package LHD;

import java.util.ArrayList;

import modele.Etat;
import modele.Probleme;
import mutation.IMutation;
import mutation.MutationElementaire;

public class ImprovedMutationLH extends IMutation {
	
	//Mutation m3 d'Arpad
	
	
	int index;
	int criticalIndex;
	int dimension; // dimension sur laquelle on mute
	
	int bestDist;
	ArrayList<Integer> criticalPoints = new ArrayList<Integer>();
	
	public ImprovedMutationLH(Grille g,int index, int criticalIndex, int dimension){
		this.index = index;
		this.criticalIndex = criticalIndex;
		this.dimension = dimension;
		
		Croix c1 = ((Croix)g.getListe().get(this.criticalIndex)).clone();
		c1.getCoord()[this.dimension] = ((Croix)g.getListe().get(this.index)).getCoord()[this.dimension];
		MutationLHElementaire m1 = new MutationLHElementaire(c1,this.criticalIndex);
		
		Croix c2 = ((Croix)g.getListe().get(this.index)).clone();
		c2.getCoord()[this.dimension] = ((Croix)g.getListe().get(this.criticalIndex)).getCoord()[this.dimension];
		MutationLHElementaire m2 = new MutationLHElementaire(c2,this.index);
		
		ArrayList<MutationElementaire> l = new ArrayList<MutationElementaire>();
		l.add(m1);
		l.add(m2);
		this.listeMutations = l;
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
		ArrayList<Integer> lc0 = new ArrayList<Integer>();
		ArrayList<Integer> lc1 = new ArrayList<Integer>();
		ArrayList<Integer> bestDim = new ArrayList<Integer>();
		int bestDist = 0;
		for (int d = 0; d < ((Croix)g.getListe().get(0)).getDimension(); d++){
			int currentBest = bestDist;
			Grille gt = (Grille) g.clone();
			MutationLH m = new MutationLH(gt,this.index,this.criticalIndex,d);
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
		ArrayList<Integer> lc = new ArrayList<Integer>();
		lc.add(lc0.get(k));
		lc.add(lc1.get(k));
		this.criticalPoints = lc;
		
		Croix c1 = ((Croix)g.getListe().get(this.criticalIndex)).clone();
		c1.getCoord()[this.dimension] = ((Croix)g.getListe().get(this.index)).getCoord()[this.dimension];
		MutationLHElementaire m1 = new MutationLHElementaire(c1,this.criticalIndex);
		
		Croix c2 = ((Croix)g.getListe().get(this.index)).clone();
		c2.getCoord()[this.dimension] = ((Croix)g.getListe().get(this.criticalIndex)).getCoord()[this.dimension];
		MutationLHElementaire m2 = new MutationLHElementaire(c2,this.index);
		
		ArrayList<MutationElementaire> l = new ArrayList<MutationElementaire>();
		l.add(m1);
		l.add(m2);
		this.listeMutations = l;
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
	public void faire(Probleme p,Etat e) {
				//Mutation
				Grille g = (Grille) e;
				int randDim = this.dimension;
				Croix cc = (Croix) g.getListe().get(this.criticalIndex);
				Croix c = (Croix) g.getListe().get(this.index);
				int coordCC = cc.getCoord()[randDim];
				cc.getCoord()[randDim] = c.getCoord()[randDim];
				c.getCoord()[randDim] = coordCC;
				
				//Points critiques et dmin actualisés
				g.findCriticalPoints();
	}

	@Override
	public double calculerdeltaEp(Probleme p, Etat e) {
		MutationLH m = new MutationLH((Grille)e,this.index,this.criticalIndex,this.dimension);
		return m.calculerdeltaEp(e);
	}

	@Override
	public void maj(Probleme p, Etat e) {
		Grille g = (Grille) e.clone();
		//Choix des 2 indices
				this.criticalIndex = g.getCriticalPoints().get((int) (Math.random()*2));
				int index = this.criticalIndex;
				while (index == this.criticalIndex){
					index = (int) (Math.random()*g.getTaille());
				}
				this.index = index;
				
				//Choix de dimension
				ArrayList<Integer> lc0 = new ArrayList<Integer>();
				ArrayList<Integer> lc1 = new ArrayList<Integer>();
				ArrayList<Integer> bestDim = new ArrayList<Integer>();
				int bestDist = 0;
				for (int d = 0; d < ((Croix)g.getListe().get(0)).getDimension(); d++){
					int currentBest = bestDist;
					Grille gt = (Grille) g.clone();
					MutationLH m = new MutationLH(gt,this.index,this.criticalIndex,d);
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
				ArrayList<Integer> lc = new ArrayList<Integer>();
				lc.add(lc0.get(k));
				lc.add(lc1.get(k));
				this.criticalPoints = lc;
				
				Croix c1 = ((Croix)g.getListe().get(this.criticalIndex)).clone();
				c1.getCoord()[this.dimension] = ((Croix)g.getListe().get(this.index)).getCoord()[this.dimension];
				MutationLHElementaire m1 = new MutationLHElementaire(c1,this.criticalIndex);
				
				Croix c2 = ((Croix)g.getListe().get(this.index)).clone();
				c2.getCoord()[this.dimension] = ((Croix)g.getListe().get(this.criticalIndex)).getCoord()[this.dimension];
				MutationLHElementaire m2 = new MutationLHElementaire(c2,this.index);
				
				ArrayList<MutationElementaire> l = new ArrayList<MutationElementaire>();
				l.add(m1);
				l.add(m2);
				this.listeMutations = l;
	}

	@Override
	public double calculerdeltaSpins(Probleme p, Etat e) {
		MutationLH m = new MutationLH((Grille)e,this.index,this.criticalIndex,this.dimension);
		ParticuleLH pLH = (ParticuleLH) p;
		return pLH.getEc().deltaSpins(pLH,(Grille)e,m);
	}

}
