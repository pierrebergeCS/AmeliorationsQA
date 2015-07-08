package LHD_1fixed;

public class DMin extends FonctionEval {
	//Fonction d'�valuation correspondant � l'oppos� de la distance minimale entre deux croix

	@Override
	public double calculer(Grille g) {
		return -g.getdmin();
	}

	@Override
	public double calculerdelta(Grille g, MutationLH m) {
		//Calcul de delta co�teux : on fait comme si la mutation s'ex�cutait, on en retire la diff�rence de dmin
		//On n'a pas trouv� beaucoup mieux pour l'instant.
		int currentDmin = g.getdmin();
		Grille gt = (Grille) g.clone();
		m.faire(gt);
		return (-(gt.getdmin()-currentDmin));
	}

}
