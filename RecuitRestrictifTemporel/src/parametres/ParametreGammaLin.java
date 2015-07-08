package parametres;

public class ParametreGammaLin extends ParametreGamma {

	public ParametreGammaLin(double temp, double froid, double temperaturefin) {
		super(temp, froid, temperaturefin);
	}

	@Override
	public void refroidissement() {
		this.gamma -= this.facteur;
	}

}
