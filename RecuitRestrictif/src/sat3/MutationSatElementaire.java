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
		int[] m = red.getTab();
		int value = m[x];
		if (!b) value=p.nombreEtat()-m[x];
		return (value <= p.getFreq()*p.nombreEtat());
	}
	
	@Override
	public void majRedondance(Probleme p, RedondancesParticuleGeneral red, Etat e){
		RedondancesParticuleSAT r = (RedondancesParticuleSAT) red;
		ElementSat esat = (ElementSat) this.getElement();
		ElementSat prev = (ElementSat) e.getListe().get(this.getIndice());
		int old = prev.getxi();
		if (prev.getassignation()) r.getTab()[old]--;
		if (!prev.getassignation()) r.getTab()[old]++;;
		int next = esat.getxi();
		if (esat.getassignation()) r.getTab()[next]++;
		if (!esat.getassignation()) r.getTab()[next]--;
		;
	}

}
