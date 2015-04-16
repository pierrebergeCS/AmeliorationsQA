package parametres;



public class ParametreurGamma {
	
	//Fonction r�ciproque � tanh
	/**
	 * Cette m�thode permet le passage de J vers Gamma. Si J=f(Gamma), cette m�thode correspond � la r�ciproque de f
	 * @param x
	 * Valeur num�rique de la pond�ration
	 * @return
	 * Retourne le Gamma associ�
	 */
	public static double argth(double x){
		return 0.5*Math.log((1+x)/(1-x)); 
	}

	// Ce parametreur donne les 3 attributs d'un parametre Gamma pour un recuit
	// Il essaie de comparer l'influence d'une amelioration d'Epot et d'Ecin
	// Etant donne un deltaE, le parametreur decrete qu'� la fin du recuit, une amelioration moyenne d'Ecin a la m�me influence qu'une amelioration deltaE/p.size() de Epot
	// Le poids permet de faie varier lin�airement l'influence d'une amelioration sur l'autre
	/**
	 * Param�tre le Gamma pour le recuit. On fixe Jfin = deltaE/(4*P) et Jdeb = 100*Jfin. 
	 * @param nombreIterations
	 * Nombre d'iterations du recuit
	 * @param nombreEtat
	 * Nombre de r�pliques pour le recuit
	 * @param temp
	 * Temp�rature du recuit
	 * @param deltaE
	 * Delta d'�nergie potentielle. Repr�sente le deuxi�me d�cile de la distribution des deltaE pour le graphe consid�r�
	 * @return
	 * Le param�tre Gamma que l'on va utiliser pour le recuit
	 */
	public static ParametreGamma parametrageGamma( int nombreIterations,int nombreEtat,Temperature temp, double deltaE){
		double t = temp.getValue();
		double finGamma = nombreEtat*t*argth(Math.exp(-deltaE/(2*t*nombreEtat)));
		double facteur = 1.0-Math.pow(0.01,1.0/nombreIterations);
		ParametreGamma gamma = new ParametreGamma(100*finGamma,facteur,finGamma);
		return gamma;
	}
}
