package MKP;

import java.util.ArrayList;

import modele.Etat;

public class Remplissage extends Etat {
	Instance ins;
	int[] poids;// poids de chaque sac. Pour savoir si on dépasse la contrainte
	ArrayList<ElementMKP> liste;
	
	
	//On crée un état MKP avec les sacs à dos vides
	//Pourra être amélioré par la suite biensûr
	public Remplissage(Instance ins){
		int n = ins.getNombreObjets();
		ArrayList<ElementMKP> l = new ArrayList<ElementMKP>();
		for (int i = 0; i < n; i++){
			l.add(new ElementMKP(ins.getObj()[i],false));
		}
		this.ins = ins;
		this.poids = new int[ins.getNombreSacs()];
		this.setListe(l);
	}
	
	public Remplissage(Instance ins, int[] poids,  ArrayList<ElementMKP> l){
		this.ins = ins;
		this.poids = poids;
		this.setListe(l);
	}
	
	public Instance getInstance(){
		return this.ins;
	}
	
	public int[] getPoids(){
		return poids;
	}
	
	public ArrayList<ElementMKP> getListe(){
		return this.liste;
	}
	
	public void setListe(ArrayList<ElementMKP> l){
		this.liste = l;
	}

	@Override
	public Etat clone() {
		ArrayList<ElementMKP> lC = new ArrayList<ElementMKP>();
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
		//On somme les utilités des objets qui remplissent l'ensemble des sacs
		for (int i = 0; i < n; i++){
			ElementMKP elt = (ElementMKP) this.getListe().get(i);
			if (elt.getAppartenance()) cpt+=elt.getObjet().getUtility();
		}
		return -cpt;
	}

	@Override
	public double getResultat() {
		return this.getEnergie();
	}

	@Override
	public void maj() {
		int n = this.ins.getNombreObjets();
		ArrayList<ElementMKP> l = new ArrayList<ElementMKP>();
		for (int i = 0; i < n; i++){
			l.add(new ElementMKP(this.ins.getObj()[i],false));
		}
		this.poids = new int[this.ins.getNombreSacs()];
		this.setListe(l);
	}

}
