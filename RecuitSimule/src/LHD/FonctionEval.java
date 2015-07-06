package LHD;

public abstract class FonctionEval {
	//Classe mère de toutes les fonctions d'évaluations pour le LHMaxiMin
	
	public int distance(Croix c1, Croix c2){
		//distance euclidienne au carré entre c1 et c2 (x² + y² + z²)
		int cpt = 0;
		for (int k = 0; k < c1.getDimension(); k++){
			cpt += (c1.getCoord()[k] - c2.getCoord()[k])*(c1.getCoord()[k] - c2.getCoord()[k]);
		}
		return cpt;
	}

	public abstract double calculer(Grille g);
	//calcule l'énergie potentielle de la grille
	
	public abstract double calculerdelta(Grille g, MutationLH m);
	//calcule la différence d'énergie entre la grille muté et la grille d'origine
}
