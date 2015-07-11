package MKP;

import modele.Element;
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
		return true;//On verra le blocage plus tard
	}
	
	@Override
	public void majRedondance(Probleme p, RedondancesParticuleGeneral red, Etat e){
		//On verra le blocage plus tard
	}

}
