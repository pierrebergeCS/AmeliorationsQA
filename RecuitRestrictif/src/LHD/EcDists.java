package LHD;

import modele.Etat;

public class EcDists extends EnergieCinetiqueLH {

	@Override
	public double distanceIsing(Grille g1, Grille g2) {
		double cpt = 0;
		int n = g1.getTaille();
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				double dist = FonctionEval.distance(((Croix)g1.getListe().get(i)),((Croix)g2.getListe().get(j)));
				cpt += 1.0/(1.0+dist);
			}
		}
		return cpt;
	}

	@Override
	public double calculerEc(ParticuleLH p) {
		int nombreEtat = p.nombreEtat();
		double cpt =0;
		for (int k = 0; k < nombreEtat;k++){
				Etat e1=p.getEtat().get(k);
				Etat e2=e1.getNext();
				cpt+=distanceIsing((Grille)e1,(Grille)e2);
		}
		return cpt;
	}

	@Override
	public double deltaSpins(ParticuleLH p, Grille e, MutationLH m) {
		double cpt = 0;
		int n = e.getTaille();
		MutationLHElementaire m1 = (MutationLHElementaire) m.listeMutations.get(0);
		MutationLHElementaire m2 = (MutationLHElementaire) m.listeMutations.get(1);
		
		Croix prev1 = ((Croix) e.getListe().get(m1.getIndice())).clone();
		Croix prev2 = ((Croix) e.getListe().get(m2.getIndice())).clone();
		
		Croix next1 = (Croix) m1.getElement();
		Croix next2 = (Croix) m2.getElement();
		
		Grille left = (Grille) e.getPrevious();
		Grille right = (Grille) e.getNext();
		
		for (int i = 0; i < n; i++){
			double distL = FonctionEval.distance(((Croix)left.getListe().get(i)),prev1);
			double distR = FonctionEval.distance(((Croix)right.getListe().get(i)),prev1);
			cpt -= (1.0/(1.0+distL) + 1.0/(1.0+distR));
		}
		
		for (int i = 0; i < n; i++){
			double distL = FonctionEval.distance(((Croix)left.getListe().get(i)),prev2);
			double distR = FonctionEval.distance(((Croix)right.getListe().get(i)),prev2);
			cpt -= (1.0/(1.0+distL) + 1.0/(1.0+distR));
		}
		
		for (int i = 0; i < n; i++){
			double distL = FonctionEval.distance(((Croix)left.getListe().get(i)),next1);
			double distR = FonctionEval.distance(((Croix)right.getListe().get(i)),next1);
			cpt += (1.0/(1.0+distL) + 1.0/(1.0+distR));
		}
		
		for (int i = 0; i < n; i++){
			double distL = FonctionEval.distance(((Croix)left.getListe().get(i)),next2);
			double distR = FonctionEval.distance(((Croix)right.getListe().get(i)),next2);
			cpt += (1.0/(1.0+distL) + 1.0/(1.0+distR));
		}
		
		return cpt;
	}

}
