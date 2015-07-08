package LHD;

import java.util.ArrayList;

import modele.Etat;

public class Grille extends Etat {
	FonctionEval f;
	ArrayList<Croix> liste;
	int taille;
	ArrayList<Integer> criticalPoints = new ArrayList<Integer>(2);//Position points critiques
	int dmin ;
	int[][] matriceDistances;

	
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
	
	//Met à jour tous les points critiques, matriceDistances et dmin. Renvoie les points critiques de la grille
	public ArrayList<Integer> findCriticalPoints(){
		ArrayList<Integer> minI = new ArrayList<Integer>();
		ArrayList<Integer> minJ = new ArrayList<Integer>();
		//Recherche de dmin et points critiques
		int[][] matriceDistances = new int[this.getTaille()][this.getTaille()];
		int dmin = this.f.distance(this.getListe().get(0),this.getListe().get(1));
		for (int i = 0; i < this.getTaille(); i++){
			for (int j = i+1; j < this.getTaille(); j++){
				int dist = this.f.distance(this.getListe().get(i),this.getListe().get(j));
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
		this.matriceDistances = matriceDistances;
		this.criticalPoints = l;
		this.dmin = dmin;
		
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
		return -this.dmin;
	}

}
