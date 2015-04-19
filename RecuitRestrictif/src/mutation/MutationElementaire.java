package mutation;

import java.util.ArrayList;

import modele.Element;
import modele.Etat;
import modele.Probleme;
import modele.RedondancesParticuleGeneral;

/**
 * La mutation élémentaire est un "élément" d'une mutation quelconque.
 * Une mutation consiste à modifier des éléments de l'état que l'on traite.
 * Une mutation élémentaire modifie un seul élément de l'état.
 * L'utilisateur devra implémenter la classe fille. Seule la méthode majRedondance devra être implémentée.
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
	public boolean estAutorisee(Etat e, ArrayList<Element> interdictions){
		int n = interdictions.size();
		int cpt = 0;
		boolean estAutorisee = true;
		while ((cpt < n) && (estAutorisee)){
			estAutorisee = !e.getListe().get(this.indice).equals(interdictions.get(cpt));
			cpt++;
		}
		return estAutorisee;
	}
	
	/**
	 * Cette méthode met à jour la liste des éléments dans la particule et la liste des éléments fréquents après mutation élémentaire
	 * 
	 * On suppose que la mutation est autorisée ("l'ancien" élément ne faisait pas partie des éléments fréquents)
	 * 
	 * @param r
	 * Liste des éléments dans la particule et liste des éléments fréquents
	 * @param e
	 * état concerné
	 */
	public void majRedondance(Probleme p,RedondancesParticuleGeneral r, Etat e){
		//On traite l'ancien élément, celui qui va être remplacé.
		//S'il est présent une seule fois dans la particule, on l'enlève de la liste des éléments de la particule.
		//Sinon, on diminue son nbApparition
		Element old = e.getListe().get(this.indice);
		int placeOfOld = r.getElementsParticule().indexOf(old);
		if (r.getElementsParticule().get(placeOfOld).getNbApparitions() == 1){
			r.getElementsParticule().remove(placeOfOld);
		} else {
			r.getElementsParticule().get(placeOfOld).removeApparition();
		}
		
		//On traite le nouvel élément.
		//On l'ajoute à la liste des éléments de la particule s'il n'est pas présent. Sinon, on augmente son nbApparition
		//On regarde s'il est dans la liste des éléments fréquents. S'il n'y est pas, on l'ajoute en cas de dépassement du seuil.
		
		int placeOfNext = r.getElementsParticule().indexOf(this.elt);
		int apparitionsOfNext = 0;//On ne modifie pas si l'élément va être ajouté à élémentParticule
		if (placeOfNext == -1){
			r.getElementsParticule().add(this.elt);
			r.getElementsParticule().get(r.getElementsParticule().size()-1).setNbApparitions(1);
		} else {
			apparitionsOfNext = r.getElementsParticule().get(placeOfNext).getNbApparitions();
			r.getElementsParticule().get(placeOfNext).addApparition();
		}
		
		placeOfNext = r.getElementsFrequents().indexOf(this.elt);
		if (placeOfNext == -1){
			if (apparitionsOfNext <= p.getFreq()*p.nombreEtat() && apparitionsOfNext+1 > p.getFreq()*p.nombreEtat()){
				r.getElementsFrequents().add(this.elt);
				r.getElementsFrequents().get(r.getElementsFrequents().size()-1).setNbApparitions(apparitionsOfNext+1);
			}
		} else {
			r.getElementsFrequents().get(placeOfNext).addApparition();
		}
	}

}
