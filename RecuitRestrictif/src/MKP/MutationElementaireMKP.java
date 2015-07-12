package MKP;


import modele.Etat;
import modele.Probleme;
import modele.RedondancesParticuleGeneral;
import mutation.MutationElementaire;

public class MutationElementaireMKP extends MutationElementaire {
	int addOrRemove; //+1 si on ajoute un objet, -1 si on l'enleve. Utile pour les deux dernières méthodes

	public MutationElementaireMKP(int indice) {
		super(null, indice);
	}
	
	@Override
	public void faire(Etat e){
		ElementMKP elt = (ElementMKP) e.getListe().get(this.getIndice());
		elt.changeAppartenance();
		int m = ((Remplissage)e).getInstance().getNombreSacs();
		for (int i = 0; i < m; i++){
			if (elt.getAppartenance()) ((Remplissage)e).getPoids()[m] += elt.getObjet().getWeight()[m];
			if (!elt.getAppartenance()) ((Remplissage)e).getPoids()[m] -= elt.getObjet().getWeight()[m];
		}
	}
	
	@Override
	public boolean estAutorisee(Probleme p, Etat e, RedondancesParticuleGeneral red){
		ElementMKP a = (ElementMKP) e.getListe().get(this.getIndice());
		RedondancesParticuleMKP r = (RedondancesParticuleMKP) red;
		int i = a.getObjet().getNumero();
		return (r.getTab()[i] <= p.getFreq()*p.nombreEtat());
	}
	
	@Override
	public void majRedondance(Probleme p, RedondancesParticuleGeneral red, Etat e){
		Remplissage re = (Remplissage) e;
		RedondancesParticuleMKP r = (RedondancesParticuleMKP) red;
		ElementMKP old = (ElementMKP) re.getListe().get(this.getIndice());
		ElementMKP next = (ElementMKP) this.getElement();
		int old1 = old.getObjet().getNumero();
		int next2 = next.getObjet().getNumero();
		r.getTab()[old1]--;
		r.getTab()[next2]++;
	}

}
