package vc;

import java.util.ArrayList;

import parametres.ParametreGamma;
import parametres.Temperature;
import modele.Etat;
import modele.Probleme;

public class ParticuleVC extends Probleme {
	Graphe g;
	int nbColors;
	
	public ParticuleVC(ArrayList<Etat> etat,Temperature T,int seed,ParametreGamma gamma,double freq,Graphe g,int nbColors){
		super(etat,T,seed,gamma,freq);
		this.g = g;
		this.nbColors = nbColors;
	}
	
	public ParticuleVC(Graphe g, int nbColors, ArrayList<Etat> r, double freq) {
	this.g=g;
	this.nbColors = nbColors;
	this.etat=r;
	this.freq=freq;
	}
	
	public Graphe getGraphe(){
		return this.g;
	}
	
	public int getNbColors(){
		return this.nbColors;
	}
	
	public static ParticuleVC initialise(int nombreEtat,double freq, Graphe g, int nbColors){
		ArrayList<Etat> r = new ArrayList<Etat>(nombreEtat);
		for(int indice=0; indice<nombreEtat; indice++){
			r.add(new Coloriage(g,nbColors));
		}
		for(int id=1; id<r.size()-1; id++){
			r.get(id).setprevious(r.get(id-1));
			r.get(id).setnext(r.get(id+1));
		}
		r.get(r.size()-1).setprevious( r.get(r.size()-2));
		r.get(r.size()-1).setnext( r.get(0));
		r.get(0).setprevious( r.get(r.size()-1));
		r.get(0).setnext(r.get(1));
		ParticuleVC p=new ParticuleVC(g,nbColors,r,freq);
		return p;
	}

	@Override
	public Probleme clone() {
		ArrayList<Etat> l=this.getEtat();
		ArrayList<Etat> l1=new ArrayList<Etat>();
		for(Etat e:l){
			Etat e2=((Coloriage) e).clone();
			l1.add(e2);
		}
		ParticuleVC p = new ParticuleVC(l1,this.getT(),this.getSeed(),this.getGamma(),this.getFreq(),this.g, this.nbColors);
		return p;
	}

	@Override
	public double calculerEnergieCinetique() {
		int cpt=0;
		ArrayList<Etat> l=this.getEtat();
		for(int i=0;i<l.size();i++){
			Etat e1=l.get(i);
			Etat e2=e1.getNext();
			cpt+=e1.distanceIsing(e2);
		}
		return cpt;
	}

	@Override
	public Etat creeEtatAleatoire() {
		return new Coloriage(this.g,this.nbColors);
	}

}
