package tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import modele.Element;
import modele.Etat;
import mutation.IMutation;

public class Routage extends Etat {

	
	Graphe g;
	int[][] ising;
	
	public Routage (Graphe g){
		this.g = g;
		this.setListe(routeInitiale());
		this.updateIsing();
	}
	public void setIsing(int[][] ising){
		this.ising =ising ;
	}

	public int[][] getIsing(){
		return this.ising;
	}


	public Routage (Graphe g, ArrayList<Element> liste){
		this.g=g;
		this.setListe(liste);
		this.updateIsing();
	}
	
	public ArrayList<Element> routeInitiale() {
		int n = this.g.nombreDeNoeuds();
		ArrayList<Integer> liste = new ArrayList<Integer>();
		for (int index = 0; index < n; index++) {
			liste.add(new Integer(index));
		}
		// Réorganise aléatoirement l'ordre de visite
		Collections.shuffle(liste);
		
		ArrayList<Element> listeElements = new ArrayList<Element>(n);
		for (int cpt = 0; cpt < (n-1); cpt++){
			listeElements.add(new Arete(liste.get(cpt), liste.get(cpt+1)));
		}
		listeElements.add(new Arete(liste.get(n-1),liste.get(0)));
		return listeElements;
	}
	
	
	public Routage clone()
	{
		int n = this.g.nombreDeNoeuds();
		ArrayList<Element> l = new ArrayList<Element>(n);
		for (int index = 0; index < n; index++){
			Arete a = (Arete) this.getListe().get(index);
			l.set(index, a.clone());
		}
		Routage clone = new Routage(this.g,l);
		return clone;
	}

	
	public Graphe getGraphe(){
		return this.g;
	}
	
	public int getNextIndex(int index){
		if (index==(this.g.nombreDeNoeuds()-1)) {
			return 0;
		} else {
			return (index+1);
		}
	}
	public int getPreviousIndex(int index){
		if (index==0) {
			return (this.g.nombreDeNoeuds() - 1);
		} else {
			return (index-1);
		}
	}
	public String toString() {
		int n = this.g.nombreDeNoeuds();
		String s = "";
		for (int index = 0; index < n; index++){
			s += this.getListe().get(index).toString() + " ";
		}
		return s;
	}

	public double getEnergie(){
		double cpt=0.0;
		int L = this.g.nombreDeNoeuds();
		for(int i=0;i<L;i++){
			Arete a = (Arete) this.getListe().get(i);
			cpt+=a.longueur(this.g);
		}
		return cpt;
	}


	//Rend la representation d'Ising du routage

	//Renvoie la valeur d'Ising pour deux noeuds fournis
	public int valueIsing(int i, int j){
		if (i<j) return this.ising[i][j];
		if (j<i) return this.ising[j][i];
		return 0;
	}

	//Cette fonction met à jour la matrice d'Ising en connectant les noeuds i et j. Condition : i != j
	public void connect(int i, int j){
		if (i<j) this.ising[i][j] = 1;
		if (j<i) this.ising[j][i] = 1;
	}

	//Fonction disconnect
	public void disconnect(int i, int j){ 
		if (i<j) this.ising[i][j] = -1;
		if (j<i) this.ising[j][i] = -1;
	}

	//Mise à jour de la matrice d'Ising et renvoi de cette matrice
	public int[][] updateIsing(){
		int n = this.g.nombreDeNoeuds();
		ArrayList<Element> r= this.getListe();
		int now;
		int next;
		int[][] m = new int[n][n];

		//On passe à 1 les noeuds lies entre eux
		for(int i =0; i<n;i++){
			Arete a = (Arete) r.get(i);
			now = a.getNoeud1();
			next = a.getNoeud2();
			if (now<next){
				m[now][next]=1;
			} else {
				m[next][now]=1;
			}
		}
		//S'ils ne sont pas lies dans la route, on met -1
		for (int k=0; k<n-1; k++){
			for (int l=k+1; l<n; l++){
				if (m[k][l] != 1) m[k][l] = -1;
			}
		}
		this.ising = m;
		return m;

	}

	//Calcule le produit spinique entre deux matrices d'Ising
	public int distanceIsing(Etat e){
		Routage autre = (Routage) e;
		int compteurspinique = 0;
		int[][] Mi = this.getIsing();
		int[][] Mj = autre.getIsing();
		for(int k =0;k<Mi.length-1;k++){
			for(int l =k+1;l<Mi.length;l++){
				compteurspinique+=Mi[k][l]*Mj[k][l];
			}
		}
		return compteurspinique;
	}

	//Affiche le pourcentage de similarite entre 2 routes. Si on renvoie 100, elles sont complètement similaires. Si on renvoie 0, elles sont complètement differentes.
	public int pourcentageSimilarite(Routage autre){
		int n = this.g.nombreDeNoeuds();
		int rapprochement = this.distanceIsing(autre);
		int limitesup = n*(n-1)/2;
		int limiteinf = n*(n-9)/2;
		int differenceTotale = limitesup - limiteinf;
		int difference = rapprochement - limiteinf;
		return ((difference*100)/differenceTotale);
	}

	//Affiche la matrice d'Ising de la route. Utile pour vérifications
	public void afficheIsing(){
		int[][] M = this.getIsing();
		for(int k =0;k<M.length;k++){
			for(int l =0;l<M.length;l++){
				System.out.print(M[k][l] + " , ");
			}
			System.out.println("");
		}
	}

	public int getTaille(){
		return this.g.nombreDeNoeuds();
	}



}
