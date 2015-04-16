package mutation;
import java.util.ArrayList;

import modele.Element;
import modele.Etat;
import modele.Probleme;

/**
 * Une mutation est une succession de mutations élémentaires
 * La classe fille de l'utilisateur contiendra un constructeur qui créera la listeMutations en fonction de son Problème, de l'état traité,...
 * Le constructeur sera essentiel car c'est lui qui déterminera comment seront réalisées les mutations.
 * L'utilisateur devra donner une valeur à la borneEc correspondant au type de mutation qu'il utilise.
 * borneEc est la valeur absolue de la variation maximale d'énergie cinétique lors d'une mutation. Cela permet d'accélerer le recuit avec RecuitAccelere
 * @author Pierre
 *
 */
public abstract class IMutation {
	ArrayList<MutationElementaire> listeMutations;
	static double borneEc;
	
	public double getBorne(){
		return borneEc;
	}
	
	/**
	 * C'est la méthode qui effectue toutes les mutations élémentaires de la liste
	 * @param e
	 * Etat de la particule sur lequel on effectue les mutations
	 */
public void faire(Etat e){
	int n = this.listeMutations.size();
	for (int i = 0; i < n; i++){
		this.listeMutations.get(i).faire(e);
	}
}
/**
 * Calcule la différence d'energie potentielle suite à la mutation sur l'état e
 * L'utilisateur devra implémenter cette méthode dans la classe fille adaptée
 * Cette méthode ne modifie ni l'état ni le problème : elle indique juste la modification d'énergie potentielle en cas de mutation
 * @param p
 * Problème sur lequel on mute
 * @param e
 * Etat de la particule sur lequel la mutation a lieu.
 * @return
 * Différence d'énergie potentielle après mutation
 */
public abstract double calculerdeltaEp(Probleme p, Etat e);
/**
 * Calcule la différence d'energie cinétique suite à la mutation sur l'état e. On ne tient pas compte de la pondération dans ce calcul. En fait, on renvoie deltaEc/J.
 * L'utilisateur devra implémenter cette méthode dans la classe fille adaptée
 * Cette méthode ne modifie ni l'état ni le problème : elle indique juste la modification d'énergie cinétique (sans J) en cas de mutation
 * @param p
 * Problème sur lequel on mute
 * @param e
 * Etat de la particule sur lequel la mutation a lieu.
 * @return
 * Différence d'énergie cinétique après mutation
 */
public abstract double calculerdeltaSpins(Probleme p, Etat e);


/**
 * Transforme la mutation courante en une autre mutation, de même type(même type d'objet) mais différente(arguments différents)
 * C'est en fait un générateur de mutation aléatoire,créant une mutation indépendante de la mutation traitée mais de même type : c'est un constructeur en fait
 * L'utilisateur devra implémenter cette méthode dans la classe fille adaptée. Elle sera équivalente au constructeur a priori.
 */
public abstract void maj(Probleme p, Etat e);

public boolean estAutorisee(Etat e, ArrayList<Element> interdictions){
	int nbMutations = this.listeMutations.size();
	boolean estAutorisee = true;
	int cpt = 0;
	while ((cpt < nbMutations) && (estAutorisee)){
		estAutorisee = this.listeMutations.get(cpt).estAutorisee(e,interdictions);
		cpt++;
	}
	return estAutorisee;
}
}
