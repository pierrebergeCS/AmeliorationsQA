package LHDVectors;

public abstract class EnergieCinetiqueLH {
	//Classe m�re de toutes les �nergies cin�tiques test�es
	
		//Distance d'Ising entre 2 r�pliques
		public abstract double distanceIsing(Grille g1, Grille g2);
		
		//Energie cin�tique d'une particule
		public abstract double calculerEc(ParticuleLH p);
		
		//Diff�rence d'Ec pour la particule lors d'une mutation de la r�plique e par m
		public abstract double deltaSpins(ParticuleLH p, Grille e, MutationLH m);
}
