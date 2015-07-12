package LHDVectors;

public abstract class EnergieCinetiqueLH {
	//Classe mère de toutes les énergies cinétiques testées
	
		//Distance d'Ising entre 2 répliques
		public abstract double distanceIsing(Grille g1, Grille g2);
		
		//Energie cinétique d'une particule
		public abstract double calculerEc(ParticuleLH p);
		
		//Différence d'Ec pour la particule lors d'une mutation de la réplique e par m
		public abstract double deltaSpins(ParticuleLH p, Grille e, MutationLH m);
}
