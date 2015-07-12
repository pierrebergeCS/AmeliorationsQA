package LHDVectors;

import modele.Etat;
import modele.Probleme;
import modele.RedondancesParticuleGeneral;
import mutation.MutationElementaire;

public class MutationLHElementaire extends MutationElementaire {

	public MutationLHElementaire(Vecteur v, int indice) {
		super(v, indice);
	}
	
	@Override
	public boolean estAutorisee(Probleme p,Etat e, RedondancesParticuleGeneral red){
		return true; //pas de blocage pour l'instant
	}
	
	@Override
	public void majRedondance(Probleme p, RedondancesParticuleGeneral red, Etat e){
		//ras puisque redondancesparticulelh ne sert à rien
	}

	
}
