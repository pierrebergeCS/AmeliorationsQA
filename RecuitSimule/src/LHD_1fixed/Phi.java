package LHD_1fixed;

public class Phi extends FonctionEval {
	int p;
	
	public Phi(int p){
		this.p = p;
	}

	@Override
	public double calculer(Grille g) {
		double cpt = 0;
		for(int i = 0; i < g.getTaille(); i++){
			for (int j = i+1; j < g.getTaille(); j++){
				cpt += Math.pow(distance(g.getListe().get(i),g.getListe().get(j)),-this.p);
			}
		}
		return cpt;
	}
	
	@Override
	public double calculerdelta(Grille g, MutationLH m){
		double cpt = 0;
		Croix next1 = g.getListe().get(m.getIndex()).clone();
		next1.getCoord()[m.getDim()] = g.getListe().get(m.getCriticalIndex()).getCoord()[m.getDim()];
		Croix next2 = g.getListe().get(m.getCriticalIndex()).clone();
		next2.getCoord()[m.getDim()] = g.getListe().get(m.getIndex()).getCoord()[m.getDim()];
		for (int i = 0; i < g.getTaille(); i++){
			if (i != m.getIndex() && i != m.getCriticalIndex()){
				cpt -= Math.pow(distance(g.getListe().get(i),g.getListe().get(m.getIndex())),-this.p);
				cpt += Math.pow(distance(g.getListe().get(i),next1),-this.p);
				cpt -= Math.pow(distance(g.getListe().get(i),g.getListe().get(m.getCriticalIndex())),-this.p);
				cpt += Math.pow(distance(g.getListe().get(i),next2),-this.p);
			}
		}
		cpt -= Math.pow(distance(g.getListe().get(m.getCriticalIndex()),g.getListe().get(m.getIndex())),-this.p);
		cpt += Math.pow(distance(next1,next2),-this.p);
		return cpt;
	}

}
