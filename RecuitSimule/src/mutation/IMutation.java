package mutation;
import java.util.ArrayList;

import modele.Etat;


/**
 * Une mutation est une succession de mutations �l�mentaires
 * La classe fille de l'utilisateur contiendra un constructeur qui cr�era la listeMutations en fonction de son Probl�me, de l'�tat trait�,...
 * Le constructeur sera essentiel car c'est lui qui d�terminera comment seront r�alis�es les mutations.
 * L'utilisateur devra donner une valeur � la borneEc correspondant au type de mutation qu'il utilise.
 * borneEc est la valeur absolue de la variation maximale d'�nergie cin�tique lors d'une mutation. Cela permet d'acc�lerer le recuit avec RecuitAccelere
 * @author Pierre
 *
 */
public abstract class IMutation {


	
	/**
	 * C'est la m�thode qui effectue toutes les mutations �l�mentaires de la liste
	 * L'utilisateur pourra overrider cette m�thode s'il le souhaite
	 * @param e
	 * Etat de la particule sur lequel on effectue les mutations
	 */
public abstract void faire(Etat e);
/**
 * Calcule la diff�rence d'energie potentielle suite � la mutation sur l'�tat e
 * L'utilisateur devra impl�menter cette m�thode dans la classe fille adapt�e
 * Cette m�thode ne modifie ni l'�tat ni le probl�me : elle indique juste la modification d'�nergie potentielle en cas de mutation
 * @param p
 * Probl�me sur lequel on mute
 * @param e
 * Etat de la particule sur lequel la mutation a lieu.
 * @return
 * Diff�rence d'�nergie potentielle apr�s mutation
 */
public abstract double calculerdeltaEp(Etat e);
/**
 * Calcule la diff�rence d'energie cin�tique suite � la mutation sur l'�tat e. On ne tient pas compte de la pond�ration dans ce calcul. En fait, on renvoie deltaEc/J.
 * L'utilisateur devra impl�menter cette m�thode dans la classe fille adapt�e
 * Cette m�thode ne modifie ni l'�tat ni le probl�me : elle indique juste la modification d'�nergie cin�tique (sans J) en cas de mutation
 * @param p
 * Probl�me sur lequel on mute
 * @param e
 * Etat de la particule sur lequel la mutation a lieu.
 * @return
 * Diff�rence d'�nergie cin�tique apr�s mutation
 */

public abstract void maj(Etat e);
}
