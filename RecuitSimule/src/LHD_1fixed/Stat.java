package LHD_1fixed;

public class Stat extends FonctionEval {
	double p;//exposant sigma
	double q;//exposant moyenne
	
	public Stat(int p, int q){
		this.p = p;
		this.q = q;
	}
	
	@Override
	public double calculer(Grille g) {
		
			double nbCouples = (g.getTaille()*(g.getTaille()-1))/2;
			double moy = 0;
			double var = 0;
			for(int i = 0; i < g.getTaille(); i++){
				for (int j = i+1; j < g.getTaille(); j++){
					double dist = this.distance(g.getListe().get(i),g.getListe().get(j));
					moy += dist;
					var += dist*dist;
				}
			}
			var -= (moy*moy)/nbCouples;
		return Math.pow(var,0.5*this.p)/Math.pow(moy,this.q);
	}

	@Override
	public double calculerdelta(Grille g, MutationLH m) {
		Grille gmut = (Grille)g.clone();
		m.faire(gmut);
		return gmut.majEnergie() - g.getEnergie();
	}

}
