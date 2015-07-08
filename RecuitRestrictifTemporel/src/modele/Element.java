package modele;

/**
 * Un élément est une partie de l'Etat.
 * L'utilisateur fera en sorte que l'élément corresponde, par la suite, à l'objet sur lequel portera l'étude des redondances.
 * 
 * Quelques exemples sur TSP:
 * 1) Un élément est une arête. L'Etat est la route. Le recuit étudiera alors la fréquence d'apparition des arêtes dans la particule.
 * 2) Un élément est un doublet d'arête. L'Etat est la route. Le recuit étudiera alors la fréquence d'apparition des doublets d'arêtes dans la particule.
 * Tout cela pour expliquer que l'élément n'est pas nécessairement la composante élémentaire de l'Etat.
 * 
 * C'est l'utilisateur qui fixe, comme il le souhaite, la subdivision de son état en plusieurs éléments.
 * 
 * L'argument nbApparitions sera seulement utilisé pour dénombrer le nombre d'apparitions de l'élément dans la particule du recuit.
 * @author Pierre
 *
 */
public abstract class Element {
	int timetolive=0;
	int timetoblock=0;
	int nbApparitions = 0;
	
	public void setNbApparitions(int k){
		this.nbApparitions = k;
	}
	
	public int getNbApparitions(){
		return this.nbApparitions;
	}
	public int getttl(){
		return this.timetolive;
	}
	public int getTimetoBlock(){
		return this.timetoblock;
	}
	
	public void resetBlock(int cpt){
		this.timetoblock=cpt;
		this.timetolive=6*cpt;
	}
	
	public boolean lives(){
		if(timetolive>0){
			this.timetolive--;
			return true;
			}
			else{ return false;}
	}
	
	public boolean iterate(){
		if(timetoblock>0){
		this.timetoblock--;
		return true;
		}
		else{ return false;}
	}
	
	public void addApparition(){
		this.nbApparitions++;
	}
	
	public void removeApparition(){
		this.nbApparitions--;
	}
	
	/**
	 * Détermine si l'élément courant est équivalent à l'élément placé en paramètre.
	 * Ex: Dans TSP, on vérifiera si les deux noeuds formant l'arête courante sont identiques aux noeuds de l'arête autre, quelque soit l'ordre (Symmetric TSP)
	 * Ex: Dans SAT, on vérifiera que l'assignation d'une variable booléenne à la variable étudiée est la même dans les deux cas
	 * Ne fais pas intervenir l'argument nbApparitions
	 * @param autre
	 * L'élément avec lequel on souhaite comparer
	 * @return
	 * True si les éléments sont identiques, faux sinon
	 */
	public abstract boolean equals(Element autre);

}
