package modele;

import java.util.ArrayList;
/**
 * Un etat a par défaut ses voisins definis a NULL, il doit etre modifié par l'utilisateur.
 * L'utilisateur se devra d'instancier Etat en une classe correspondant à son probleme.
 * Les méthodes de l'Etat de l'utilisateur seront celles qui contiennent la consigne "L'utilisateur devra..."
 * 
 */
public abstract class Etat {
	
	
	/**
	 * Clone l'etat
	 * L'utilisateur devra implémenter cette méthode de manière à cloner son Etat.
	 * Cette méthode lui servira certainement pour la méthode Probleme.clone()
	 */
	public abstract Etat clone();
	
	/**
	 * L'utilisateur devra implémenter cette méthode
	 * On calcule ici l'énergie potentielle de l'état en fonction des éléments placés en arguments.
	 * @return
	 * L'energie potentielle de l'etat
	 */
	public abstract double getEnergie();

}
