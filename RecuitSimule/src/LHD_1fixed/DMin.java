package LHD_1fixed;

public class DMin extends FonctionEval {
	//Fonction d'évaluation correspondant à l'opposé de la distance minimale entre deux croix

	@Override
	public double calculer(Grille g) {
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
