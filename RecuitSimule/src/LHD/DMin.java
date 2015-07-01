package LHD;

public class DMin extends FonctionEval {

	@Override
	public int distance(Croix c1, Croix c2) {
		return 0;
	}

	@Override
	public double calculer(Grille g) {
		return g.getdmin();
	}

	@Override
	public double calculerdelta(Grille g, MutationLH m) {
		g.findCriticalPoints();
		return g.getdmin();
	}

}
