package parametres;

public class ParametreK {

	double K =1;
	
	/**
	 * Construit un param�tre K intervenant dans la probabilit� d'acceptation de l'�tat mut�. En g�nral, dans QA, K = 1
	 * @param e
	 * Valeur num�rique de K
	 */
	public ParametreK(double e){
		this.K = e;
	}
	/**
	 * @return
	 * Retourne la valeur num�rique de K
	 */
	public double getK(){
		return this.K;
	}

	/**
	 * Permet d'�tablir la nouvelle valeur num�rique de K
	 * @param e
	 * Nouvelle valeur num�rique de K
	 */
	public void setK(double e){
		this.K = e;
	}
	

	
}
