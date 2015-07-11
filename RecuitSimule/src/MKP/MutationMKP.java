package MKP;

import java.util.ArrayList;

import modele.Etat;
import mutation.IMutation;


public class MutationMKP extends IMutation {
	int indice;
	
	public MutationMKP(Remplissage r){
		int n = r.getListe().size();//nombre d'objets
		int k = 0;
		boolean b = false;
		while (!b){
			k = (int) (Math.random()*n);
			
			//vérifie le respect des contraintes
			ElementMKP elt = (ElementMKP) r.getListe().get(k);
			if (!elt.getAppartenance()){
				//Cas où on ajoute
				int index = 0;
				while ((index < r.getInstance().getNombreSacs()) && (r.getPoids()[index] + elt.getObjet().getWeight()[index] <= r.getInstance().getCapacite()[index])){
					index++;
				}
				b = index==r.getInstance().getNombreSacs();
			} else {
				//Cas où on retire
				int index = 0;
				while ((index < r.getInstance().getNombreSacs()) && (r.getPoids()[index] - elt.getObjet().getWeight()[index] >= 0)){
					index++;
				}
				b = index==r.getInstance().getNombreSacs();
			}
		}
		
		this.indice = k;;
	}
	
	@Override
	public void faire(Etat e) {
		ElementMKP elt = ((Remplissage)e).getListe().get(this.indice);
		elt.changeAppartenance();
		int m = ((Remplissage)e).getInstance().getNombreSacs();
		for (int i = 0; i < m; i++){
			if (elt.getAppartenance()) ((Remplissage)e).getPoids()[m] += elt.getObjet().getWeight()[m];
			if (!elt.getAppartenance()) ((Remplissage)e).getPoids()[m] -= elt.getObjet().getWeight()[m];
		}
	}

	@Override
	public double calculerdeltaEp(Etat e) {
		Remplissage r = (Remplissage) e;
		ElementMKP elt = (ElementMKP) r.getListe().get(this.indice);
		if (elt.getAppartenance()){
			return -elt.getObjet().getUtility();
		} else {
			return elt.getObjet().getUtility();
		}
	}

	@Override
	public void maj(Etat e) {
		Remplissage r = (Remplissage) e;
		int n = r.getListe().size();//nombre d'objets
		int k = 0;
		boolean b = false;
		while (!b){
			k = (int) (Math.random()*n);
			
			//vérifie le respect des contraintes
			ElementMKP elt = (ElementMKP) r.getListe().get(k);
			if (!elt.getAppartenance()){
				//Cas où on ajoute
				int index = 0;
				while ((index < r.getInstance().getNombreSacs()) && (r.getPoids()[index] + elt.getObjet().getWeight()[index] <= r.getInstance().getCapacite()[index])){
					index++;
				}
				b = index==r.getInstance().getNombreSacs();
			} else {
				//Cas où on retire
				int index = 0;
				while ((index < r.getInstance().getNombreSacs()) && (r.getPoids()[index] - elt.getObjet().getWeight()[index] >= 0)){
					index++;
				}
				b = index==r.getInstance().getNombreSacs();
			}
		}
		
		this.indice = k;;
	}

}
