package modele;

import java.util.ArrayList;

/**
 * 
 * Cette classe sert à définir les entités qui retiennent en mémoire les éléments fréquents de la particule
 * On doit, tout au long du recuit, être capable de mettre à jour ces entités(listes, matrices,...).
 * Une classe fille, RedondancesParticuleGénéral, est la classe qui permet de traiter les éléments fréquents pour nimporte quel problème.
 * 
 * Néanmoins, on veut laisser à l'utilisateur la possibilité de créer sa propre classe fille.
 * La classe générale utilise souvent des méthodes coûteuses et des recherches inutiles pour un certain problème.
 * Il est donc conseillé, notamment si le cardinal de l'univers est assez petit (n² par exemple), d'implémenter sa propre classe.
 * 
 * Quelques exemples :
 * 
 * La classe RedondancesParticuleTSP contiendra une matrice de taille n x n, où m[i][j] donnera l'information sur la fréquence d'apparitions de l'arête (i,j)
 * On pourra, à partir de cette matrice, avoir accès aux éléments fréquents de la particule (m[i][j] > f0*P)
 * 
 * La classe RedondancesParticuleSAT contiendra une matrice de taille 2 x n où n est le nombre de variables.
 * m[0][i] indiquera combien de fois l'assignation {x(i) = true} apparaît.
 * m[1][i] indiquera combien de fois l'assignation {x(i) = false} apparaît.
 *
 * A propos des arguments de cette classe :
 * 
 * Contient deux listes : celle des éléments présents dans la particule et celle des éléments fréquents.
 * Ces deux instances sont nécessaires s'il on veut s'éviter le fait de manipuler des objets de taille card(oméga), qui peut être très grand.
 * L'utilisateur peut utiliser ce type d'objet lorsqu'il initialise son recuit mais, si card(oméga) n'est pas si grand, on lui conseille de créer sa propre classe.
 * 
 * @author La Groupie du Pianiste
 *
 */
public class RedondancesParticuleGeneral {
	
	ArrayList<Element> elementsParticule;
	protected ArrayList<Element> elementsFrequents;
	
	public RedondancesParticuleGeneral(ArrayList<Element> elementsParticule, ArrayList<Element> elementsFrequents){
		this.elementsParticule = elementsParticule;
		this.elementsFrequents = elementsFrequents;
	}
	
	public ArrayList<Element> getElementsParticule(){
		return this.elementsParticule;
	}
	
	public ArrayList<Element> getElementsFrequents(){
		return this.elementsFrequents;
	}
	
	//Ajoute l'élément à elementsFrequents
	public void ajouterElement(Element element){
		this.elementsFrequents.add(element);
	}

}
