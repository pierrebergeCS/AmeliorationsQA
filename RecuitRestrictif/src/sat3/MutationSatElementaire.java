package sat3;

import modele.Etat;
import modele.Probleme;
import modele.RedondancesParticuleGeneral;


public class MutationSatElementaire extends mutation.MutationElementaire {

	public MutationSatElementaire(ElementSat elt, int indice) {
		super(elt, indice);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean estAutorisee(Probleme p, Etat e, RedondancesParticuleGeneral r){
		RedondancesParticuleSAT red = (RedondancesParticuleSAT) r;
		ElementSat elt = (ElementSat) this.getElement();
		int x = elt.getxi();
		boolean b = elt.getassignation();
		int k = 0;
		if (b) k=1;
		int[][] m = red.getTab();
		return (m[x][k] <= p.getFreq()*p.nombreEtat());
	}
	
	@Override
	public void majRedondance(Probleme p, RedondancesParticuleGeneral red, Etat e){
		RedondancesParticuleSAT r = (RedondancesParticuleSAT) red;
		ElementSat esat = (ElementSat) this.getElement();
		ElementSat prev = (ElementSat) e.getListe().get(this.getIndice());
		int old1 = prev.getxi();
		int old2 = 0;
		if (prev.getassignation()) old2=1;
		int next1 = esat.getxi();
		int next2 = 0;
		if (esat.getassignation()) next2 = 1;
		r.getTab()[old1][old2]--;
		r.getTab()[next1][next2]++;
	}

}
