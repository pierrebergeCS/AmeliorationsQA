package parametres;

public class ParametreGammaExp extends ParametreGamma {

	public ParametreGammaExp(double temp, double froid, double temperaturefin) {
		super(temp, froid, temperaturefin);
	}

	@Override
	public void refroidissement() {
		this.gamma *= this.facteur;
	}
}
