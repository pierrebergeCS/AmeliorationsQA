package parametres;

public class Temperature {
	double value;
	
	/**
	 * Construit un param�tre T
	 * @param value
	 * Valeur num�rique le la temp�rature
	 */
	public Temperature(double value){
		this.value = value;
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
}
