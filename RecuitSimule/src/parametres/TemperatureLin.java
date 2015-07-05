package parametres;

public class TemperatureLin extends Temperature {
	double value;
	double start;
	double end;
	
	/**
	 * Construit un paramètre T
	 * @param value
	 * Valeur numérique le la température
	 */
	public TemperatureLin(double start, double end){
		this.start = start;
		this.end = end;
		this.value = start;
	}

	/**
	 * @return
	 * Retourne la valeur numérique de la température
	 */
	public double getValue(){
		return this.value;
	}
	
	/**
	 * Permet d'établir une nouvelle valeur numérique de T
	 * @param value
	 * Nouvelle valeur numérique de T
	 */
	public void setValue(double value){
		this.value = value;
	}
	
	public void maj(int currentIter, int nbIterations){
		this.setValue(this.start - ((this.start-this.end)*currentIter)/nbIterations);
	}
	
}
