package modele;

import java.util.ArrayList;
/**
 * Un etat a par défaut ses voisins definis a NULL, il doit etre modifié par l'utilisateur.
 * L'utilisateur se devra d'instancier Etat en une classe correspondant à son probleme.
 * Les méthodes de l'Etat de l'utilisateur seront celles qui contiennent la consigne "L'utilisateur devra..."
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
	 * L'utilisateur devra implémenter cette méthode de manière à cloner son Etat.
	 * Cette méthode lui servira certainement pour la méthode Probleme.clone()
	 */
	public abstract Etat clone();
	/**
	 * @return
	 * L'etat suivant dans la chaine liant les états
	 */
	
	public Etat getNext(){
		return this.next;
	}
	/**
	 * @return
	 * L'etat précédent dans la chaine liant les états
	 */
	public Etat getPrevious(){
		return this.previous;
	}
	/**
	 * L'utilisateur devra implémenter cette méthode
	 * On calcule ici l'énergie potentielle de l'état en fonction des éléments placés en arguments.
	 * @return
	 * L'energie potentielle de l'etat
	 */
	public abstract double getEnergie();
	
	/**
	 * L'utilisateur devra implementer la methode appropriée a son "type" d'etat
	 * Elle renvoie la somme des produits Vetat(i)*Ve(i)
	 * retourne 0 par défaut
	 * @param e
	 * Etat a calculer
	 * @return
	 * l'entier de la somme de spin
	 */
	public abstract int distanceIsing(Etat e);

}
