package LHD;

public class EcNull extends EnergieCinetiqueLH {

	@Override
	public double distanceIsing(Grille g1, Grille g2) {
		return 0;
	}

	@Override
	public double calculerEc(ParticuleLH p) {
		return 0;
	}

	@Override
	public double deltaSpins(ParticuleLH p, Grille e, MutationLH m) {
		return 0;
	}

}
