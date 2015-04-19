package mutation;

import java.util.ArrayList;

import modele.Element;
import modele.Etat;
import modele.Probleme;
import modele.RedondancesParticuleGeneral;

/**
 * La mutation �l�mentaire est un "�l�ment" d'une mutation quelconque.
 * Une mutation consiste � modifier des �l�ments de l'�tat que l'on traite.
 * Une mutation �l�mentaire modifie un seul �l�ment de l'�tat.
 * L'utilisateur devra impl�menter la classe fille. Seule la m�thode majRedondance devra �tre impl�ment�e.
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
	 * Remplace l'�l�ment situ� � la i-�me place dans l'�tat e par this.elt
	 * @param e
	 * Etat sur lequel on travaille
	 * @param i
	 * Indice de l'�l�ment remplac�
	 */
	public void faire(Etat e){
		e.getListe().set(this.indice,this.elt);
	}
	
	/**
	 * Determine si une mutation �l�mentaire est autoris�e lorsque les �l�ments contenus dans interdictions sont bloqu�s
	 * @param interdictions
	 * Liste d'�l�ments bloqu�s
	 * @return
	 * True si this.elt est diff�rent des �l�ments contenus dans interdictions, false sinon
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
	 * Cette m�thode met � jour la liste des �l�ments dans la particule et la liste des �l�ments fr�quents apr�s mutation �l�mentaire
	 * 
	 * On suppose que la mutation est autoris�e ("l'ancien" �l�ment ne faisait pas partie des �l�ments fr�quents)
	 * 
	 * @param r
	 * Liste des �l�ments dans la particule et liste des �l�ments fr�quents
	 * @param e
	 * �tat concern�
	 */
	public void majRedondance(Probleme p,RedondancesParticuleGeneral r, Etat e){
		//On traite l'ancien �l�ment, celui qui va �tre remplac�.
		//S'il est pr�sent une seule fois dans la particule, on l'enl�ve de la liste des �l�ments de la particule.
		//Sinon, on diminue son nbApparition
		Element old = e.getListe().get(this.indice);
		int placeOfOld = r.getElementsParticule().indexOf(old);
		if (r.getElementsParticule().get(placeOfOld).getNbApparitions() == 1){
			r.getElementsParticule().remove(placeOfOld);
		} else {
			r.getElementsParticule().get(placeOfOld).removeApparition();
		}
		
		//On traite le nouvel �l�ment.
		//On l'ajoute � la liste des �l�ments de la particule s'il n'est pas pr�sent. Sinon, on augmente son nbApparition
		//On regarde s'il est dans la liste des �l�ments fr�quents. S'il n'y est pas, on l'ajoute en cas de d�passement du seuil.
		
		int placeOfNext = r.getElementsParticule().indexOf(this.elt);
		int apparitionsOfNext = 0;//On ne modifie pas si l'�l�ment va �tre ajout� � �l�mentParticule
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
