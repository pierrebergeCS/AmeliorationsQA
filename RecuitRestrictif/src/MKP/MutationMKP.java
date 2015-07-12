package MKP;

import java.util.ArrayList;

import modele.Etat;
import modele.Probleme;
import mutation.IMutation;
import mutation.MutationElementaire;

public class MutationMKP extends IMutation {
	int indice;//indice de ce qu'on ajoute
	int indice2;//indice de ce qu'on enleve
	int typeOfMut; //+1 si on ajoute, 0 si on échange, -1 si on retire
	public MutationMKP(Remplissage r){
		ArrayList<MutationElementaire> liste = new ArrayList<MutationElementaire>();
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
			liste.add(new MutationElementaireMKP(new ElementMKP(elt.getObjet(),true),k));
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
				liste.add(new MutationElementaireMKP(new ElementMKP(elt.getObjet(),true),k));
				liste.add(new MutationElementaireMKP(new ElementMKP(elt2.getObjet(),false),k2));
			} else {
				//et enfin, si c'est pas possible on va tenter de retirer k2 (nécessaire si on souhaite réduire le nb d'objets dans le sac)
				this.indice2 = k2;
				this.typeOfMut = -1;
				liste.add(new MutationElementaireMKP(new ElementMKP(elt2.getObjet(),false),k2));
			}
		}
		this.listeMutations = liste;
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
	public void faire(Probleme p,Etat e) {
		Remplissage r = (Remplissage) e;
		int m = r.getInstance().getNombreSacs();
		if (this.typeOfMut == 1){
			ElementMKP elt =(ElementMKP) r.getListe().get(this.indice);
			elt.changeAppartenance();
			for(int i = 0; i < m; i++){
				r.getPoids()[i] += elt.getObjet().getWeight()[i];
			}
		}
		if (this.typeOfMut == -1){
			ElementMKP elt2 = (ElementMKP) r.getListe().get(this.indice2);
			elt2.changeAppartenance();
			for(int i = 0; i < m; i++){
				r.getPoids()[i] -= elt2.getObjet().getWeight()[i];
			}
		}
		if (this.typeOfMut == 0){
			ElementMKP elt = (ElementMKP) r.getListe().get(this.indice);
			ElementMKP elt2 =(ElementMKP)  r.getListe().get(this.indice2);
			elt.changeAppartenance();
			elt2.changeAppartenance();
			for(int i = 0; i < m; i++){
				r.getPoids()[i] += elt.getObjet().getWeight()[i] - elt2.getObjet().getWeight()[i];
			}
		}
		
		
	}

	public double calculerdeltaEp(Probleme p,Etat e) {
		Remplissage r = (Remplissage) e;
		if (this.typeOfMut == 1){
			ElementMKP elt =(ElementMKP) r.getListe().get(this.indice);
			return -elt.getObjet().getUtility();
		} 
		if (this.typeOfMut == -1){
			ElementMKP elt2 =(ElementMKP) r.getListe().get(this.indice2);
			return elt2.getObjet().getUtility();
		}
		if (this.typeOfMut == 0){
			ElementMKP elt =(ElementMKP) r.getListe().get(this.indice);
			ElementMKP elt2 =(ElementMKP) r.getListe().get(this.indice2);
			return elt2.getObjet().getUtility()-elt.getObjet().getUtility();
		} 
		return 0;
	}


	public void maj(Probleme p,Etat e) {
		Remplissage r = (Remplissage) e;
		ArrayList<MutationElementaire> liste = new ArrayList<MutationElementaire>();
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
			liste.add(new MutationElementaireMKP(new ElementMKP(elt.getObjet(),true),k));
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
				liste.add(new MutationElementaireMKP(new ElementMKP(elt.getObjet(),true),k));
				liste.add(new MutationElementaireMKP(new ElementMKP(elt2.getObjet(),false),k2));
			} else {
				//et enfin, si c'est pas possible on va tenter de retirer k2 (nécessaire si on souhaite réduire le nb d'objets dans le sac)
				this.indice2 = k2;
				this.typeOfMut = -1;
				liste.add(new MutationElementaireMKP(new ElementMKP(elt2.getObjet(),false),k2));
			}
		}
		this.listeMutations = liste;
	}



	@Override
	public double calculerdeltaSpins(Probleme p, Etat e) {
		double cpt=0;
		Remplissage r = (Remplissage) e;
		int n = r.getListe().size();
		Remplissage preced=(Remplissage) e.getPrevious();
		Remplissage next=(Remplissage) e.getNext();
		if (this.typeOfMut == 1){//cas ou on ajoute juste le obj[indice] au sac
			
			int num = ((ElementMKP)r.getListe().get(this.indice)).getObjet().getNumero();
			int index = 0;
			while(index < n && ((ElementMKP)next.getListe().get(index)).getObjet().getNumero() != num){
				index++;
			}
			if(((ElementMKP)next.getListe().get(index)).getAppartenance()){
				cpt++;
			} else {
				cpt--;
			}
			index = 0;
			while(index < n && ((ElementMKP)preced.getListe().get(index)).getObjet().getNumero() != num){
				index++;
			}
			if(((ElementMKP)preced.getListe().get(index)).getAppartenance()){
				cpt++;
			} else {
				cpt--;
			}
			return cpt;
		} 
		if (this.typeOfMut == -1){//on enleve obj[indice2] du sac
			
			int num = ((ElementMKP)r.getListe().get(this.indice2)).getObjet().getNumero();
			int index = 0;
			while(index < n && ((ElementMKP)next.getListe().get(index)).getObjet().getNumero() != num){
				index++;
			}
			if(((ElementMKP)next.getListe().get(index)).getAppartenance()){
				cpt--;
			} else {
				cpt++;
			}
			index = 0;
			while(index < n && ((ElementMKP)preced.getListe().get(index)).getObjet().getNumero() != num){
				index++;
			}
			if(((ElementMKP)preced.getListe().get(index)).getAppartenance()){
				cpt--;
			} else {
				cpt++;
			}
			return cpt;
		}
		if (this.typeOfMut == 0){//cas ou on ajoute le obj[indice] au sac et on enleve obj[indice2]
			
			int num1 = ((ElementMKP)r.getListe().get(this.indice)).getObjet().getNumero();
			int num2 = ((ElementMKP)r.getListe().get(this.indice2)).getObjet().getNumero();
			int index1 = 0;
			while(index1 < n && ((ElementMKP)next.getListe().get(index1)).getObjet().getNumero() != num1){
				index1++;
			}
			if(((ElementMKP)next.getListe().get(index1)).getAppartenance()){
				cpt++;
			} else {
				cpt--;
			}
			int index2 = 0;
			while(index2 < n && ((ElementMKP)preced.getListe().get(index2)).getObjet().getNumero() != num1){
				index2++;
			}
			if(((ElementMKP)preced.getListe().get(index2)).getAppartenance()){
				cpt++;
			} else {
				cpt--;
			}
			int index3 = 0;
			while(index3 < n && ((ElementMKP)next.getListe().get(index3)).getObjet().getNumero() != num2){
				index3++;
			}
			if(((ElementMKP)next.getListe().get(index3)).getAppartenance()){
				cpt--;
			} else {
				cpt++;
			}
			int index4 = 0;
			while(index4 < n && ((ElementMKP)next.getListe().get(index4)).getObjet().getNumero() != num2){
				index4++;
			}
			if(((ElementMKP)preced.getListe().get(index4)).getAppartenance()){
				cpt--;
			} else {
				cpt++;
			}
			return cpt;
		} 
		return 0;
	}


	

	
}
