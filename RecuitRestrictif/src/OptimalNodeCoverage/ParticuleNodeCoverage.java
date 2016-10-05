package OptimalNodeCoverage;


import java.util.ArrayList;


import parametres.ParametreGamma;
import parametres.Temperature;
import modele.Etat;
import modele.Probleme;


public class ParticuleNodeCoverage extends  Probleme{
	
	private Coverage c;
	
	public ParticuleNodeCoverage(ArrayList<Etat> etat,Temperature T,int seed,ParametreGamma gamma,double freq,Coverage c){
		super(etat,T,seed,gamma,freq);
		this.c=c;
	}
	
	@Override
	public Probleme clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double calculerEnergieCinetique() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Etat creeEtatAleatoire() {
		// TODO Auto-generated method stub
		return null;
	}

}
