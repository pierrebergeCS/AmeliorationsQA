package LHD_1fixed;

import modele.RedondancesParticuleGeneral;

public class RedondancesParticuleLH extends RedondancesParticuleGeneral {
	
	//NE SERT A RIEN pour ce probleme. Il y a peu de croix pour une grille (n d�passe rarement 100...)
	//Inutile de cr�er un tableau � n �l�ments de dimension d pour savoir si une croix est pr�sente dans la particule
	//Le nombre de cases est trop grand pour que cet outil "redondances" soit utile.
	public RedondancesParticuleLH(){
		super(null,null);
	}

}
