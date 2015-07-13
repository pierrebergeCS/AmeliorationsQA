package MKP;

import java.util.ArrayList;

import modele.Element;
import modele.Etat;

public class Remplissage extends Etat {
	Instance ins;
	int[] poids;// poids de chaque sac. Pour savoir si on d�passe la contrainte
	
	
	//On cr�e un �tat MKP avec les sacs � dos vides
	//Pourra �tre am�lior� par la suite biens�r
	public Remplissage(Instance ins){
		int n = ins.getNombreObjets();
		ArrayList<Element> l = new ArrayList<Element>();
		for (int i = 0; i < n; i++){
			l.add(new ElementMKP(ins.getObj()[i],false));
		}
		this.ins = ins;
		this.poids = new int[ins.getNombreSacs()];
		this.setListe(l);
	}
	
	public Remplissage(Instance ins, int[] poids,  ArrayList<Element> l){
		this.ins = ins;
		this.poids = poids;
		this.setListe(l);
	}
	
	public Remplissage(ParticuleMKP particuleMKP) {
		this.ins=particuleMKP.getIns();
		int n = ins.getNombreObjets();
		ArrayList<Element> l = new ArrayList<Element>();
		for (int i = 0; i < n; i++){
			l.add(new ElementMKP(ins.getObj()[i],false));
		}
		this.poids = new int[ins.getNombreSacs()];
		this.setListe(l);
		
	}
	
	public static Remplissage glouton(Instance ins){
		int n = ins.getNombreObjets();
		Remplissage r = new Remplissage(ins);
		boolean estPlein = false;
		int cpt = 0;
		int k = -1;
		while (!estPlein && cpt < n){
			while (k < 0 || ((ElementMKP)r.getListe().get(k)).getAppartenance()){
				k = (int) (Math.random()*n);
			}
			for (int i = 0; i < ins.getNombreSacs(); i++){
				if (r.getPoids()[i] + ((ElementMKP)r.getListe().get(k)).getObjet().getWeight()[i] > ins.getCapacite()[i]){
					return r;
				}
				r.getPoids()[i]+=((ElementMKP)r.getListe().get(k)).getObjet().getWeight()[i];
			}
			((ElementMKP)r.getListe().get(k)).changeAppartenance();
			cpt++;
		}
		return r;
	}

	public Instance getInstance(){
		return this.ins;
	}
	
	public int[] getPoids(){
		return poids;
	}

	@Override
	public Etat clone() {
		ArrayList<Element> lC = new ArrayList<Element>();
		for (int i = 0; i < this.ins.getNombreObjets(); i++){
			lC.add(((ElementMKP)this.getListe().get(i)).clone());
		}
		
		int[] poids = new int[ins.getNombreSacs()];
		for (int j = 0; j < this.ins.getNombreSacs(); j++){
			poids[j] = this.poids[j];
		}
		Remplissage r = new Remplissage(this.ins,poids,lC);
		r.setEnergie(this.getEnergie());
		return r;
	}

	@Override
	public double majEnergie() {
		int n = ins.getNombreObjets();
		int cpt = 0;
		//On somme les utilit�s des objets qui remplissent l'ensemble des sacs
		for (int i = 0; i < n; i++){
			ElementMKP elt = (ElementMKP) this.getListe().get(i);
			if (elt.getAppartenance()) cpt+=elt.getObjet().getUtility();
		}
		return cpt;
	}

	@Override
	//Renvoie le nombre d'objets qui sont contenus dans les sacs des deux �tats
	public int distanceIsing(Etat e) {
		int n = ins.getNombreObjets();
		int cpt = 0;
		for (int i = 0; i < n; i++){
			ElementMKP elt1 = (ElementMKP) this.getListe().get(i);
			for (int j = 0; j < n; j++){
				ElementMKP elt2 = (ElementMKP) e.getListe().get(j);
				if(elt1.getObjet().getNumero() == elt2.getObjet().getNumero()  && elt1.getAppartenance() == elt2.getAppartenance()) cpt++;
			}
		}
		return cpt;
	}

	@Override
	public double getResultat() {
		return this.getEnergie();
	}

}
