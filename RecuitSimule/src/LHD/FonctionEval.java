package LHD;

public abstract class FonctionEval {
	//Classe m�re de toutes les fonctions d'�valuations pour le LHMaxiMin
	
	public int distance(Croix c1, Croix c2){
		//distance euclidienne au carr� entre c1 et c2 (x� + y� + z�)
		int cpt = 0;
		for (int k = 0; k < c1.getDimension(); k++){
			cpt += (c1.getCoord()[k] - c2.getCoord()[k])*(c1.getCoord()[k] - c2.getCoord()[k]);
		}
		return cpt;
	}

	public abstract double calculer(Grille g);
	//calcule l'�nergie potentielle de la grille
	
	public abstract double calculerdelta(Grille g, MutationLH m);
	//calcule la diff�rence d'�nergie entre la grille mut� et la grille d'origine
}
