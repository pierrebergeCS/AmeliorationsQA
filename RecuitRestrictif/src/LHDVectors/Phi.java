package LHDVectors;

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
				cpt += Math.pow(distance(g.getCroix().get(i),g.getCroix().get(j)),-this.p);
			}
		}
		return cpt;
	}
	
	@Override
	public double calculerdelta(Grille g, MutationLH m){
		double cpt = 0;
		Croix next1 = (Croix) g.getCroix().get(m.getIndex()).clone();
		next1.getCoord()[m.getDim()] = g.getCroix().get(m.getCriticalIndex()).getCoord()[m.getDim()];
		Croix next2 = (Croix)g.getCroix().get(m.getCriticalIndex()).clone();
		next2.getCoord()[m.getDim()] = g.getCroix().get(m.getIndex()).getCoord()[m.getDim()];
		for (int i = 0; i < g.getTaille(); i++){
			if (i != m.getIndex() && i != m.getCriticalIndex()){
				cpt -= Math.pow(distance(g.getCroix().get(i),g.getCroix().get(m.getIndex())),-this.p);
				cpt += Math.pow(distance(g.getCroix().get(i),next1),-this.p);
				cpt -= Math.pow(distance(g.getCroix().get(i),g.getCroix().get(m.getCriticalIndex())),-this.p);
				cpt += Math.pow(distance(g.getCroix().get(i),next2),-this.p);
			}
		}
		cpt -= Math.pow(distance(g.getCroix().get(m.getCriticalIndex()),g.getCroix().get(m.getIndex())),-this.p);
		cpt += Math.pow(distance(next1,next2),-this.p);
		return cpt;
	}

}
