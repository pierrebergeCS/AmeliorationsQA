package mutation;

import java.util.ArrayList;

import modele.Element;
import modele.Etat;

/**
 * La mutation �l�mentaire est un "�l�ment" d'une mutation quelconque.
 * Une mutation consiste � modifier des �l�ments de l'�tat que l'on traite.
 * Une mutation �l�mentaire modifie un seul �l�ment de l'�tat.
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
