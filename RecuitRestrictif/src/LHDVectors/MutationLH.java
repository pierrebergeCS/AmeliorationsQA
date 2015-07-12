package LHDVectors;

import java.util.ArrayList;
import java.util.Collections;

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
		this.dimension = (int) (Math.random()*g.getCroix().get(0).getDimension());
		
		//liste mutations élémentaires
		ArrayList<MutationElementaire> l = new ArrayList<MutationElementaire>();
		
		int[] tabModifs = new int[g.getTaille()-1];
		
		Croix c1 = (Croix) g.getCroix().get(this.criticalIndex).clone();
		c1.getCoord()[this.dimension] = g.getCroix().get(this.index).getCoord()[this.dimension];
		if (this.criticalIndex != 0){
			Vecteur v11 = ((Vecteur) g.getListe().get(this.criticalIndex-1)).clone();
			tabModifs[this.criticalIndex-1] += c1.getCoord()[this.dimension] - g.getCroix().get(this.criticalIndex).getCoord()[this.dimension];
		}
		if (this.criticalIndex != g.getTaille()-1){
			Vecteur v12 = ((Vecteur) g.getListe().get(this.criticalIndex)).clone();
			tabModifs[this.criticalIndex] += - c1.getCoord()[this.dimension] + g.getCroix().get(this.criticalIndex).getCoord()[this.dimension];
		}
		
		
		Croix c2 = (Croix) g.getCroix().get(this.index).clone();
		c2.getCoord()[this.dimension] = g.getCroix().get(this.criticalIndex).getCoord()[this.dimension];
		if (this.index != 0){
			Vecteur v21 = ((Vecteur) g.getListe().get(this.index-1)).clone();
			tabModifs[this.index - 1] += c2.getCoord()[this.dimension] - g.getCroix().get(this.index).getCoord()[this.dimension];
		}
		if (this.index != g.getTaille()-1){
			Vecteur v22 = ((Vecteur) g.getListe().get(this.index)).clone();
			tabModifs[this.index] += - c2.getCoord()[this.dimension] + g.getCroix().get(this.index).getCoord()[this.dimension];
		}
		
		for (int k = 0; k < tabModifs.length; k++){
			if (tabModifs[k] != 0){
				Vecteur v = ((Vecteur)g.getListe().get(k)).clone();
				v.getCoord()[this.dimension] += tabModifs[k];
				MutationLHElementaire m = new MutationLHElementaire(v,k);
				l.add(m);
			}
		}
		
		this.listeMutations = l;
	}
	
	public MutationLH(Grille g,int index, int criticalIndex, int dimension){
		this.index = index;
		this.criticalIndex = criticalIndex;
		this.dimension = dimension;
		
		//liste mutations élémentaires
				ArrayList<MutationElementaire> l = new ArrayList<MutationElementaire>();
				
				int[] tabModifs = new int[g.getTaille()-1];
				
				Croix c1 = (Croix) g.getCroix().get(this.criticalIndex).clone();
				c1.getCoord()[this.dimension] = g.getCroix().get(this.index).getCoord()[this.dimension];
				if (this.criticalIndex != 0){
					Vecteur v11 = ((Vecteur) g.getListe().get(this.criticalIndex-1)).clone();
					tabModifs[this.criticalIndex-1] = v11.getCoord()[this.dimension] + c1.getCoord()[this.dimension] - g.getCroix().get(this.criticalIndex).getCoord()[this.dimension];
				}
				if (this.criticalIndex != g.getTaille()-1){
					Vecteur v12 = ((Vecteur) g.getListe().get(this.criticalIndex)).clone();
					tabModifs[this.criticalIndex] = v12.getCoord()[this.dimension] - c1.getCoord()[this.dimension] + g.getCroix().get(this.criticalIndex).getCoord()[this.dimension];
				}
				
				
				Croix c2 = (Croix) g.getCroix().get(this.index).clone();
				c2.getCoord()[this.dimension] = g.getCroix().get(this.criticalIndex).getCoord()[this.dimension];
				if (this.index != 0){
					Vecteur v21 = ((Vecteur) g.getListe().get(this.index-1)).clone();
					tabModifs[this.index - 1] = v21.getCoord()[this.dimension] + c2.getCoord()[this.dimension] - g.getCroix().get(this.index).getCoord()[this.dimension];
				}
				if (this.index != g.getTaille()-1){
					Vecteur v22 = ((Vecteur) g.getListe().get(this.index)).clone();
					tabModifs[this.index] = v22.getCoord()[this.dimension] - c2.getCoord()[this.dimension] + g.getCroix().get(this.index).getCoord()[this.dimension];
				}
				
				for (int k = 0; k < tabModifs.length; k++){
					if (tabModifs[k] != 0){
						Vecteur v = ((Vecteur)g.getListe().get(k)).clone();
						MutationLHElementaire m = new MutationLHElementaire(v,k);
						l.add(m);
					}
				}
				
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
		
		//Mutation sur les croix
		int randDim = this.dimension;
		Croix cc = g.getCroix().get(this.criticalIndex);
		Croix c = g.getCroix().get(this.index);
		int coordCC = cc.getCoord()[randDim];
		cc.getCoord()[randDim] = c.getCoord()[randDim];
		c.getCoord()[randDim] = coordCC;
		
		//Remet les croix à leur bonne place. On les trie selon la coordonnée de la 1ere dimension
		if (this.dimension == 0){
			Collections.swap(g.getCroix(),this.index,this.criticalIndex);
		}
		//Vecteurs
		int n = this.listeMutations.size();
		for (int i = 0; i < n; i++){
			this.listeMutations.get(i).faire(e);
		}
		
		//Points critiques et dmin actualisés
		
				int indexcc0 = -1;
				int indexcc1 = -1;
				
				int dminCC = g.getTaille()*g.getTaille()*g.getCroix().get(0).getDimension();
				int i1 = this.criticalIndex;
				for (int j1 = i1+1; j1<g.getTaille(); j1++){
					g.getMatrice()[i1][j1] = FonctionEval.distance(g.getCroix().get(i1),g.getCroix().get(j1));
					if (g.getMatrice()[i1][j1] <= dminCC){
						indexcc0 = i1;
						indexcc1 = j1;
						dminCC = g.getMatrice()[i1][j1];
					}
				}
				
				int j2 = this.criticalIndex;
				for (int i2 = 0; i2<j2; i2++){
					g.getMatrice()[i2][j2] = FonctionEval.distance(g.getCroix().get(i2),g.getCroix().get(j2));
					if (g.getMatrice()[i2][j2] <= dminCC){
						indexcc0 = i2;;
						indexcc1 = j2;
						dminCC = g.getMatrice()[i2][j2];
					}
				}
				
				int i3 = this.index;
				for (int j3 = i3+1; j3<g.getTaille(); j3++){
					g.getMatrice()[i3][j3] = FonctionEval.distance(g.getCroix().get(i3),g.getCroix().get(j3));
					if (g.getMatrice()[i3][j3] <= dminCC){
						indexcc0 = i3;
						indexcc1 = j3;
						dminCC = g.getMatrice()[i3][j3];
					}
				}
				
				int j4 = this.index;
				for (int i4 = 0; i4<j4; i4++){
					g.getMatrice()[i4][j4] = FonctionEval.distance(g.getCroix().get(i4),g.getCroix().get(j4));
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
		this.dimension = (int) (Math.random()*g.getCroix().get(0).getDimension());
		
		//liste mutations élémentaires
		ArrayList<MutationElementaire> l = new ArrayList<MutationElementaire>();
		
		Croix c1 = (Croix) g.getCroix().get(this.criticalIndex).clone();
		c1.getCoord()[this.dimension] = g.getCroix().get(this.index).getCoord()[this.dimension];
		if (this.criticalIndex != 0){
			Vecteur v11 = ((Vecteur) g.getListe().get(this.criticalIndex-1)).clone();
			v11.getCoord()[this.dimension] += c1.getCoord()[this.dimension] - g.getCroix().get(this.criticalIndex).getCoord()[this.dimension];
			MutationLHElementaire m11 = new MutationLHElementaire(v11,this.criticalIndex-1);
			l.add(m11);
		}
		if (this.criticalIndex != g.getTaille()-1){
			Vecteur v12 = ((Vecteur) g.getListe().get(this.criticalIndex)).clone();
			v12.getCoord()[this.dimension] -= c1.getCoord()[this.dimension] - g.getCroix().get(this.criticalIndex).getCoord()[this.dimension];
			MutationLHElementaire m12 = new MutationLHElementaire(v12,this.criticalIndex);
			l.add(m12);
		}
		
		
		Croix c2 = (Croix) g.getCroix().get(this.index).clone();
		c2.getCoord()[this.dimension] = g.getCroix().get(this.criticalIndex).getCoord()[this.dimension];
		if (this.index != 0){
			Vecteur v21 = ((Vecteur) g.getListe().get(this.index-1)).clone();
			v21.getCoord()[this.dimension] += c1.getCoord()[this.dimension] - g.getCroix().get(this.index).getCoord()[this.dimension];
			MutationLHElementaire m21 = new MutationLHElementaire(v21,this.index-1);
			l.add(m21);
		}
		if (this.index != g.getTaille()-1){
			Vecteur v22 = ((Vecteur) g.getListe().get(this.index)).clone();
			v22.getCoord()[this.dimension] -= c1.getCoord()[this.dimension] - g.getCroix().get(this.index).getCoord()[this.dimension];
			MutationLHElementaire m22 = new MutationLHElementaire(v22,this.index);
			l.add(m22);
		}
		
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
		this.dimension = (int) (Math.random()*g.getCroix().get(0).getDimension());
		
		//liste mutations élémentaires
		ArrayList<MutationElementaire> l = new ArrayList<MutationElementaire>();
		
		Croix c1 = (Croix) g.getCroix().get(this.criticalIndex).clone();
		c1.getCoord()[this.dimension] = g.getCroix().get(this.index).getCoord()[this.dimension];
		if (this.criticalIndex != 0){
			Vecteur v11 = ((Vecteur) g.getListe().get(this.criticalIndex-1)).clone();
			v11.getCoord()[this.dimension] += c1.getCoord()[this.dimension] - g.getCroix().get(this.criticalIndex).getCoord()[this.dimension];
			MutationLHElementaire m11 = new MutationLHElementaire(v11,this.criticalIndex-1);
			l.add(m11);
		}
		if (this.criticalIndex != g.getTaille()-1){
			Vecteur v12 = ((Vecteur) g.getListe().get(this.criticalIndex)).clone();
			v12.getCoord()[this.dimension] -= c1.getCoord()[this.dimension] - g.getCroix().get(this.criticalIndex).getCoord()[this.dimension];
			MutationLHElementaire m12 = new MutationLHElementaire(v12,this.criticalIndex);
			l.add(m12);
		}
		
		
		Croix c2 = (Croix) g.getCroix().get(this.index).clone();
		c2.getCoord()[this.dimension] = g.getCroix().get(this.criticalIndex).getCoord()[this.dimension];
		if (this.index != 0){
			Vecteur v21 = ((Vecteur) g.getListe().get(this.index-1)).clone();
			v21.getCoord()[this.dimension] += c1.getCoord()[this.dimension] - g.getCroix().get(this.index).getCoord()[this.dimension];
			MutationLHElementaire m21 = new MutationLHElementaire(v21,this.index-1);
			l.add(m21);
		}
		if (this.index != g.getTaille()-1){
			Vecteur v22 = ((Vecteur) g.getListe().get(this.index)).clone();
			v22.getCoord()[this.dimension] -= c1.getCoord()[this.dimension] - g.getCroix().get(this.index).getCoord()[this.dimension];
			MutationLHElementaire m22 = new MutationLHElementaire(v22,this.index);
			l.add(m22);
		}
		
		this.listeMutations = l;
	}

}
