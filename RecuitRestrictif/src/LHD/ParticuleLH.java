package LHD;

import java.util.ArrayList;

import parametres.ParametreGamma;
import parametres.Temperature;
import tsp.Graphe;
import tsp.ParticuleTSP;
import tsp.Routage;
import modele.Etat;
import modele.Probleme;

public class ParticuleLH extends Probleme {
	int n;//taille
	int d;//dimension
	FonctionEval f;
	
	public ParticuleLH(int n, int d, FonctionEval f, ArrayList<Etat> etat,Temperature T,int seed,ParametreGamma gamma, double freq){
		super(etat,T,seed,gamma, freq);
		this.n = n;
		this.d = d;
		this.f = f;
	}
	
	public ParticuleLH(int n, int d, FonctionEval f, ArrayList<Etat> l, double freq){
		this.etat = l;
		this.freq = freq;
		this.n = n;
		this.f = f;
		this.d = d;
	}
	
	public static ParticuleLH initialise(int n, int d, FonctionEval f, int nombreEtat, double freq){
		ArrayList<Etat> r = new ArrayList<Etat>(nombreEtat);
		for(int indice=0; indice<nombreEtat; indice++){
			r.add(new Grille(f,n,d));
		}
		for(int id=1; id<r.size()-1; id++){
			r.get(id).setprevious(r.get(id-1));
			r.get(id).setnext(r.get(id+1));
		}
		r.get(r.size()-1).setprevious( r.get(r.size()-2));
		r.get(r.size()-1).setnext( r.get(0));
		r.get(0).setprevious( r.get(r.size()-1));
		r.get(0).setnext(r.get(1));
		ParticuleLH p=new ParticuleLH(n,d,f,r,freq);
		return p;
	}
	
	public int getTaille(){
		return this.n;
	}
	
	public int getDim(){
		return this.d;
	}
	
	public FonctionEval getEval(){
		return this.f;
	}

	@Override
	public Probleme clone() {
		int n = this.etat.size();
		ArrayList<Etat> r = new ArrayList<Etat>(n);
		for(int i=0; i<n; i++){
			r.add(((Grille) this.etat.get(i)).clone());
		}
		 ParticuleLH p = new ParticuleLH(this.n,this.d,this.f,r,this.getT(), this.getSeed(),this.getGamma(), this.getFreq()) ;
		return p;
	}

	@Override
	public double calculerEnergieCinetique() {
		int nombreEtat = this.nombreEtat();
		double cpt =0;
		for (int k = 0; k < nombreEtat;k++){
				Etat e1=this.getEtat().get(k);
				Etat e2=e1.getNext();
				cpt+=e1.distanceIsing(e2);
		}
		return cpt;
	}

	@Override
	public Etat creeEtatAleatoire() {
		return new Grille(f,n,d);
	}

}
