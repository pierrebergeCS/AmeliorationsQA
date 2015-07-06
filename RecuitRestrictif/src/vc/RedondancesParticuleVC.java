package vc;

import java.util.ArrayList;

import modele.Element;
import modele.Etat;
import modele.RedondancesParticuleGeneral;

public class RedondancesParticuleVC extends RedondancesParticuleGeneral {
	int[][] redondances;
	//Tableau des noeuds : on regarde si les noeuds i et j ont la même couleur
	//+1 si (i,j) est un couple de même couleur, 0 sinon

	public RedondancesParticuleVC(int[][] redondances){
		super(null,null);
		this.redondances = redondances;
	}
	public RedondancesParticuleVC(ArrayList<Element> elementsParticule,
			ArrayList<Element> elementsFrequents) {
		super(elementsParticule, elementsFrequents);
		// TODO Auto-generated constructor stub
	}
	
	public int[][] getRedondances(){
		return this.redondances;
	}

	public RedondancesParticuleVC(ParticuleVC p){
		super(null,null);
		int nbNoeuds = p.getGraphe().getConnexions().length;
		int[][] tab = new int[nbNoeuds][nbNoeuds];
		for (Etat e: p.getEtat()){
			Coloriage c = (Coloriage) e;
			for (int k = 0; k < nbNoeuds; k++){
				//On regarde si les noeuds k et i ont la même couleur ou pas. En cas de divergence, on incrémente dans le tab
				int colorIndex = c.getNoeuds().get(k).getCouleur();
				for (int i: ((Couleur)c.getListe().get(colorIndex)).getNoeuds()){
					tab[k][i]++;
				}
			}
		}
		this.redondances = tab;
	}
	
	public void printTab(){
		int[][] m = this.redondances;
		int n = m.length;
		for (int i = 0; i < n; i++){
			System.out.println(" ");
			for (int j = 0; j < n; j++){
				System.out.print(m[i][j] + " ");
			}
		}
	}

}
