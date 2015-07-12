package MKP;

import java.util.ArrayList;

import modele.Element;
import modele.RedondancesParticuleGeneral;

public class RedondancesParticuleMKP extends RedondancesParticuleGeneral {

	int[] m;
	public RedondancesParticuleMKP(ArrayList<Element> elementsParticule,
			ArrayList<Element> elementsFrequents) {
		super(elementsParticule, elementsFrequents);
		// TODO Auto-generated constructor stub
	}
	public RedondancesParticuleMKP(ParticuleMKP p){
		super(null,null);
		int n = p.getIns().getNombreObjets();
		int[]m = new int[n];
		int nombreEtat = p.nombreEtat();
		for (int k = 0; k < nombreEtat; k++){
			ArrayList<Element> l = p.getEtat().get(k).getListe();
			for (int i = 0; i < l.size(); i++){
				ElementMKP a = (ElementMKP) l.get(i);
				if(a.getAppartenance()){
				int numero=a.getObjet().getNumero();
				m[numero]++;
				}
			}
		}
		this.m = m;
	}
	
	public int[] getTab(){
		return this.m;
	}
	

}
