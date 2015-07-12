package MKP;

import java.util.ArrayList;

import parametres.ParametreGamma;
import parametres.Temperature;
import sat3.EtatSat;
import sat3.Instancesat;
import sat3.Particulesat;
import modele.Etat;
import modele.Probleme;

public class ParticuleMKP extends Probleme {
	private Instance instance;
	
	public ParticuleMKP(ArrayList<Etat> etat,Temperature T,int seed,ParametreGamma gamma,double freq,Instance instance){
		super(etat,T,seed,gamma,freq);
		this.instance=instance;
		
	}
	
	public ParticuleMKP(Instance ins, ArrayList<Etat> r, double freq) {
		this.instance=ins;
		this.etat=r;
		this.freq=freq;
	}

	public static ParticuleMKP initialise(int nombreEtat,Instance ins,double freq){
		ArrayList<Etat> r = new ArrayList<Etat>(nombreEtat);
		for(int indice=0; indice<nombreEtat; indice++){
			r.add(new Remplissage(ins));
		}
		for(int id=1; id<r.size()-1; id++){
			r.get(id).setprevious(r.get(id-1));
			r.get(id).setnext(r.get(id+1));
		}
		r.get(r.size()-1).setprevious( r.get(r.size()-2));
		r.get(r.size()-1).setnext( r.get(0));
		r.get(0).setprevious( r.get(r.size()-1));
		r.get(0).setnext(r.get(1));
		ParticuleMKP p=new ParticuleMKP(ins,r,freq);
		return p;
	}
	
	
	@Override
	public Probleme clone() {
		ArrayList<Etat> l=this.getEtat();
		ArrayList<Etat> l1=new ArrayList<Etat>();
		for(Etat e:l){
			Etat e2=((Remplissage) e).clone();
			l1.add(e2);
		}
		ParticuleMKP p = new ParticuleMKP(l1,this.getT(),this.getSeed(),this.getGamma(),this.getFreq(),this.instance);
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
		return new Remplissage(this);
	}
	
	public Instance getIns(){
		return this.instance;
	}
}
