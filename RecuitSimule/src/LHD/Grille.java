package LHD;

import java.util.ArrayList;
import java.util.Collections;

import modele.Etat;

public class Grille extends Etat {
	FonctionEval f;
	ArrayList<Croix> liste;
	int taille;
	ArrayList<Integer> criticalPoints = new ArrayList<Integer>(2);//Position points critiques
	ArrayList<Distance> distributionDistances;

	
	public Grille(FonctionEval f, ArrayList<Croix> liste, int n, double energie){
		this.f = f;
		this.liste = liste;
		this.taille = n;
		findCriticalPoints();
		this.setEnergie(energie);
	}
	
	public Grille(FonctionEval f, ArrayList<Croix> liste, int n){
		this.f = f;
		this.liste = liste;
		this.taille = n;
		findCriticalPoints();
		this.majEnergie();
	}
	
	public Grille(FonctionEval f, int n, int dim){
		this.f = f;
		ArrayList<Croix> liste = new ArrayList<Croix>();
		
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
			liste.add(new Croix(tab));
		}
		
		this.liste = liste;
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
	
	public ArrayList<Croix> getListe(){
		return this.liste;
	}
	
	public ArrayList<Integer> getCriticalPoints(){
		return this.criticalPoints;
	}
	
	public int getdmin(){
		return distributionDistances.get(0).getValue();
	}
	
	public ArrayList<Distance> getDistances(){
		return this.distributionDistances;
	}
	
	public void setCriticalPoints(ArrayList<Integer> l){
		this.criticalPoints = l;
	}
	
	@Override
	public Etat clone() {
		//clone les croix
		ArrayList<Croix> l = new ArrayList<Croix>();
		for (Croix x : this.liste){
			l.add(x.clone());
		}
		return new Grille(this.f,l,this.taille,this.energie);
	}

	@Override
	public double majEnergie() {
		this.energie = this.f.calculer(this);
		return this.energie;
	}
	
	public void afficheGrille(){
		int n = this.liste.size();
		for (int i = 0; i < n; i++){
			System.out.println(this.liste.get(i).toString());
		}
		System.out.println("");
	}
	
	//Ajoute une distance à la distribution pour qu'elle reste triée
	public void addDistance(Distance dist){
		int length = this.distributionDistances.size();
		this.distributionDistances.add(dist);
		if (length > 0){
			int index = length;
			int value = this.distributionDistances.get(index-1).getValue();
			while (value > dist.getValue() && index > 0){
				Collections.swap(this.distributionDistances,index-1,index);
				if (index == 1){
					value = -1;
				} else {
					value = this.distributionDistances.get(index-2).getValue();
				}
				index--;
			}
		}
		
	}
	
	//enleve cette distance de la distribution
	public void removeDistance(int value){
		int index = 0;
		int limit = this.distributionDistances.size();
		while ((index < limit) &&(this.distributionDistances.get(index).getValue() != value)){
			index++;
		}
		if (index < limit) {
			this.distributionDistances.remove(index);
		} else {
			//
		}
	}
	
	//Met à jour tous les points critiques, distributionDistances et dmin. Renvoie les points critiques de la grille
	public ArrayList<Integer> findCriticalPoints(){
		ArrayList<Integer> minI = new ArrayList<Integer>();
		ArrayList<Integer> minJ = new ArrayList<Integer>();
		//Recherche de dmin et points critiques
		this.distributionDistances = new ArrayList<Distance>();
		int dmin = this.f.distance(this.getListe().get(0),this.getListe().get(1));
		for (int i = 0; i < this.getTaille(); i++){
			for (int j = i+1; j < this.getTaille(); j++){
				Distance dist = new Distance(this,i,j);
				this.addDistance(dist);
				if (dist.getValue() < dmin){
					dmin = dist.getValue();
					minI.clear();
					minI.add(i);
					minJ.clear();
					minJ.add(j);
				}
				if (dist.getValue() == dmin){
					minI.add(i);
					minJ.add(j);
				}
			}
		}
		ArrayList<Integer> l = new ArrayList<Integer>(2);
		int k = (int) (Math.random()*minI.size());
		l.add(minI.get(k));
		l.add(minJ.get(k));
		this.criticalPoints = l;
		
		return l;
	}
	
	//Regarde si la croix est cochee
		public boolean estCochee(Croix c){
			for (int i = 0; i < this.getTaille(); i++){
				if (this.getListe().get(i).equals(c)) return true;
			}
			return false;
		}
		
	public void maj(){
		int n = this.getTaille();
		int dim = this.getListe().get(0).getDimension();
		ArrayList<Croix> liste = new ArrayList<Croix>();
		
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
			liste.add(new Croix(tab));
		}
		
		this.liste = liste;
		this.taille = n;
		findCriticalPoints();
		this.majEnergie();
	}

	@Override
	public double getResultat() {
		return -this.distributionDistances.get(0).getValue();
	}

}
