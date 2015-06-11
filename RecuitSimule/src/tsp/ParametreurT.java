package tsp;

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
	public static List<Double> parametreurRecuit(Graphe g,IMutation m){
		
		Routage r;
		double deltaE = -1;
		List<Double> l = new LinkedList<Double>();
		for (int i =0; i < 1000; i++){
			deltaE = -1;
			while(deltaE<=0){
		
			r=new Routage(g);
			
			deltaE=m.calculerdeltaEp(r);
			m.maj(r);
			}
			l.add((deltaE));
			//On vient de générer une liste de 1000 échantillons deltaE du graphe g.
		}
		Collections.sort(l);
		//Ici, on choisit de se baser autour du 5e centile des échantillons générés précédemment.
		return l;
	}
}
