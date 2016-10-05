package OptimalNodeCoverage;


import modele.Etat;
import modele.Probleme;
import mutation.IMutation;
import mutation.MutationElementaire;

public class MutationNodeCoverage extends IMutation {
	int indice;//indice de ce qu'on ajoute
	int indice2;//indice de ce qu'on enleve
	int typeOfMut; //+1 si on ajoute, 0 si on échange, -1 si on retire



	@Override
	public double calculerdeltaEp(Probleme p, Etat e) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calculerdeltaSpins(Probleme p, Etat e) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void maj(Probleme p, Etat e) {
		// TODO Auto-generated method stub

	}

}
