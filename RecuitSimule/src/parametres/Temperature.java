package parametres;

public class Temperature {
	double value;
	double start;
	double end;
	
	/**
	 * Construit un param�tre T
	 * @param value
	 * Valeur num�rique le la temp�rature
	 */
	public Temperature(double start, double end){
		this.start = start;
		this.end = end;
		this.value = start;
	}

	/**
	 * @return
	 * Retourne la valeur num�rique de la temp�rature
	 */
	public double getValue(){
		return this.value;
	}
	
	/**
	 * Permet d'�tablir une nouvelle valeur num�rique de T
	 * @param value
	 * Nouvelle valeur num�rique de T
	 */
	public void setValue(double value){
		this.value = value;
	}
	
	public void maj(int currentIter, int nbIterations){
		this.value = ((this.start-this.end)*currentIter)/nbIterations;
	}
	
}
