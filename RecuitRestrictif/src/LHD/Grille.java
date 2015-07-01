package LHD;

import java.util.ArrayList;

import modele.Element;
import modele.Etat;

public class Grille extends Etat {
	FonctionEval f;
	int taille;
	ArrayList<Integer> criticalPoints = new ArrayList<Integer>(2);//Position points critiques
	int dmin ;

	public Grille(FonctionEval f, ArrayList<Element> liste, int n){
		this.f = f;
		this.listeElements = liste;
		this.taille = n;
		findCriticalPoints();
	}
	
	public Grille(FonctionEval f, int n, int dim){
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
			liste.add(new Croix(tab));
		}
		
		this.listeElements = liste;
		this.taille = n;
		findCriticalPoints();
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
	public double getEnergie() {
		return this.f.calculer(this);
	}
	
	public void afficheGrille(){
		int n = this.getListe().size();
		for (int i = 0; i < n; i++){
			System.out.println(this.getListe().get(i).toString());
		}
	}
	
	public ArrayList<Integer> findCriticalPoints(){
		int dmin = this.f.distance(((Croix)this.getListe().get(0)),((Croix)this.getListe().get(1)));
		int c1 = 0;
		int c2 = 1;
		for (int i = 0; i < this.getTaille(); i++){
			for (int j = i+1; j < this.getTaille(); j++){
				if (this.f.distance(((Croix)this.getListe().get(i)),((Croix)this.getListe().get(j))) < dmin){
					dmin = this.f.distance(((Croix)this.getListe().get(i)),((Croix)this.getListe().get(j)));
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

}
