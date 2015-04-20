package tsp;

import modele.Etat;
import modele.RedondancesParticuleGeneral;
import mutation.MutationElementaire;

public class TwoOptMoveElementaire extends MutationElementaire {
	
	public TwoOptMoveElementaire(Arete a, int indice){
		super(a,indice);
	}
	
	@Override
	public boolean estAutorisee(Etat e, RedondancesParticuleGeneral red){
		RedondancesParticuleTSP r = (RedondancesParticuleTSP) red;
		Routage route = (Routage) e;
		
	}

}
