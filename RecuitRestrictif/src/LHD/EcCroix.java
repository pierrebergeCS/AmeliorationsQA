package LHD;

import modele.Etat;

public class EcCroix extends EnergieCinetiqueLH {
	

	@Override
	public double distanceIsing(Grille g, Grille autre) {
		int cpt = 0;
		for (int i = 0; i < g.getTaille(); i++){
			for (int j = 0; j < autre.getTaille(); j++){
				if (g.getListe().get(i).equals(autre.getListe().get(j))) cpt++;
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
		MutationLHElementaire m1 = (MutationLHElementaire) m.listeMutations.get(0);
		MutationLHElementaire m2 = (MutationLHElementaire) m.listeMutations.get(1);
		
		Croix prev1 = ((Croix) e.getListe().get(m1.getIndice())).clone();
		Croix prev2 = ((Croix) e.getListe().get(m2.getIndice())).clone();
		
		Croix next1 = (Croix) m1.getElement();
		Croix next2 = (Croix) m2.getElement();
		
		Grille left = (Grille) e.getPrevious();
		Grille right = (Grille) e.getNext();
		
		if (left.estCochee(prev1)) cpt-=1;
		if (left.estCochee(prev2)) cpt-=1;
		if (right.estCochee(prev1)) cpt-=1;
		if (right.estCochee(prev2)) cpt-=1;
		
		if (left.estCochee(next1)) cpt+=1;
		if (left.estCochee(next2)) cpt+=1;
		if (right.estCochee(next1)) cpt+=1;
		if (right.estCochee(next2)) cpt+=1;
		
		return cpt;
	}

}
