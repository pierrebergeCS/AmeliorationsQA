package LHD;

public class DMin extends FonctionEval {
	// Fonction d'évaluation dmin

	@Override
	public double calculer(Grille g) {
		//On veut maximiser dmin donc minimiser -dmin
		return -g.getdmin();
	}

	@Override
	public double calculerdelta(Grille g, MutationLH m) {
		//Calcul de delta coûteux : on fait comme si la mutation s'exécutait, on en retire la différence de dmin
		//On n'a pas trouvé beaucoup mieux pour l'instant.
		int currentDmin = g.getdmin();
		Grille gt = (Grille) g.clone();
		m.faire(gt);
		return (-(gt.getdmin()-currentDmin));
	}

}
