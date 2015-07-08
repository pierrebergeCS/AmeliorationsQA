package parametres;

public abstract class ParametreGamma {

	/**
	 * Gamma début
	 */
	protected double gamma;
	protected double facteur;
	protected double gammafin;
	
	/**
	 * Construit un paramètre Gamma
	 * @param temp
	 * C'est la réelle valeur numérique de Gamma.
	 * @param froid
	 * C'est le facteur de décroissance exponentiel de Gamma.
	 * @param temperaturefin
	 * Lorsque le recuit se termine, on fait en sorte de s'arrêter à cette valeur.
	 */
	public ParametreGamma(double temp,double froid,double temperaturefin){
		this.setGamma(temp);
		this.facteur = froid;
		this.gammafin = temperaturefin;
	}
	
// Get et Set de la temperature initiale
	/**
	 * @return
	 * Retourne la valeur numérique de Gamma
	 */
	public double getGamma() {
		return this.gamma;
	}

	/**
	 * Permet d'établir la nouvelle valeur numérique de Gamma
	 * @param temperature
	 * Nouvelle valeur numérique de Gamma
	 */
	public void setGamma(double temperature) {
		this.gamma = temperature;
	}
//Get et Set de la temperature de fin	
	/**
	 * @return
	 * Retourne la valeur finale de Gamma
	 */
	public double getGammaFin(){
		return this.gammafin;
	}
	
	/**
	 * Permet d'établir la nouvelle valeur finale de Gamma
	 * @param gammafin
	 * Nouvelle valeur finale de Gamma
	 */
	public void setGammaFin(double gammafin){
		this.gammafin = gammafin;
	}
//Get et Set du refroidissement, refroidissement de la temperature Recuit en exponentiel et lineaire
	public  abstract void refroidissement();

	/**
	 * @return
	 * Retourne le facteur de décroissance de Gamma
	 */
	public double getFacteur() {
		return this.facteur;
	}

	/**
	 * Permet d'établir le nouveau facteur de décroissance de Gamma
	 * @param facteurDeRefroidissement
	 * Nouveau facteur de décroissance de Gamma
	 */
	public void setFacteur(double facteurDeRefroidissement) {
		this.facteur = facteurDeRefroidissement;
	}
	
	
}
