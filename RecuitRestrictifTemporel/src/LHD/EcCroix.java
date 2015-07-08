package LHD;

import modele.Etat;

public class EcCroix extends EnergieCinetiqueLH {
	//Energie cinétique qui prend en compte les superpositions de croix entre répliques
	//Cette énergie n'est pas efficace pour des grosses instances
	//Le nombre de cases étant trop grand, il y a peu de chances que 2 croix se superposent
	

	@Override
	public double distanceIsing(Grille g, Grille autre) {
		//Egal au nombre de croix qui se superposent
		int cpt = 0;
		for (int i = 0; i < g.getTaille(); i++){
			for (int j = 0; j < autre.getTaille(); j++){
				//Chaque fois que des croix issues de chaque état se superposent, on ajoute +1
				if (g.getListe().get(i).equals(autre.getListe().get(j))) cpt++;
			}
		}
		return cpt;
	}

	@Override
	public double calculerEc(ParticuleLH p) {
		//C'est la somme des distances d'Ising entre les répliques couplées.
		int nombreEtat = p.nombreEtat();
		double cpt =0;
		for (int k = 0; k < nombreEtat;k++){
				Etat e1=p.getEtat().get(k);// Etat courant
				Etat e2=e1.getNext();// Etat suivant dans la chaîne
				cpt+=distanceIsing((Grille)e1,(Grille)e2);//On calcule la distance d'Ising
		}
		return cpt;
	}

	@Override
	public double deltaSpins(ParticuleLH p, Grille e, MutationLH m) {
		//Différence d'énergie cinétique entre l'état muté avec m et l'état actuel
		double cpt = 0;
		//Les mutations élémentaires permettent d'aller chercher: 
		//     -l'élément qui fait son apparition (ici une croix)
		//     -l'indice de l'élément qui risque de disparaître
		MutationLHElementaire m1 = (MutationLHElementaire) m.listeMutations.get(0);
		MutationLHElementaire m2 = (MutationLHElementaire) m.listeMutations.get(1);
		
		//Les deux croix qui risquent de disparaître
		Croix prev1 = ((Croix) e.getListe().get(m1.getIndice())).clone();
		Croix prev2 = ((Croix) e.getListe().get(m2.getIndice())).clone();
		
		//Les deux croix qui risquent de les remplacer
		Croix next1 = (Croix) m1.getElement();
		Croix next2 = (Croix) m2.getElement();
		
		//Les grilles voisines de la grille courante
		Grille left = (Grille) e.getPrevious();
		Grille right = (Grille) e.getNext();
		
		//Si la croix qui tend à disparaître est présente dans un voisin, on diminue Ec
		if (left.estCochee(prev1)) cpt-=1;
		//Même raisonnement pour les autres
		if (left.estCochee(prev2)) cpt-=1;
		if (right.estCochee(prev1)) cpt-=1;
		if (right.estCochee(prev2)) cpt-=1;
		
		if (left.estCochee(next1)) cpt+=1;
		if (left.estCochee(next2)) cpt+=1;
		if (right.estCochee(next1)) cpt+=1;
		if (right.estCochee(next2)) cpt+=1;
		
		return cpt;
	}

}
