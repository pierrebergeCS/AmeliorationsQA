package vc;

import java.util.ArrayList;

import modele.Element;
import modele.Etat;

public class Coloriage extends Etat {
	Graphe g;
	ArrayList<Noeud> listeNoeuds;// On la laissera triée : le noeud 0 est à la place 0...
	
	public Coloriage(Graphe g,ArrayList<Noeud> listeNoeuds, ArrayList<Element> l){
		this.g = g;
		this.listeNoeuds = listeNoeuds;
		this.setListe(l);
	}

	public Coloriage(Graphe g, int nbColors){
		this.g = g;
		
		//On crée la liste des couleurs
		
		ArrayList<Element> colors = new ArrayList<Element>();
		for (int k = 0; k < nbColors; k++){
			colors.add(new Couleur(k));
		}
		
		//On met chaque noeud dans une couleur de manière aléatoire
		
		int nbNoeuds = this.g.getConnexions().length;
		ArrayList<Noeud> listeNoeuds = new ArrayList<Noeud>();
		for (int i = 0; i < nbNoeuds; i++){
			int rand = (int) Math.random()*nbColors;
			listeNoeuds.add(new Noeud(i,rand));
			((Couleur)colors.get(rand)).getNoeuds().add(i);
		}
	}
	
	public Graphe getGraphe(){
		return this.g;
	}
	
	@Override
	public Etat clone() {
		Graphe g2 = this.g;
		ArrayList<Element> l2 = new ArrayList<Element>();
		int nbElts = this.getListe().size();
		for (int i = 0; i < nbElts; i++){
			Couleur c = (Couleur) this.getListe().get(i);
			l2.add(c.clone());
		}
		ArrayList<Noeud> nodes = new ArrayList<Noeud>();
		for (int k = 0; k < this.listeNoeuds.size(); k++){
			nodes.add(this.listeNoeuds.get(k).clone());
		}
		return new Coloriage(g2,nodes,l2);
	}

	@Override
	public double getEnergie() {
		double cpt = 0;
		int n = this.listeNoeuds.size();
		for (int i = 0; i < n; i++){
			for (int j : this.g.getConnexions()[i]){
				Noeud n1 = this.listeNoeuds.get(i);
				Noeud n2 = this.listeNoeuds.get(j);
				if (n1.getCouleur() == n2.getCouleur()) cpt++;
			}
		}
		return cpt/2;
	}

	@Override
	public int distanceIsing(Etat e) {
		double cpt = 0;
		Coloriage autre = (Coloriage) e;
		int n = this.listeNoeuds.size();
		for (int i = 0; i < n; i++){
			for (int j: this.g.getConnexions()[i]){
				Noeud n1 = this.listeNoeuds.get(i);
				Noeud n2 = this.listeNoeuds.get(j);
				Noeud othern1 = autre.listeNoeuds.get(i);
				Noeud othern2 = autre.listeNoeuds.get(j);
				if ((n1.getCouleur() == n2.getCouleur()) && (othern1.getCouleur() == othern2.getCouleur())) cpt++;
				if ((n1.getCouleur() != n2.getCouleur()) && (othern1.getCouleur() != othern2.getCouleur())) cpt++;
				if ((n1.getCouleur() != n2.getCouleur()) && (othern1.getCouleur() == othern2.getCouleur())) cpt--;
				if ((n1.getCouleur() == n2.getCouleur()) && (othern1.getCouleur() != othern2.getCouleur())) cpt--;
			}
		}
		return ((int) cpt/2);
	}

}
