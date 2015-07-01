package LHD;

import java.util.ArrayList;

import tsp.Routage;
import modele.Etat;
import modele.Probleme;
import mutation.IMutation;
import mutation.MutationElementaire;

public class MutationLH extends IMutation {
	int index;
	int criticalIndex;
	int dimension; // dimension sur laquelle on mute (ne pas confondre avec la dimension du pb)
	
	public MutationLH(Grille g){
		this.criticalIndex = g.getCriticalPoints().get((int) (Math.random()*2));
		int index = this.criticalIndex;
		while (index == this.criticalIndex){
			index = (int) (Math.random()*g.getTaille());
		}
		this.index = index;
		this.dimension = (int) (Math.random()*((Croix)g.getListe().get(0)).getDimension());
		
		//liste mutations élémentaires
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
	
	public void faire(Etat e) {
		Grille g = (Grille) e;
		
		int n = this.listeMutations.size();
		for (int i = 0; i < n; i++){
			this.listeMutations.get(i).faire(e);
		}
		
		g.findCriticalPoints();
	}

	public double calculerdeltaEp(Etat e) {
		Grille g = (Grille) e;
		return g.getEval().calculerdelta(g,this);
	}

	public void maj(Etat e) {
		Grille g = (Grille) e;
		this.criticalIndex = g.getCriticalPoints().get((int) (Math.random()*2));
		int index = this.criticalIndex;
		while (index == this.criticalIndex){
			index = (int) (Math.random()*g.getTaille());
		}
		this.index = index;
		this.dimension = (int) (Math.random()*((Croix)g.getListe().get(0)).getDimension());
		
		//liste mutations élémentaires
		Croix c1 = ((Croix)g.getListe().get(this.criticalIndex)).clone();
		c1.getCoord()[this.dimension] = ((Croix)g.getListe().get(this.index)).getCoord()[this.dimension];
		MutationLHElementaire m1 = new MutationLHElementaire(c1,this.criticalIndex);
		
		Croix c2 = ((Croix)g.getListe().get(this.index)).clone();
		c2.getCoord()[this.dimension] = ((Croix)g.getListe().get(this.criticalIndex)).getCoord()[this.dimension];
		MutationLHElementaire m2 = new MutationLHElementaire(c1,this.index);
		
		
		ArrayList<MutationElementaire> l = new ArrayList<MutationElementaire>();
		l.add(m1);
		l.add(m2);
		this.listeMutations = l;
	}
	
	@Override
	public void faire(Probleme p, Etat e){
		Grille g = (Grille) e;
		
		for (int i = 0; i < 2; i++){
			this.listeMutations.get(i).faire(e);
		}
		
		g.findCriticalPoints();
	}

	@Override
	public double calculerdeltaEp(Probleme p, Etat e) {
		Grille g = (Grille) e;
		return g.getEval().calculerdelta(g,this);
	}
	

	@Override
	public double calculerdeltaSpins(Probleme p, Etat e) {
		double cpt = 0;
		MutationLHElementaire m1 = (MutationLHElementaire) this.listeMutations.get(0);
		MutationLHElementaire m2 = (MutationLHElementaire) this.listeMutations.get(1);
		
		Croix prev1 = ((Croix) e.getListe().get(m1.getIndice())).clone();
		Croix prev2 = ((Croix) e.getListe().get(m2.getIndice())).clone();
		
		Croix next1 = (Croix) m1.getElement();
		Croix next2 = (Croix) m2.getElement();
		
		Grille left = (Grille) e.getPrevious();
		Grille right = (Grille) e.getNext();
		
		if (left.estCochee(prev1)) cpt-=1;
		if (left.estCochee(prev2)) cpt-=1;
		if (right.estCochee(prev1)) cpt-=1;
		if (right.estCochee(prev2)) cpt-=1;
		
		if (left.estCochee(next1)) cpt+=1;
		if (left.estCochee(next2)) cpt+=1;
		if (right.estCochee(next1)) cpt+=1;
		if (right.estCochee(next2)) cpt+=1;
		
		return cpt;
	}

	@Override
	public void maj(Probleme p, Etat e) {
		Grille g = (Grille) e;
		this.criticalIndex = g.getCriticalPoints().get((int) (Math.random()*2));
		int index = this.criticalIndex;
		while (index == this.criticalIndex){
			index = (int) (Math.random()*g.getTaille());
		}
		this.index = index;
		this.dimension = (int) (Math.random()*((Croix)g.getListe().get(0)).getDimension());
		
		//liste mutations élémentaires
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

}
