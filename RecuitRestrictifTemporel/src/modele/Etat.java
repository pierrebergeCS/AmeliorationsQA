package modele;

import java.util.ArrayList;
/**
 * Un etat a par d�faut ses voisins definis a NULL, il doit etre modifi� par l'utilisateur.
 * L'utilisateur se devra d'instancier Etat en une classe correspondant � son probleme.
 * Les m�thodes de l'Etat de l'utilisateur seront celles qui contiennent la consigne "L'utilisateur devra..."
 * 
 */
public abstract class Etat {
	
	Etat previous;
	Etat next;
	public ArrayList<Element> listeElements;
	
	
	public ArrayList<Element> getListe(){
		return this.listeElements;
	}
	
	public void setListe(ArrayList<Element> liste){
		this.listeElements = liste;
	}

	public void setprevious(Etat etat) {
		this.previous=etat;
		
	}
	
	public void setnext(Etat etat) {
		this.next=etat;		
	}
	/**
	 * Clone l'etat
	 * L'utilisateur devra impl�menter cette m�thode de mani�re � cloner son Etat.
	 * Cette m�thode lui servira certainement pour la m�thode Probleme.clone()
	 */
	public abstract Etat clone();
	/**
	 * @return
	 * L'etat suivant dans la chaine liant les �tats
	 */
	
	public Etat getNext(){
		return this.next;
	}
	/**
	 * @return
	 * L'etat pr�c�dent dans la chaine liant les �tats
	 */
	public Etat getPrevious(){
		return this.previous;
	}
	/**
	 * L'utilisateur devra impl�menter cette m�thode
	 * On calcule ici l'�nergie potentielle de l'�tat en fonction des �l�ments plac�s en arguments.
	 * @return
	 * L'energie potentielle de l'etat
	 */
	public abstract double getEnergie();
	
	/**
	 * L'utilisateur devra implementer la methode appropri�e a son "type" d'etat
	 * Elle renvoie la somme des produits Vetat(i)*Ve(i)
	 * retourne 0 par d�faut
	 * @param e
	 * Etat a calculer
	 * @return
	 * l'entier de la somme de spin
	 */
	public abstract int distanceIsing(Etat e);

}
