package parametres;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import modele.*;
import mutation.IMutation;


public class ParametreurT {
	
	/**
	 * Renvoie la liste de 1000 deltaEpot tri�e. Permet le param�trage de T (au 5e centile) et de Gamma (voir param�treurGamma)
	 * @param p
	 * Probl�me en entr�e du recuit
	 * @param m
	 * Type de mutation que l'on traite
	 * @param nombreIterations
	 * Nombre d'it�rations du recuit
	 * @return
	 * Liste tri�e de 1000 deltaEpot
	 */
	public static List<Double> parametreurRecuit(Probleme p,IMutation m, int nbEchantillons){
		
		Etat r1;
		double deltaE = -1;
		List<Double> l = new LinkedList<Double>();
		for (int i =0; i < nbEchantillons; i++){
			deltaE = -1;
			while(deltaE<=0){
		
			r1=p.creeEtatAleatoire();
			
			
			deltaE=m.calculerdeltaEp(p,r1);
			m.maj(p,r1);
			}
			l.add((deltaE));
			//On vient de générer une liste de 1000 échantillons deltaE du graphe g.
		}
		Collections.sort(l);
		//Ici, on choisit de se baser autour du 5e centile des échantillons générés précédemment.
		return l;
	}
}
