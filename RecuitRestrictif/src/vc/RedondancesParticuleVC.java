package vc;

import java.util.ArrayList;

import modele.Element;
import modele.Etat;
import modele.RedondancesParticuleGeneral;

public class RedondancesParticuleVC extends RedondancesParticuleGeneral {
	int[][] redondances;

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
				int colorIndex = c.getNoeuds().get(k).getCouleur();
				for (int i: ((Couleur)c.getListe().get(colorIndex)).getNoeuds()){
					tab[k][i]++;
					tab[i][k]++;
				}
			}
		}
		this.redondances = tab;
	}

}
