package LHD;

import java.util.ArrayList;

import modele.Etat;

public class Grille extends Etat {
	FonctionEval f;
	ArrayList<Croix> liste;
	int taille;
	ArrayList<Integer> criticalPoints = new ArrayList<Integer>(2);//Position points critiques
	int dmin ;

	public Grille(FonctionEval f, ArrayList<Croix> liste, int n){
		this.f = f;
		this.liste = liste;
		this.taille = n;
		findCriticalPoints();
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
	
	@Override
	public Etat clone() {
		//clone les croix
		ArrayList<Croix> l = new ArrayList<Croix>();
		for (Croix x : this.liste){
			l.add(x.clone());
		}
		return new Grille(this.f,l,this.taille);
	}

	@Override
	public double getEnergie() {
		return this.f.calculer(this);
	}
	
	public void afficheGrille(){
		int n = this.liste.size();
		for (int i = 0; i < n; i++){
			System.out.println(this.liste.get(i).toString());
		}
	}
	
	public ArrayList<Integer> findCriticalPoints(){
		int dmin = this.f.distance(this.getListe().get(0),this.getListe().get(1));
		int c1 = 0;
		int c2 = 1;
		for (int i = 0; i < this.getTaille(); i++){
			for (int j = i+1; j < this.getTaille(); j++){
				if (this.f.distance(this.getListe().get(i),this.getListe().get(j)) < dmin){
					dmin = this.f.distance(this.getListe().get(i),this.getListe().get(j));
					c1 = i;
					c2 = j;
				}
			}
		}
		ArrayList<Integer> l = new ArrayList<Integer>(2);
		l.add(c1);
		l.add(c2);
		this.criticalPoints = l;
		this.dmin = dmin;
		return l;
	}

}
