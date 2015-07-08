package LHD_1fixed;

import modele.Etat;
import modele.Probleme;
import modele.RedondancesParticuleGeneral;
import mutation.MutationElementaire;

public class MutationLHElementaire extends MutationElementaire {

	public MutationLHElementaire(Croix c, int indice){
		super(c,indice);
	}
	
	@Override
	public boolean estAutorisee(Probleme p,Etat e, RedondancesParticuleGeneral red){
		if (p.getFreq() > 1.0){
			return true;
		}
		int cpt = 0;
		Croix c = (Croix) e.getListe().get(this.getIndice());
		for (int k = 0; k < p.nombreEtat(); k++){
			if (((Grille)p.getEtat().get(k)).estCochee(c)) cpt++;
		}
		if (cpt > p.getFreq()*p.nombreEtat()) return false;
		return true;
	}
	
	@Override
	public void majRedondance(Probleme p, RedondancesParticuleGeneral red, Etat e){
		//ras puisque redondancesparticulelh ne sert à rien
	}
	
}
