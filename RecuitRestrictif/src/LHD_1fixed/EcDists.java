package LHD_1fixed;

import modele.Etat;

public class EcDists extends EnergieCinetiqueLH {
	//Energie cinétique basés sur les similarités de distance
	//Pour chaque croix de la 1e grille, on regarde la distance avec une croix de la 2e grille
	//On ajoute +1 à cette distance et on prend l'inverse du tout
	//On somme pour tout couple (CroixGrille1,CroixGrille2) et on obtient la distance entre deux grilles
	

	@Override
	public double distanceIsing(Grille g1, Grille g2){
		double cpt = 0;
		int n = g1.getTaille();
		//On parcourt les croix de g1
		for (int i = 0; i < n; i++){
			//On parcourt les croix de g2
			for (int j = 0; j < n; j++){
				//distance entre les deux croix
				double dist = FonctionEval.distance(((Croix)g1.getListe().get(i)),((Croix)g2.getListe().get(j)));
				//On fait +1 et on inverse
				cpt += 1.0/(1.0+dist);
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
		return 1000*cpt;
	}

	@Override
	public double deltaSpins(ParticuleLH p, Grille e, MutationLH m) {
		//Différence d'énergie cinétique entre l'état muté avec m et l'état actuel
		double cpt = 0;
		int n = e.getTaille();
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
		
		//Dans cette boucle, on calcule la distance de prev1 avec toutes les croix des voisins de l'état traité
		//On en déduit la variation dûe à cette croix
		for (int i = 0; i < n; i++){
			double distL = FonctionEval.distance(((Croix)left.getListe().get(i)),prev1);
			double distR = FonctionEval.distance(((Croix)right.getListe().get(i)),prev1);
			cpt -= (1.0/(1.0+distL) + 1.0/(1.0+distR));
		}
		
		//Idem prev2
		for (int i = 0; i < n; i++){
			double distL = FonctionEval.distance(((Croix)left.getListe().get(i)),prev2);
			double distR = FonctionEval.distance(((Croix)right.getListe().get(i)),prev2);
			cpt -= (1.0/(1.0+distL) + 1.0/(1.0+distR));
		}
		
		//Idem next1
		for (int i = 0; i < n; i++){
			double distL = FonctionEval.distance(((Croix)left.getListe().get(i)),next1);
			double distR = FonctionEval.distance(((Croix)right.getListe().get(i)),next1);
			cpt += (1.0/(1.0+distL) + 1.0/(1.0+distR));
		}
		
		//Idem next2
		for (int i = 0; i < n; i++){
			double distL = FonctionEval.distance(((Croix)left.getListe().get(i)),next2);
			double distR = FonctionEval.distance(((Croix)right.getListe().get(i)),next2);
			cpt += (1.0/(1.0+distL) + 1.0/(1.0+distR));
		}
		
		return 1000*cpt;
	}

}
