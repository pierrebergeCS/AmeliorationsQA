package LHD;

public abstract class EnergieCinetiqueLH {
	
	public abstract double distanceIsing(Grille g1, Grille g2);
	
	public abstract double calculerEc(ParticuleLH p);
	
	public abstract double deltaSpins(ParticuleLH p, Grille e, MutationLH m);

}
