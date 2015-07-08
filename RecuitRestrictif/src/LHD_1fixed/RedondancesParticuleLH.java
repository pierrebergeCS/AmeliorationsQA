package LHD_1fixed;

import modele.RedondancesParticuleGeneral;

public class RedondancesParticuleLH extends RedondancesParticuleGeneral {
	
	//NE SERT A RIEN pour ce probleme. Il y a peu de croix pour une grille (n dépasse rarement 100...)
	//Inutile de créer un tableau à n éléments de dimension d pour savoir si une croix est présente dans la particule
	//Le nombre de cases est trop grand pour que cet outil "redondances" soit utile.
	public RedondancesParticuleLH(){
		super(null,null);
	}

}
