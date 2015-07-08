package tsp;

import java.util.ArrayList;
import java.util.Collections;

import modele.*;
import mutation.IMutation;
import mutation.MutationElementaire;

/**
 * Mutation 2-opt pour TSP
 * @author Pierre
 */
public class TwoOptMove extends IMutation {
	int taille;

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
		int indice1 = randIndex1;
		int indice2 = randIndex2;
		
		//Elements
		Arete a1 = new Arete(((Arete)r.getListe().get(indice1)).getNoeud1(),((Arete)r.getListe().get(indice2)).getNoeud1());
		Arete a2 = new Arete(((Arete)r.getListe().get(indice1)).getNoeud2(),((Arete)r.getListe().get(indice2)).getNoeud2());
		
		ArrayList<MutationElementaire> l = new ArrayList<MutationElementaire>();
		l.add(new TwoOptMoveElementaire(a1,indice1));
		l.add(new TwoOptMoveElementaire(a2,indice2));
		this.listeMutations = l;
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
	public void faire(Probleme p, Etat e) {

		Routage routage = (Routage) e;
		int indice1 = this.listeMutations.get(0).getIndice();
		int indice2 = this.listeMutations.get(1).getIndice();
		//Mise à jour d'Ising
		int NodeI  = ((Arete)routage.getListe().get(indice1)).getNoeud2();
		int NodeBeforeI = ((Arete)routage.getListe().get(indice1)).getNoeud1();
		int NodeJ = ((Arete)routage.getListe().get(indice2)).getNoeud1();
		int NodeAfterJ = ((Arete)routage.getListe().get(indice2)).getNoeud2();

		//On modifie les spins concernes.
		
		routage.disconnect(NodeBeforeI, NodeI);
		routage.disconnect(NodeAfterJ, NodeJ);
		routage.connect(NodeBeforeI, NodeJ);
		routage.connect(NodeAfterJ, NodeI);
		

		//Mutation sur la liste de noeuds
		this.listeMutations.get(0).faire(routage);
		this.listeMutations.get(1).faire(routage);
		
		//On inverse les arêtes entre indice1 et indice2
		int cpt = routage.getNextIndex(indice1);
		
		while (cpt != indice2){
			Arete a1 = (Arete)routage.getListe().get(cpt);
			Arete a2 = a1.reverse();
			routage.getListe().set(cpt,a2);
			cpt = routage.getNextIndex(cpt);
		}
		
		//On swape les aretes pour obtenir la nouvelle route
		int k = routage.getNextIndex(indice1);
		int l = routage.getPreviousIndex(indice2);

		while (k!=l && routage.getPreviousIndex(k)!=l ) {
			
			Collections.swap(routage.getListe(),k,l);
			k=routage.getNextIndex(k);
			l=routage.getPreviousIndex(l);
			
		}
		
		
		//routage.updateIsing();
		
	}







	public double calculerdeltaEp(Probleme p, Etat e) {
		// Cette méthode va calculer le delta potentiel engendré par la mutation, qui n'a pas encore eu lieu.

		Graphe g = ((ParticuleTSP)p).getGraphe();
		Routage r = (Routage) e;
		double cpt = 0;
		cpt += ((Arete)this.listeMutations.get(0).getElement()).longueur(g);
		cpt += ((Arete)this.listeMutations.get(1).getElement()).longueur(g);
		cpt -= ((Arete)r.getListe().get(this.listeMutations.get(0).getIndice())).longueur(g);
		cpt -= ((Arete)r.getListe().get(this.listeMutations.get(1).getIndice())).longueur(g);
		return cpt;
	}
	
	public double calculerdeltaSpins(Probleme p, Etat e){
		double cptspin = 0;
		Routage r = (Routage) e;
		Arete ap1 = (Arete) this.listeMutations.get(0).getElement();
		Arete ap2 = (Arete) this.listeMutations.get(1).getElement();
		Arete av1 = (Arete) r.getListe().get(this.listeMutations.get(0).getIndice());
		Arete av2 = (Arete) r.getListe().get(this.listeMutations.get(1).getIndice());
		
		Routage left = (Routage) e.getPrevious();
		Routage right = (Routage) e.getNext();
		
		cptspin -= left.valueIsing(av1.getNoeud1(),av1.getNoeud2()) + right.valueIsing(av1.getNoeud1(),av1.getNoeud2());
		cptspin -= left.valueIsing(av2.getNoeud1(),av2.getNoeud2()) + right.valueIsing(av2.getNoeud1(),av2.getNoeud2());
		cptspin += left.valueIsing(ap1.getNoeud1(),ap1.getNoeud2()) + right.valueIsing(ap1.getNoeud1(),ap1.getNoeud2());
		cptspin += left.valueIsing(ap2.getNoeud1(),ap2.getNoeud2()) + right.valueIsing(ap2.getNoeud1(),ap2.getNoeud2());
		
		return (2*cptspin);
	}


	///// POUR CETTE MUTATION ON NE S'INTERESSE QU'A UNE MUTATION ETATIQUE

	public void maj(Probleme p, Etat e){
		Routage r = (Routage) e;
		int randIndex1 = 0;
		int randIndex2 = 0;
		//On choisit deux arêtes différentes, au hasard.
		while ((r.getNextIndex(randIndex1) == randIndex2) || (randIndex1 == randIndex2)){
			randIndex1 = (int) (this.taille * Math.random()); 
			randIndex2 = (int) (this.taille * Math.random());
		}
		
		//Indices mutations élémentaires
		int indice1 = randIndex1;
		int indice2 = randIndex2;
		
		//Elements
		Arete a1 = new Arete(((Arete)r.getListe().get(indice1)).getNoeud1(),((Arete)r.getListe().get(indice2)).getNoeud1());
		Arete a2 = new Arete(((Arete)r.getListe().get(indice1)).getNoeud2(),((Arete)r.getListe().get(indice2)).getNoeud2());
		
		ArrayList<MutationElementaire> l = new ArrayList<MutationElementaire>();
		l.add(new TwoOptMoveElementaire(a1,indice1));
		l.add(new TwoOptMoveElementaire(a2,indice2));
		this.listeMutations = l;
	}
}


