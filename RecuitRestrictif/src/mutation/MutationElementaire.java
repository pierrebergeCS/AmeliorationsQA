package mutation;

import java.util.ArrayList;

import modele.Element;
import modele.Etat;

/**
 * La mutation élémentaire est un "élément" d'une mutation quelconque.
 * Une mutation consiste à modifier des éléments de l'état que l'on traite.
 * Une mutation élémentaire modifie un seul élément de l'état.
 * @author Pierre
 *
 */
public class MutationElementaire {
	
	Element elt;
	int indice;
	
	public MutationElementaire(Element elt,int indice){
		this.elt = elt;
		this.indice = indice;
	}
	
	public Element getElement(){
		return this.elt;
	}
	
	/**
	 * Remplace l'élément situé à la i-ème place dans l'état e par this.elt
	 * @param e
	 * Etat sur lequel on travaille
	 * @param i
	 * Indice de l'élément remplacé
	 */
	public void faire(Etat e){
		e.getListe().set(this.indice,this.elt);
	}
	
	/**
	 * Determine si une mutation élémentaire est autorisée lorsque les éléments contenus dans interdictions sont bloqués
	 * @param interdictions
	 * Liste d'éléments bloqués
	 * @return
	 * True si this.elt est différent des éléments contenus dans interdictions, false sinon
	 */
	public boolean estAutorisee(ArrayList<Element> interdictions){
		int n = interdictions.size();
		int cpt = 0;
		boolean estAutorisee = true;
		while ((cpt < n) && (estAutorisee)){
			estAutorisee = !this.elt.equals(interdictions.get(cpt));
			cpt++;
		}
		return estAutorisee;
	}

}
