package sat3;
import java.util.ArrayList;

import parametres.ParametreGamma;
import parametres.Temperature;
import modele.Etat;
import modele.Probleme;
public class Particulesat extends Probleme{

	private Instancesat instance;
	
	public Particulesat(ArrayList<Etat> etat,Temperature T,int seed,ParametreGamma gamma,double freq,Instancesat instance){
		super(etat,T,seed,gamma,freq);
		this.instance=instance;
		
	}
	@Override
	public Probleme clone() {
		ArrayList<Etat> l=this.getEtat();
		ArrayList<Etat> l1=new ArrayList<Etat>();
		for(Etat e:l){
			Etat e2=((EtatSat) e).clone();
			l1.add(e2);
		}
		Particulesat p = new Particulesat(l1,this.getT(),this.getSeed(),this.getGamma(),this.getFreq(),this.instance);
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
		return new EtatSat(this);
	}

	public int getnombreXi() {

		return this.instance.getNbvar();
	}

	public Instancesat getInstance() {
		return this.instance;
	}

}
