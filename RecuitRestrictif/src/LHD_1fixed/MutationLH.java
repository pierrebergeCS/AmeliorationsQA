package LHD_1fixed;

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
		//Choix des 2 indices
		this.criticalIndex = g.getCriticalPoints().get((int) (Math.random()*2));// la croix est fixee
		while (((Croix)g.getListe().get(this.criticalIndex)).isFixed()){
					this.criticalIndex = g.getCriticalPoints().get((int) (Math.random()*2));
				}
				
				int index = this.criticalIndex;
				while (index == this.criticalIndex || ((Croix)g.getListe().get(index)).isFixed()){
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
	
	public MutationLH(Grille g,int index, int criticalIndex, int dimension){
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
		
		//Points critiques et dmin actualisés
		
				int indexcc0 = -1;
				int indexcc1 = -1;
				
				int dminCC = g.getTaille()*g.getTaille()*((Croix)g.getListe().get(0)).getDimension();
				int i1 = this.criticalIndex;
				for (int j1 = i1+1; j1<g.getTaille(); j1++){
					g.getMatrice()[i1][j1] = FonctionEval.distance(((Croix)g.getListe().get(i1)),((Croix)g.getListe().get(j1)));
					if (g.getMatrice()[i1][j1] <= dminCC){
						indexcc0 = i1;
						indexcc1 = j1;
						dminCC = g.getMatrice()[i1][j1];
					}
				}
				
				int j2 = this.criticalIndex;
				for (int i2 = 0; i2<j2; i2++){
					g.getMatrice()[i2][j2] = FonctionEval.distance(((Croix)g.getListe().get(i2)),((Croix)g.getListe().get(j2)));
					if (g.getMatrice()[i2][j2] <= dminCC){
						indexcc0 = i2;;
						indexcc1 = j2;
						dminCC = g.getMatrice()[i2][j2];
					}
				}
				
				int i3 = this.index;
				for (int j3 = i3+1; j3<g.getTaille(); j3++){
					g.getMatrice()[i3][j3] = FonctionEval.distance(((Croix)g.getListe().get(i3)),((Croix)g.getListe().get(j3)));
					if (g.getMatrice()[i3][j3] <= dminCC){
						indexcc0 = i3;
						indexcc1 = j3;
						dminCC = g.getMatrice()[i3][j3];
					}
				}
				
				int j4 = this.index;
				for (int i4 = 0; i4<j4; i4++){
					g.getMatrice()[i4][j4] = FonctionEval.distance(((Croix)g.getListe().get(i4)),((Croix)g.getListe().get(j4)));
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
		ParticuleLH pLH = (ParticuleLH) p;
		return pLH.getEc().deltaSpins(pLH,(Grille)e,this);
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
