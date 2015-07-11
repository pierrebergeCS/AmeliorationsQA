package MKP;

import java.util.ArrayList;

import modele.Etat;
import modele.Probleme;
import mutation.IMutation;
import mutation.MutationElementaire;

public class MutationMKP extends IMutation {
	
	public MutationMKP(Remplissage r){
		int n = r.getListe().size();//nombre d'objets
		ArrayList<MutationElementaire> l = new ArrayList<MutationElementaire>();
		MutationElementaireMKP me = new MutationElementaireMKP(0);
		boolean b = false;
		while (!b){
			int k = (int) (Math.random()*n);
			me = new MutationElementaireMKP(k);
			l.add(me);
			
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
		
		this.listeMutations = l;
	}

	@Override
	public double calculerdeltaEp(Probleme p, Etat e) {
		Remplissage r = (Remplissage) e;
		ElementMKP elt = (ElementMKP) r.getListe().get(this.listeMutations.get(0).getIndice());
		if (elt.getAppartenance()){
			return -elt.getObjet().getUtility();
		} else {
			return elt.getObjet().getUtility();
		}
	}

	@Override
	public double calculerdeltaSpins(Probleme p, Etat e) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void maj(Probleme p, Etat e) {
		Remplissage r = (Remplissage) e;
		int n = r.getListe().size();//nombre d'objets
		ArrayList<MutationElementaire> l = new ArrayList<MutationElementaire>();
		MutationElementaireMKP me = new MutationElementaireMKP(0);
		boolean b = false;
		while (!b){
			int k = (int) (Math.random()*n);
			me = new MutationElementaireMKP(k);
			l.add(me);
			
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
		
		this.listeMutations = l;
	}

}
