package MKP;

import java.util.ArrayList;

import modele.Element;
import modele.Etat;

public class Remplissage extends Etat {
	Instance ins;
	int[] poids;// poids de chaque sac. Pour savoir si on dépasse la contrainte
	
	
	//On crée un état MKP avec les sacs à dos vides
	//Pourra être amélioré par la suite biensûr
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
		return new Remplissage(this.ins,poids,lC);
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
		return cpt;
	}

	@Override
	//Renvoie le nombre d'objets qui sont contenus dans les sacs des deux états
	public int distanceIsing(Etat e) {
		int n = ins.getNombreObjets();
		int cpt = 0;
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				ElementMKP elt1 = (ElementMKP) this.getListe().get(i);
				ElementMKP elt2 = (ElementMKP) e.getListe().get(j);
				if(elt1.equals(elt2) && elt1.getAppartenance() == elt2.getAppartenance()) cpt++;
			}
		}
		return cpt;
	}

	@Override
	public double getResultat() {
		return this.getEnergie();
	}

}
