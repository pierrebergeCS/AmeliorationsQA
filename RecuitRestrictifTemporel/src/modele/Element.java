package modele;

/**
 * Un �l�ment est une partie de l'Etat.
 * L'utilisateur fera en sorte que l'�l�ment corresponde, par la suite, � l'objet sur lequel portera l'�tude des redondances.
 * 
 * Quelques exemples sur TSP:
 * 1) Un �l�ment est une ar�te. L'Etat est la route. Le recuit �tudiera alors la fr�quence d'apparition des ar�tes dans la particule.
 * 2) Un �l�ment est un doublet d'ar�te. L'Etat est la route. Le recuit �tudiera alors la fr�quence d'apparition des doublets d'ar�tes dans la particule.
 * Tout cela pour expliquer que l'�l�ment n'est pas n�cessairement la composante �l�mentaire de l'Etat.
 * 
 * C'est l'utilisateur qui fixe, comme il le souhaite, la subdivision de son �tat en plusieurs �l�ments.
 * 
 * L'argument nbApparitions sera seulement utilis� pour d�nombrer le nombre d'apparitions de l'�l�ment dans la particule du recuit.
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
	 * D�termine si l'�l�ment courant est �quivalent � l'�l�ment plac� en param�tre.
	 * Ex: Dans TSP, on v�rifiera si les deux noeuds formant l'ar�te courante sont identiques aux noeuds de l'ar�te autre, quelque soit l'ordre (Symmetric TSP)
	 * Ex: Dans SAT, on v�rifiera que l'assignation d'une variable bool�enne � la variable �tudi�e est la m�me dans les deux cas
	 * Ne fais pas intervenir l'argument nbApparitions
	 * @param autre
	 * L'�l�ment avec lequel on souhaite comparer
	 * @return
	 * True si les �l�ments sont identiques, faux sinon
	 */
	public abstract boolean equals(Element autre);

}
