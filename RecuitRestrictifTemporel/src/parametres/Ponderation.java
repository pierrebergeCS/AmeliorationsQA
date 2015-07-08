package parametres;

public class Ponderation {
	ParametreGamma gamma;
	/**
	 * Construit un J(Gamma)
	 * @param gamma
	 * Param�tre Gamma correspondant
	 */
	public Ponderation(ParametreGamma gamma){
		this.gamma=gamma;
	}

	/**
	 * Permet d'�tablir un nouveau Gamma pour ce J
	 * @param gamma
	 * Nouveau param�tre Gamma
	 */
	public void setGamma(ParametreGamma gamma){
		this.gamma = gamma;
	}
	
	/**
	 * Calcule la valeur num�rique de J (loi en ln tanh)
	 * @param temperature
	 * Temp�rature du recuit
	 * @param p
	 * Coefficient Entier (fix� � la taille du probl�me dans le recuit)
	 * @return
	 * Valeur num�rique de J
	 */
	public double calcul(Temperature temperature, int p) {
		double t = temperature.getValue();
		return - t/2*Math.log(Math.tanh(this.gamma.getGamma()/(p*t))) ;
	}

}
