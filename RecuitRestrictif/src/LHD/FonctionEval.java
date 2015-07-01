package LHD;

public abstract class FonctionEval {
	
	public abstract int distance(Croix c1, Croix c2);

	public abstract double calculer(Grille g);
	
	public abstract double calculerdelta(Grille g, MutationLH m);
}
