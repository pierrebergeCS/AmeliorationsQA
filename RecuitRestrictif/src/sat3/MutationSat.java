package sat3;

import java.util.ArrayList;

import modele.Etat;
import modele.Probleme;
import mutation.IMutation;
import mutation.MutationElementaire;

public class MutationSat extends IMutation{

	public MutationSat(EtatSat e){
		int n = e.getNbxi();
		int rand = (int) (n * Math.random()); 
		boolean b = !((ElementSat)e.getListe().get(rand)).getassignation();//On inverse le booléen
		ElementSat enext = new ElementSat(((ElementSat)e.getListe().get(rand)).getxi(),b);
		MutationSatElementaire msat = new MutationSatElementaire(enext,rand);
		ArrayList<MutationElementaire> l = new ArrayList<MutationElementaire>();
		l.add(msat);
		this.listeMutations = l;
	}
	@Override
	public double calculerdeltaEp(Probleme p, Etat e) {
		MutationSatElementaire mut= (MutationSatElementaire) this.listeMutations.get(0);
		ElementSat elt =(ElementSat) mut.getElement();
		int cpt=0;
		for(Minterme m : elt.getMintermes() ){
			boolean preced=m.is();
			int i =m.getIndex(elt);
			m.calque[i]=-m.calque[i];
			if(m.is()&&(preced==false)){
				cpt--;
			}
			if(preced && (m.is()==false)){
				cpt++;
			}
			m.calque[i]=-m.calque[i];//reset a la valeur initiale du calque
			
		}
		return cpt;
	}

	@Override
	public double calculerdeltaSpins(Probleme p, Etat e) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void maj(Probleme p, Etat e) {
		EtatSat esat = (EtatSat) e;
		int n = esat.getNbxi();
		int rand = (int) (n * Math.random()); 
		boolean b = !((ElementSat)esat.getListe().get(rand)).getassignation();//On inverse le booléen
		ElementSat enext = new ElementSat(((ElementSat)esat.getListe().get(rand)).getxi(),b);
		MutationSatElementaire msat = new MutationSatElementaire(enext,rand);
		ArrayList<MutationElementaire> l = new ArrayList<MutationElementaire>();
		l.add(msat);
		this.listeMutations = l;
	}
	

}
