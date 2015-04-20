package tsp;

import java.util.ArrayList;

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
	 * Construit une mutation 2-opt al�atoire
	 * @param r
	 * Route sur laquelle on veut muter
	 */
	public TwoOptMove(Routage r){
		this.taille = r.getTaille();
		int randIndex1 = 0;//Indice du noeud c2
		int randIndex2 = 0;//Indice du noeud c1'
		//  On recalcule les indices de c2 et c1' jusqu'à ce que c2 soit différent de c1'.Notons que le cas c2=c1' ne change pas la route.
		while (randIndex1 == randIndex2){
			randIndex1 = (int) (this.taille * Math.random()); //c1'
			randIndex2 = (int) (this.taille * Math.random()); //c2
		}
		this.i = randIndex1;
		this.j = randIndex2;
	}

	/**
	 * @return
	 * Retourne l'indice I du 2-opt
	 */
	public int getI(){
		return this.i;
	}

	/**
	 * @return
	 * Retourne l'indice J du 2-opt
	 */
	public int getJ(){
		return this.j;
	}

	/**
	 * Fait la mutation 2-opt sur un Routage et modifie sa matrice d'Ising
	 * @param p
	 * Probleme consid�r�
	 * @param e
	 * Etat sur lequel on mute
	 * @return 
	 * Nouvel �tat apr�s mutation 2-opt
	 */
	public void faire(Probleme p, Etat e) {

		Routage routage = (Routage) e;
		//Mise � jour d'Ising
		int NodeI  = routage.getRoute().get(this.i);
		int NodeBeforeI = routage.getRoute().get(routage.getPreviousIndex(this.i));
		int NodeJ = routage.getRoute().get(this.j);
		int NodeAfterJ = routage.getRoute().get(routage.getNextIndex(this.j));

		//On modifie les spins concernes. La condition elimine un cas o� twoOptMove ne modifie rien.
		if(routage.getNextIndex(this.j)!=this.i){
			routage.disconnect(NodeBeforeI, NodeI);
			routage.disconnect(NodeAfterJ, NodeJ);
			routage.connect(NodeBeforeI, NodeJ);
			routage.connect(NodeAfterJ, NodeI);
		}

		//Mutation sur la liste de noeuds
		int k = this.i;
		int l = this.j;

		Swap.faire(routage,k,l);


		//On itère ensuite pour effectuer tous les échanges du twoOptMove
		while (k!=l && routage.getNextIndex(k)!=l ) {

			k=routage.getNextIndex(k);
			l=routage.getPreviousIndex(l);
			Swap.faire(routage,k,l);
		}
		//routage.updateIsing();
		
	}







	public double calculer(Probleme p, Etat e) {
		// Cette m�thode va calculer le delta potentiel engendr� par la mutation

		Graphe g = ((ParticuleTSP)p).getGraphe();
		Routage r = (Routage) e;
		ArrayList<Integer> l = r.getRoute();
		double cpt =0;
		if (r.getPreviousIndex(this.i)!=this.j){
			cpt-=g.getdists()[ l.get(this.i)][ l.get(r.getPreviousIndex(this.i))];
			cpt-=g.getdists()[ l.get(this.j)][ l.get(r.getNextIndex(this.j))];
			cpt+=g.getdists()[ l.get(this.i)][ l.get(r.getNextIndex(this.j))];
			cpt+=g.getdists()[ l.get(this.j)][ l.get(r.getPreviousIndex(this.i))];
			return cpt;
		}
		return cpt;
	}


	///// POUR CETTE MUTATION ON NE S'INTERESSE QU'A UNE MUTATION ETATIQUE

	@Override
	public double calculer(Probleme p) {
		// TODO Auto-generated method stub
		return Double.MAX_VALUE;
	}


	@Override
	public void faire(Probleme p) {
		// TODO Auto-generated method stub

	}
	public void maj(){
		int randIndex1 = 0;//Indice du noeud c2
		int randIndex2 = 0;//Indice du noeud c1'
		//  On recalcule les indices de c2 et c1' jusqu'à ce que c2 soit différent de c1'.Notons que le cas c2=c1' ne change pas la route.
		while (randIndex1 == randIndex2){
			randIndex1 = (int) (this.taille * Math.random()); //c1'
			randIndex2 = (int) (this.taille * Math.random()); //c2
		}
		this.i = randIndex1;
		this.j = randIndex2;
	}
}


