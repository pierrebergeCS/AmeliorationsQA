package sat3;

import java.util.ArrayList;

import tsp.Routage;
import modele.Etat;
import mutation.IMutation;

public class MutationSat extends IMutation{
	public int var;

	public MutationSat(EtatSat e){
		int n = e.getNbxi();
		int rand = (int) (n * Math.random()); 
		this.var = rand;
	}
	
	@Override
	public void faire(Etat e){
		EtatSat esat = (EtatSat) e;
		esat.getListe().get(this.var).change();
	}
	
	@Override
	public double calculerdeltaEp(Etat e) {
		ElementSat elt = ((EtatSat)e).getListe().get(this.var);
		int cpt=0;
		for(Integer i : elt.getMintermes() ){
			Minterme m =((EtatSat) e).clauses.get(i);
			
			boolean preced=m.is();
			int j =m.getIndex(elt);
			
			m.tab[j].change();
			
			if(m.is()&&(preced==false)){				
				cpt--;
				
			}
			if(preced && (m.is()==false)){
				cpt++;
			}
		
			m.tab[j].change();//reset a la valeur initiale du calque
			
		}
		return cpt;
	}


	@Override
	public void maj(Etat e) {
		EtatSat esat = (EtatSat) e;
		int n = esat.getNbxi();
		int rand = (int) (n * Math.random()); 
		this.var = rand;
	}
	

}
