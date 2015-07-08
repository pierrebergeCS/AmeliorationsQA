package LHD_1fixed;

import java.util.ArrayList;

import modele.Element;
import modele.Etat;

public class Grille extends Etat {
	FonctionEval f;
	int taille;
	ArrayList<Integer> criticalPoints = new ArrayList<Integer>(2);//Position points critiques
	int dmin ;
	int[][] matriceDistances;

	public Grille(FonctionEval f, ArrayList<Element> liste, int n){
		this.f = f;
		this.listeElements = liste;
		this.taille = n;
		findCriticalPoints();
		this.majEnergie();
	}
	
	public Grille(FonctionEval f, int n, int dim, int nbCroixBloquees){
		this.f = f;
		ArrayList<Element> liste = new ArrayList<Element>();
		
		//On genere les zones libres, cad ne contenant pas de croix. Pour l'instant, il s'agit de toutes les lignes, colonnes,...
		ArrayList<ArrayList<Integer>> zonesLibres = new ArrayList<ArrayList<Integer>>(dim);
		for(int k = 0; k < dim; k++){
			zonesLibres.add(new ArrayList<Integer>(n));
		}
		for (int j = 0; j < dim; j++){
			for (int i = 0; i < n; i++){
				zonesLibres.get(j).add(i);
			}
		}
		
		//On génère chaque croix à partir des zones libres.
		for(int i = 0; i < n; i++){
			int[] tab = new int[dim];
			for (int j = 0; j < dim; j++){
				int index = (int) (Math.random()*zonesLibres.get(j).size());
				tab[j] = zonesLibres.get(j).get(index);
				zonesLibres.get(j).remove(index);
			}
			if (i<nbCroixBloquees){
				liste.add(new Croix(tab,true));//croix fixée
			} else {
				liste.add(new Croix(tab,false));
			}
		}
		
		this.listeElements = liste;
		this.taille = n;
		findCriticalPoints();
		this.majEnergie();
	}
	
	public Grille(FonctionEval f, int n, int dim, Croix croixFixee){
		this.f = f;
		ArrayList<Element> liste = new ArrayList<Element>();
		
		//On genere les zones libres, cad ne contenant pas de croix. Pour l'instant, il s'agit de toutes les lignes, colonnes,...
		ArrayList<ArrayList<Integer>> zonesLibres = new ArrayList<ArrayList<Integer>>(dim);
		for(int k = 0; k < dim; k++){
			zonesLibres.add(new ArrayList<Integer>(n));
		}
		for (int j = 0; j < dim; j++){
			for (int i = 0; i < n; i++){
				zonesLibres.get(j).add(i);
			}
		}
		
		liste.add(new Croix(croixFixee.getCoord(),true));
		for (int j = 0; j < dim; j++){
			zonesLibres.get(j).remove((Integer)croixFixee.getCoord()[j]);
		}
		
		//On génère chaque croix à partir des zones libres.
		for(int i = 1; i < n; i++){
			int[] tab = new int[dim];
			for (int j = 0; j < dim; j++){
				int index = (int) (Math.random()*zonesLibres.get(j).size());
				tab[j] = zonesLibres.get(j).get(index);
				zonesLibres.get(j).remove(index);
			}
				liste.add(new Croix(tab,false));
		}
		
		this.listeElements = liste;
		this.taille = n;
		findCriticalPoints();
		this.majEnergie();
	}
	
	public int getTaille(){
		return this.taille;
	}
	
	public FonctionEval getEval(){
		return this.f;
	}
	
	public ArrayList<Integer> getCriticalPoints(){
		return this.criticalPoints;
	}
	
	public int getdmin(){
		return dmin;
	}
	
	public int[][] getMatrice(){
		return this.matriceDistances;
	}
	
	public void setDmin(int k){
		this.dmin = k;
	}
	
	public void setCriticalPoints(ArrayList<Integer> l){
		this.criticalPoints = l;
	}
	
	@Override
	public Etat clone() {
		//clone les croix
		ArrayList<Element> l = new ArrayList<Element>();
		for (Element x : this.getListe()){
			l.add(((Croix)x).clone());
		}
		return new Grille(this.f,l,this.taille);
	}

	@Override
	public double majEnergie() {
		this.setEnergie(this.f.calculer(this));
		return this.getEnergie();
	}
	
	public void afficheGrille(){
		int n = this.getListe().size();
		for (int i = 0; i < n; i++){
			System.out.println(this.getListe().get(i).toString());
		}
	}
	
	public ArrayList<Integer> findCriticalPoints(){
		ArrayList<Integer> minI = new ArrayList<Integer>();
		ArrayList<Integer> minJ = new ArrayList<Integer>();
		int[][] matriceDistances = new int[this.getTaille()][this.getTaille()];
		int dmin = FonctionEval.distance(((Croix)this.getListe().get(0)),((Croix)this.getListe().get(1)));
		for (int i = 0; i < this.getTaille(); i++){
			for (int j = i+1; j < this.getTaille(); j++){
				int dist = FonctionEval.distance(((Croix)this.getListe().get(i)),((Croix)this.getListe().get(j)));
				matriceDistances[i][j] = dist;
				if (dist < dmin){
					dmin = dist;
					minI.clear();
					minI.add(i);
					minJ.clear();
					minJ.add(j);
				}
				if (dist == dmin){
					minI.add(i);
					minJ.add(j);
				}
			}
		}
		ArrayList<Integer> l = new ArrayList<Integer>(2);
		l.add(minI.get((int) (Math.random()*minI.size())));
		l.add(minJ.get((int) (Math.random()*minJ.size())));
		this.criticalPoints = l;
		this.matriceDistances = matriceDistances;
		this.dmin = dmin;
		return l;
	}

	//Le nombre total de cases est n^d. L'énergie cinétique sera seulement le nombre de croix partagées ( < n )
	@Override
	public int distanceIsing(Etat e) {
		Grille autre = (Grille) e;
		int cpt = 0;
		for (int i = 0; i < this.getTaille(); i++){
			for (int j = 0; j < autre.getTaille(); j++){
				if (this.getListe().get(i).equals(autre.getListe().get(j))) cpt++;
			}
		}
		return cpt;
	}
	
	//Regarde si la croix est cochee
	public boolean estCochee(Croix c){
		for (int i = 0; i < this.getTaille(); i++){
			if (this.getListe().get(i).equals(c)) return true;
		}
		return false;
	}
	
	@Override
	public double getResultat() {
		return -this.dmin;
	}

}
