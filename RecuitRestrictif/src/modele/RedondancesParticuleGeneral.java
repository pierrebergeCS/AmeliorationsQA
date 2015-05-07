package modele;

import java.util.ArrayList;

/**
 * 
 * Cette classe sert � d�finir les entit�s qui retiennent en m�moire les �l�ments fr�quents de la particule
 * On doit, tout au long du recuit, �tre capable de mettre � jour ces entit�s(listes, matrices,...).
 * Une classe fille, RedondancesParticuleG�n�ral, est la classe qui permet de traiter les �l�ments fr�quents pour nimporte quel probl�me.
 * 
 * N�anmoins, on veut laisser � l'utilisateur la possibilit� de cr�er sa propre classe fille.
 * La classe g�n�rale utilise souvent des m�thodes co�teuses et des recherches inutiles pour un certain probl�me.
 * Il est donc conseill�, notamment si le cardinal de l'univers est assez petit (n� par exemple), d'impl�menter sa propre classe.
 * 
 * Quelques exemples :
 * 
 * La classe RedondancesParticuleTSP contiendra une matrice de taille n x n, o� m[i][j] donnera l'information sur la fr�quence d'apparitions de l'ar�te (i,j)
 * On pourra, � partir de cette matrice, avoir acc�s aux �l�ments fr�quents de la particule (m[i][j] > f0*P)
 * 
 * La classe RedondancesParticuleSAT contiendra une matrice de taille 2 x n o� n est le nombre de variables.
 * m[0][i] indiquera combien de fois l'assignation {x(i) = true} appara�t.
 * m[1][i] indiquera combien de fois l'assignation {x(i) = false} appara�t.
 *
 * A propos des arguments de cette classe :
 * 
 * Contient deux listes : celle des �l�ments pr�sents dans la particule et celle des �l�ments fr�quents.
 * Ces deux instances sont n�cessaires s'il on veut s'�viter le fait de manipuler des objets de taille card(om�ga), qui peut �tre tr�s grand.
 * L'utilisateur peut utiliser ce type d'objet lorsqu'il initialise son recuit mais, si card(om�ga) n'est pas si grand, on lui conseille de cr�er sa propre classe.
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
	
	//Ajoute l'�l�ment � elementsFrequents
	public void ajouterElement(Element element){
		this.elementsFrequents.add(element);
	}

}
