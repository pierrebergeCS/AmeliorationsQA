package modele;

/**
 * Un élément est une partie de l'Etat.
 * L'utilisateur fera en sorte que l'élément corresponde, par la suite, à l'objet sur lequel portera l'étude des redondances.
 * Quelques exemples sur TSP:
 * 1) Un élément est une arête. L'Etat est la route. Le recuit étudiera alors la fréquence d'apparition des arêtes dans la particule.
 * 2) Un élément est un doublet d'arête. L'Etat est la route. Le recuit étudiera alors la fréquence d'apparition des doublets d'arêtes dans la particule.
 * Tout cela pour expliquer que l'élément n'est pas nécessairement la composante élémentaire de l'Etat.
 * C'est l'utilisateur qui fixe, comme il le souhaite, la subdivision de son état en plusieurs éléments.
 * @author Pierre
 *
 */
public abstract class Element {
	
	/**
	 * Détermine si l'élément courant est équivalent à l'élément placé en paramètre.
	 * Ex: Dans TSP, on vérifiera si les deux noeuds formant l'arête courante sont identiques aux noeuds de l'arête autre, quelque soit l'ordre (Symmetric TSP)
	 * Ex: Dans SAT, on vérifiera que l'assignation d'une variable booléenne à la variable étudiée est la même dans les deux cas
	 * @param autre
	 * L'élément avec lequel on souhaite comparer
	 * @return
	 * True si les éléments sont identiques, faux sinon
	 */
	public abstract boolean equals(Element autre);

}
