package vc;

import java.util.ArrayList;
import java.util.LinkedList;

import modele.Etat;

import mutation.IMutation;
import vc.MutationVCElementaire;

public class MutationVC extends IMutation {
	int nodeIndex;//Noeud concerné
	int newColorIndex;//Nouvelle couleur
	ArrayList<MutationVCElementaire> liste;
	
	public MutationVC(Coloriage c){
		Graphe g = c.getGraphe();
		int nbNoeuds = g.getConnexions().length;
		int nbCouleurs = c.getListe().size();
		int randNode = (int) (Math.random()*nbNoeuds);
		int currentColor = c.getNoeuds().get(randNode).getCouleur();
		int randColor = -1;
		while((randColor < 0) || (randColor == currentColor)){
			randColor = (int) (Math.random()*nbCouleurs);
		}
		this.nodeIndex = randNode;
		this.newColorIndex = randColor;
		
		ArrayList<MutationVCElementaire> liste = new ArrayList<MutationVCElementaire>();
		
		Couleur c1 =  ((Couleur)c.getListe().get(currentColor)).clone();
		c1.getNoeuds().remove((Integer) this.nodeIndex);
		MutationVCElementaire m1 = new MutationVCElementaire(c1,currentColor);
		m1.setNodeIndex(this.nodeIndex);
		
		Couleur c2 =  ((Couleur)c.getListe().get(this.newColorIndex)).clone();
		c2.getNoeuds().add((Integer) this.nodeIndex);
		MutationVCElementaire m2 = new MutationVCElementaire(c.getListe().get(this.newColorIndex),this.newColorIndex);
		m2.setNodeIndex(this.nodeIndex);
		
		liste.add(m1);
		liste.add(m2);
		this.liste = liste;// A retenir: d'abord on enleve le noeud(m1) puis on l'ajoute ailleurs(m2)
	}
	
	public ArrayList<MutationVCElementaire> getListe(){
		return this.liste;
	}
	
	public int getNodeIndex(){
		return this.nodeIndex;
	}
	
	public int getColorIndex(){
		return this.newColorIndex;
	}

	@Override
	public double calculerdeltaEp(Etat e) {
		double cpt = 0;
		Coloriage c = (Coloriage) e;
		LinkedList<Integer> connectedNodes = c.getGraphe().getConnexions()[this.nodeIndex];
		int currentColor = c.getNoeuds().get(this.nodeIndex).getCouleur();
		// Si les noeuds connectés avec le noeud courant ont la future couleur du noeud courant, on incrémente (+1)
		// Si les noeuds connectés avec le noeud courant ont la précédente couleur du noeud courant, on décrémente (-1)
		for (int i: connectedNodes){
			if (c.getNoeuds().get(i).getCouleur() == currentColor) cpt--;
			if (c.getNoeuds().get(i).getCouleur() == this.newColorIndex) cpt++;
		}
		return cpt;
	}
	

	
		
	@Override
	public void faire(Etat e){
		int n = this.liste.size();
		for (int i = 0; i < n; i++){
			this.liste.get(i).faire((Coloriage)e);
		}
		Coloriage c = (Coloriage) e;
		c.getNoeuds().get(this.nodeIndex).setCouleur(this.newColorIndex);
	}
	
	@Override
	public void maj(Etat e) {
		Coloriage c = (Coloriage) e;
		Graphe g = c.getGraphe();
		int nbNoeuds = g.getConnexions().length;
		int nbCouleurs = c.getListe().size();
		int randNode = (int) (Math.random()*nbNoeuds);
		int currentColor = c.getNoeuds().get(randNode).getCouleur();
		int randColor = -1;
		while((randColor < 0) || (randColor == currentColor)){
			randColor = (int) (Math.random()*nbCouleurs);
		}
		this.nodeIndex = randNode;
		this.newColorIndex = randColor;
		
		ArrayList<MutationVCElementaire> liste = new ArrayList<MutationVCElementaire>();
		
		Couleur c1 =  ((Couleur)c.getListe().get(currentColor)).clone();
		c1.getNoeuds().remove((Integer) this.nodeIndex);
		MutationVCElementaire m1 = new MutationVCElementaire(c1,currentColor);
		m1.setNodeIndex(this.nodeIndex);
		
		Couleur c2 =  ((Couleur)c.getListe().get(this.newColorIndex)).clone();
		c2.getNoeuds().add((Integer) this.nodeIndex);
		MutationVCElementaire m2 = new MutationVCElementaire(c2,this.newColorIndex);
		m2.setNodeIndex(this.nodeIndex);
		
		liste.add(m1);
		liste.add(m2);
		this.liste = liste;
	}

}
