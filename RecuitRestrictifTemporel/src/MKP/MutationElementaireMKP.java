package MKP;


import sat3.ElementSat;
import modele.Etat;
import modele.Probleme;
import modele.RedondancesParticuleGeneral;
import mutation.MutationElementaire;

public class MutationElementaireMKP extends MutationElementaire {
	//int addOrRemove; //+1 si on ajoute un objet, -1 si on l'enleve. Utile pour les deux dernières méthodes

	public MutationElementaireMKP(ElementMKP elt, int indice) {
		super(elt, indice);
	}
	
	@Override
	public void faire(Etat e){
		/*ElementMKP elt = (ElementMKP) e.getListe().get(this.getIndice());
		elt.changeAppartenance();
		int m = ((Remplissage)e).getInstance().getNombreSacs();
		for (int i = 0; i < m; i++){
			if (elt.getAppartenance()) ((Remplissage)e).getPoids()[m] += elt.getObjet().getWeight()[m];
			if (!elt.getAppartenance()) ((Remplissage)e).getPoids()[m] -= elt.getObjet().getWeight()[m];
		}*/
		System.out.println("alerte");
	}
	
	@Override
	public boolean estAutorisee(Probleme p, Etat e, RedondancesParticuleGeneral red,int dureeBlock){
		ElementMKP elt = (ElementMKP) this.getElement();
		
		if(elt.lives()){
			if(elt.iterate()){
				return false;
			}
			else{ return true;}
		}
		
		if (p.getFreq() > 1) return true;
		ElementMKP a = (ElementMKP) e.getListe().get(this.getIndice());
		RedondancesParticuleMKP r = (RedondancesParticuleMKP) red;
		int i = a.getObjet().getNumero();
		
		boolean estAutorisee=r.getTab()[i] <= p.getFreq()*p.nombreEtat();
		if(!estAutorisee){
			elt.resetBlock(dureeBlock);
		}
		return estAutorisee ;
	}
	
	@Override
	public void majRedondance(Probleme p, RedondancesParticuleGeneral red, Etat e){
		Remplissage re = (Remplissage) e;
		RedondancesParticuleMKP r = (RedondancesParticuleMKP) red;
		ElementMKP elt = (ElementMKP) this.getElement();
		if (elt.getAppartenance()){
			r.getTab()[elt.getObjet().getNumero()]++;
		} else {
			r.getTab()[elt.getObjet().getNumero()]--;
		}
	}

}
