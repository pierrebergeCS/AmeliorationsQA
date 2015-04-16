package parametrage;

public class ParametreGamma {

	/**
	 * Gamma d�but
	 */
	private double gamma;
	private double facteur;
	private double gammafin;
	
	/**
	 * Construit un param�tre Gamma
	 * @param temp
	 * C'est la r�elle valeur num�rique de Gamma.
	 * @param froid
	 * C'est le facteur de d�croissance exponentiel de Gamma.
	 * @param temperaturefin
	 * Lorsque le recuit se termine, on fait en sorte de s'arr�ter � cette valeur.
	 */
	public ParametreGamma(double temp,double froid,double temperaturefin){
		this.setGamma(temp);
		this.facteur = froid;
		this.gammafin = temperaturefin;
	}
	
// Get et Set de la temperature initiale
	/**
	 * @return
	 * Retourne la valeur num�rique de Gamma
	 */
	public double getGamma() {
		return this.gamma;
	}

	/**
	 * Permet d'�tablir la nouvelle valeur num�rique de Gamma
	 * @param temperature
	 * Nouvelle valeur num�rique de Gamma
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
	 * Permet d'�tablir la nouvelle valeur finale de Gamma
	 * @param gammafin
	 * Nouvelle valeur finale de Gamma
	 */
	public void setGammaFin(double gammafin){
		this.gammafin = gammafin;
	}
//Get et Set du refroidissement, refroidissement de la temperature Recuit en exponentiel et lineaire
	/**
	 * Diminue la valeur de Gamma de mani�re exponentielle gr�ce au facteur associ�.
	 */
	public void refroidissementExp(){
		this.gamma *= 1-this.facteur;
	}
	
	/**
	 * Diminue la valeur de Gamma de mani�re lin�aire gr�ce au facteur associ�. Inutilis� pour le recuit.
	 */
	public void refroidissementLin(){
		this.gamma -= this.facteur;
	}

	/**
	 * @return
	 * Retourne le facteur de d�croissance de Gamma
	 */
	public double getFacteur() {
		return this.facteur;
	}

	/**
	 * Permet d'�tablir le nouveau facteur de d�croissance de Gamma
	 * @param facteurDeRefroidissement
	 * Nouveau facteur de d�croissance de Gamma
	 */
	public void setFacteur(double facteurDeRefroidissement) {
		this.facteur = facteurDeRefroidissement;
	}
	
	
}
