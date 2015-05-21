package tsp;

import java.util.ArrayList;
import java.util.Collections;

import modele.*;
import mutation.IMutation;

/**
 * Mutation 2-opt pour TSP
 * @author Pierre
 */
public class TwoOptMove extends IMutation {
	int taille;
	int i;
	int j;

	/**
	 * Construit une mutation 2-opt aléatoire
	 * @param r
	 * Route sur laquelle on veut muter
	 */
	public TwoOptMove(Routage r){
		this.taille = r.getTaille();
		int randIndex1 = 0;
		int randIndex2 = 0;
		//On choisit deux arêtes différentes, au hasard.
		while ((r.getNextIndex(randIndex1) == randIndex2) || (randIndex1 == randIndex2)){
			randIndex1 = (int) (this.taille * Math.random()); 
			randIndex2 = (int) (this.taille * Math.random());
		}
		
		//Indices mutations élémentaires
		this.i = randIndex1;
		this.j = randIndex2;
	}

	/**
	 * Fait la mutation 2-opt sur un Routage et modifie sa matrice d'Ising
	 * @param p
	 * Probleme considéré
	 * @param e
	 * Etat sur lequel on mute
	 * @return 
	 * Nouvel état après mutation 2-opt
	 */
	@Override
	public void faire(Etat e) {

		Routage routage = (Routage) e;
		
		//Mutation sur la liste de noeuds
		Collections.swap(routage.getListe(),this.i,this.j);
		
		
		//On swape les aretes pour obtenir la nouvelle route
		int k = routage.getNextIndex(this.i);
		int l = routage.getPreviousIndex(this.j);

		while (k!=l && routage.getPreviousIndex(k)!=l ) {
			
			Collections.swap(routage.getListe(),k,l);
			k=routage.getNextIndex(k);
			l=routage.getPreviousIndex(l);
			
		}
	}







	public double calculerdeltaEp(Etat e) {
		// Cette méthode va calculer le delta potentiel engendré par la mutation, qui n'a pas encore eu lieu.

		Routage r = (Routage) e;
		Graphe g = r.getGraphe();
		
		int NodeI  = r.getListe().get(this.i);
		int NodeBeforeI = r.getListe().get(r.getPreviousIndex(this.i));
		int NodeJ = r.getListe().get(this.j);
		int NodeAfterJ = r.getListe().get(r.getNextIndex(this.j));
				

		double cpt = 0;
		cpt += g.longueurEntre(NodeBeforeI,NodeJ);
		cpt += g.longueurEntre(NodeI,NodeAfterJ);
		cpt -= g.longueurEntre(NodeBeforeI,NodeI);
		cpt -= g.longueurEntre(NodeJ,NodeAfterJ);
		return cpt;
	}
	
	
	public void maj(Etat e){
		Routage r = (Routage) e;
		int randIndex1 = 0;
		int randIndex2 = 0;
		//On choisit deux arêtes différentes, au hasard.
		while ((r.getNextIndex(randIndex1) == randIndex2) || (randIndex1 == randIndex2)){
			randIndex1 = (int) (this.taille * Math.random()); 
			randIndex2 = (int) (this.taille * Math.random());
		}
		
		this.i = randIndex1;
		this.j = randIndex2;
	}
}


