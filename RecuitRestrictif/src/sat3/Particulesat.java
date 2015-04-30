package sat3;
import java.util.ArrayList;

import modele.Etat;
import modele.Probleme;
public class Particulesat extends Probleme{

	private Instancesat instance;

	@Override
	public Probleme clone() {
		// TODO Auto-generated method stub
		return null;
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
