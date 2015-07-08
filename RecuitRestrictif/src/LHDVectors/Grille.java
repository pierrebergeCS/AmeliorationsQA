package LHDVectors;

import java.util.ArrayList;

import LHDVectors.Croix;
import LHDVectors.FonctionEval;
import modele.Element;
import modele.Etat;

public class Grille extends Etat {
	FonctionEval f;
	int taille;
	ArrayList<Integer> criticalPoints = new ArrayList<Integer>(2);//Position points critiques
	int dmin ;
	int[][] matriceDistances;
	ArrayList<Croix> listeCroix;
	
	public Grille(FonctionEval f, ArrayList<Element> liste,ArrayList<Croix> listeCroix,int n){
		this.f = f;
		this.listeElements = liste;
		this.taille = n;
		this.listeCroix = listeCroix;
		findCriticalPoints();
		this.majEnergie();
	}
	
	public Grille(FonctionEval f, int n, int dim){
		this.f = f;
		ArrayList<Element> liste = new ArrayList<Element>();
		Croix[] tabCroix = new Croix[n];
		
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
			tabCroix[tab[0]] = new Croix(tab);
		}
		
		ArrayList<Croix> listeCroix = new ArrayList<Croix>();
		for (int i = 0; i < n; i++){
			listeCroix.add(tabCroix[i]);
		}
		
		this.listeCroix = listeCroix;
		this.taille = n;
		findCriticalPoints();
		this.majEnergie();
		
		//mise à jour vecteurs
		for (int i = 0; i < n-1; i++){
			int[] tabV = new int[dim];
			for (int d = 0; d < dim; d++){
				tabV[d] = listeCroix.get(i+1).getCoord()[d] - listeCroix.get(i).getCoord()[d];
			}
			liste.add(new Vecteur(tabV));
		}
		this.setListe(liste);
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
	
	public ArrayList<Croix> getCroix(){
		return this.listeCroix;
	}

	//Regarde si la croix est cochee
		public boolean estCochee(Croix c){
			for (int i = 0; i < this.getTaille(); i++){
				if (this.getCroix().get(i).equals(c)) return true;
			}
			return false;
		}
		
	@Override
	public Etat clone() {
		//clone les vecteurs
			ArrayList<Element> l = new ArrayList<Element>();
			for (Element x : this.getListe()){
				l.add(((Vecteur)x).clone());
			}
			
		//clone les croix
			ArrayList<Croix> lCroix = new ArrayList<Croix>();
			for (int k = 0; k < this.getTaille(); k++){
				lCroix.add(this.listeCroix.get(k));
			}
			return new Grille(this.f,l,lCroix,this.taille);
	}
	
	public void afficheGrille(){
		int n = this.getCroix().size();
		for (int i = 0; i < n; i++){
			System.out.println(this.getCroix().get(i).toString());
		}
		
		System.out.println("");
		for (int i = 0; i < n-1; i++){
			System.out.print(" -> " + this.getListe().get(i).toString());
		}
	}
	
	public ArrayList<Integer> findCriticalPoints(){
		ArrayList<Integer> minI = new ArrayList<Integer>();
		ArrayList<Integer> minJ = new ArrayList<Integer>();
		int[][] matriceDistances = new int[this.getTaille()][this.getTaille()];
		int dmin = FonctionEval.distance(((Croix)this.getCroix().get(0)),((Croix)this.getCroix().get(1)));
		for (int i = 0; i < this.getTaille(); i++){
			for (int j = i+1; j < this.getTaille(); j++){
				int dist = FonctionEval.distance(((Croix)this.getCroix().get(i)),((Croix)this.getCroix().get(j)));
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

	@Override
	public double majEnergie() {
		this.setEnergie(this.f.calculer(this));
		return this.getEnergie();
	}

	@Override
	public int distanceIsing(Etat e) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getResultat() {
		return -this.dmin;
	}

}
