package MKP;

import java.util.ArrayList;

import modele.Etat;
import mutation.IMutation;


public class MutationMKP extends IMutation {
	int indice;//indice de ce qu'on ajoute
	int indice2;//indice de ce qu'on enleve
	int typeOfMut; //+1 si on ajoute, 0 si on échange, -1 si on retire
	
	public MutationMKP(Remplissage r){
		int n = r.getListe().size();//nombre d'objets
		boolean b = false;
		//On essaie d'abord d'ajouter
		int k = -1;
		while ( k < 0 || ((ElementMKP) r.getListe().get(k)).getAppartenance()){
			k = (int) (Math.random()*n);
		}
		//vérifie le respect des contraintes
		ElementMKP elt = (ElementMKP) r.getListe().get(k);
		int index = 0;
		while ((index < r.getInstance().getNombreSacs()) && (r.getPoids()[index] + elt.getObjet().getWeight()[index] <= r.getInstance().getCapacite()[index])){
			index++;
		}
		b = index==r.getInstance().getNombreSacs();
		if (b){
			this.indice = k;
			this.typeOfMut = 1;
		} else {
			//si on y arrive pas, on tente d'échanger avec un autre
			boolean b2 = false;
			int k2 = -1;
			while ( k2 < 0 || !((ElementMKP) r.getListe().get(k2)).getAppartenance()){
				k2 = (int) (Math.random()*n);
			}
			ElementMKP elt2 = (ElementMKP) r.getListe().get(k2);
			index = 0;
			while ((index < r.getInstance().getNombreSacs()) && (r.getPoids()[index] + elt.getObjet().getWeight()[index] - elt2.getObjet().getWeight()[index] <= r.getInstance().getCapacite()[index])){
				index++;
			}
			b2 = index==r.getInstance().getNombreSacs();
			if (b2) {
				this.indice = k;
				this.indice2 = k2;
				this.typeOfMut = 0;
			} else {
				//et enfin, si c'est pas possible on va tenter de retirer k2 (nécessaire si on souhaite réduire le nb d'objets dans le sac)
				this.indice2 = k2;
				this.typeOfMut = -1;
			}
		}
	}
	
	public int getType(){
		return this.typeOfMut;
	}
	
	public int getIndice(){
		return this.indice;
	}
	
	public int getIndice2(){
		return this.indice2;
	}
	
	@Override
	public void faire(Etat e) {
		Remplissage r = (Remplissage) e;
		int m = r.getInstance().getNombreSacs();
		if (this.typeOfMut == 1){
			ElementMKP elt = r.getListe().get(this.indice);
			elt.changeAppartenance();
			for(int i = 0; i < m; i++){
				r.getPoids()[i] += elt.getObjet().getWeight()[i];
			}
		}
		if (this.typeOfMut == -1){
			ElementMKP elt2 = r.getListe().get(this.indice2);
			elt2.changeAppartenance();
			for(int i = 0; i < m; i++){
				r.getPoids()[i] -= elt2.getObjet().getWeight()[i];
			}
		}
		if (this.typeOfMut == 0){
			ElementMKP elt = r.getListe().get(this.indice);
			ElementMKP elt2 = r.getListe().get(this.indice2);
			elt.changeAppartenance();
			elt2.changeAppartenance();
			for(int i = 0; i < m; i++){
				r.getPoids()[i] += elt.getObjet().getWeight()[i] - elt2.getObjet().getWeight()[i];
			}
		}
		
		
	}

	@Override
	public double calculerdeltaEp(Etat e) {
		Remplissage r = (Remplissage) e;
		if (this.typeOfMut == 1){
			ElementMKP elt = r.getListe().get(this.indice);
			return -elt.getObjet().getUtility();
		} 
		if (this.typeOfMut == -1){
			ElementMKP elt2 = r.getListe().get(this.indice2);
			return elt2.getObjet().getUtility();
		}
		if (this.typeOfMut == 0){
			ElementMKP elt = r.getListe().get(this.indice);
			ElementMKP elt2 = r.getListe().get(this.indice2);
			return elt2.getObjet().getUtility()-elt.getObjet().getUtility();
		} 
		return 0;
	}

	@Override
	public void maj(Etat e) {
		Remplissage r = (Remplissage) e;
		int n = r.getListe().size();//nombre d'objets
		boolean b = false;
		//On essaie d'abord d'ajouter
		int k = -1;
		while ( k < 0 || ((ElementMKP) r.getListe().get(k)).getAppartenance()){
			k = (int) (Math.random()*n);
		}
		//vérifie le respect des contraintes
		ElementMKP elt = (ElementMKP) r.getListe().get(k);
		int index = 0;
		while ((index < r.getInstance().getNombreSacs()) && (r.getPoids()[index] + elt.getObjet().getWeight()[index] <= r.getInstance().getCapacite()[index])){
			index++;
		}
		b = index==r.getInstance().getNombreSacs();
		if (b){
			this.indice = k;
			this.typeOfMut = 1;
		} else {
			//si on y arrive pas, on tente d'échanger avec un autre
			boolean b2 = false;
			int k2 = -1;
			while ( k2 < 0 || !((ElementMKP) r.getListe().get(k2)).getAppartenance()){
				k2 = (int) (Math.random()*n);
			}
			ElementMKP elt2 = (ElementMKP) r.getListe().get(k2);
			index = 0;
			while ((index < r.getInstance().getNombreSacs()) && (r.getPoids()[index] + elt.getObjet().getWeight()[index] - elt2.getObjet().getWeight()[index] <= r.getInstance().getCapacite()[index])){
				index++;
			}
			b2 = index==r.getInstance().getNombreSacs();
			if (b2) {
				this.indice = k;
				this.indice2 = k2;
				this.typeOfMut = 0;
			} else {
				//et enfin, si c'est pas possible on va tenter de retirer k2 (nécessaire si on souhaite réduire le nb d'objets dans le sac)
				this.indice2 = k2;
				this.typeOfMut = -1;
			}
		}
	}

}
