package vc;

import java.util.ArrayList;

import modele.Etat;

public class Coloriage extends Etat {
	Graphe g;
	ArrayList<Noeud> listeNoeuds;// On la laissera triée : le noeud 0 est à la place 0...
	ArrayList<Couleur> listeCouleurs;
	
	public Coloriage(Graphe g,ArrayList<Noeud> listeNoeuds, ArrayList<Couleur> l){
		this.g = g;
		this.listeNoeuds = listeNoeuds;
		this.listeCouleurs = l;
		this.majEnergie();
	}

	public Coloriage(Graphe g, int nbColors){
		this.g = g;
		
		//On crée la liste des couleurs
		
		ArrayList<Couleur> colors = new ArrayList<Couleur>();
		for (int k = 0; k < nbColors; k++){
			colors.add(new Couleur(k));
		}
		this.listeCouleurs = colors;
		
		//On met chaque noeud dans une couleur de manière aléatoire
		
		int nbNoeuds = this.g.getConnexions().length;
		ArrayList<Noeud> listeNoeuds = new ArrayList<Noeud>();
		for (int i = 0; i < nbNoeuds; i++){
			int rand = (int) (Math.random()*nbColors);
			listeNoeuds.add(new Noeud(i,rand));
			this.getListe().get(rand).getNoeuds().add(i);
		}
		this.listeNoeuds = listeNoeuds;
		this.majEnergie();
	}
	
	public ArrayList<Couleur> getListe(){
		return this.listeCouleurs;
	}
	
	public Graphe getGraphe(){
		return this.g;
	}
	
	public ArrayList<Noeud> getNoeuds(){
		return this.listeNoeuds;
	}
	
	@Override
	public Etat clone() {
		Graphe g2 = this.g;
		ArrayList<Couleur> l2 = new ArrayList<Couleur>();
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
	public double majEnergie() {
		double cpt = 0;
		int n = this.listeNoeuds.size();
		for (int i = 0; i < n; i++){
			for (int j : this.g.getConnexions()[i]){
				Noeud n1 = this.listeNoeuds.get(i);
				Noeud n2 = this.listeNoeuds.get(j);
				if (n1.getCouleur() == n2.getCouleur()) cpt++;
			}
		}
		this.setEnergie(cpt/2);
		return this.getEnergie();
	}

	public void maj(){
		int nbColors = this.listeCouleurs.size();
		
		//On crée la liste des couleurs
		
		ArrayList<Couleur> colors = new ArrayList<Couleur>();
		for (int k = 0; k < nbColors; k++){
			colors.add(new Couleur(k));
		}
		this.listeCouleurs = colors;
		
		//On met chaque noeud dans une couleur de manière aléatoire
		
		int nbNoeuds = this.g.getConnexions().length;
		ArrayList<Noeud> listeNoeuds = new ArrayList<Noeud>();
		for (int i = 0; i < nbNoeuds; i++){
			int rand = (int) (Math.random()*nbColors);
			listeNoeuds.add(new Noeud(i,rand));
			this.getListe().get(rand).getNoeuds().add(i);
		}
		this.listeNoeuds = listeNoeuds;
		this.majEnergie();
	}
	
	@Override
	public double getResultat() {
		return this.getEnergie();
	}
}
