package vc;

import modele.Element;
import modele.Etat;
import modele.Probleme;
import modele.RedondancesParticuleGeneral;
import mutation.MutationElementaire;

public class MutationVCElementaire extends MutationElementaire {
	int nodeIndex;//Le noeud qu'on enleve ou qu'on ajoute à une couleur.
	//On pourrait le déduire de element et indice, mais c'est pénible.
	
	public MutationVCElementaire(Element elt, int indice) {
		super(elt, indice);
		// TODO Auto-generated constructor stub
	}

	public void setNodeIndex(int k){
		this.nodeIndex = k;
	}
	
	@Override
	public boolean estAutorisee(Probleme p, Etat e, RedondancesParticuleGeneral red,int dureeBlock){
		RedondancesParticuleVC redVC = (RedondancesParticuleVC) red;
		Couleur c = (Couleur) e.getListe().get(this.getIndice());
		if(c.lives()){
			if(c.iterate()){
				return false;
			}
			else{ return true;}
		}
		int taille = c.getTaille();
		for (int i = 0; i < taille; i++){
				if ((redVC.getRedondances()[this.nodeIndex][c.getNoeuds().get(i)] > (p.getFreq()*p.nombreEtat())) && (c.getNoeuds().get(i)!=this.nodeIndex)){
					c.resetBlock(dureeBlock);
					return false;
				}
		}
		return true;
	}
	
	public void majRedondance(Probleme p, RedondancesParticuleGeneral red, Etat e){
		RedondancesParticuleVC redVC = (RedondancesParticuleVC) red;
		Couleur prev = (Couleur) e.getListe().get(this.getIndice());
		Couleur next = (Couleur) this.getElement();
		if (next.getNoeuds().size() < prev.getNoeuds().size()){
			//On enleve
			for (int i: next.getNoeuds()){
				redVC.getRedondances()[this.nodeIndex][i]--;
				redVC.getRedondances()[i][this.nodeIndex]--;
			}
		} else {
			//On ajoute
			for (int i: prev.getNoeuds()){
				redVC.getRedondances()[this.nodeIndex][i]++;
				redVC.getRedondances()[i][this.nodeIndex]++;
			}
		}
	}

}
