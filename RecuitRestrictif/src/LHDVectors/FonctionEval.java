package LHDVectors;

import LHDVectors.Croix;

public abstract class FonctionEval {
	
	public static int distance(Croix c1, Croix c2){
		int cpt = 0;
		for (int k = 0; k < c1.getDimension(); k++){
			cpt += (c1.getCoord()[k] - c2.getCoord()[k])*(c1.getCoord()[k] - c2.getCoord()[k]);
		}
		return cpt;
	}

	public abstract double calculer(Grille g);
	
	public abstract double calculerdelta(Grille g, MutationLH m);
}
