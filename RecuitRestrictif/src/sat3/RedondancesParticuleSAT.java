package sat3;

import java.util.ArrayList;

import modele.Element;
import modele.Etat;
import modele.RedondancesParticuleGeneral;

public class RedondancesParticuleSAT extends RedondancesParticuleGeneral {
	public int[] redondances;
	//TRES IMPORTANT : La ligne i correspond à la variable xi, denombrant le nombre d'assignations true dans la particule
	
	public RedondancesParticuleSAT(ArrayList<Element> elementsParticule,
			ArrayList<Element> elementsFrequents) {
		super(elementsParticule, elementsFrequents);
		// TODO Auto-generated constructor stub
	}
	
	public RedondancesParticuleSAT(Particulesat p) {
		super(null,null);
		int n = p.getnombreXi();
		redondances=new int[n];
		for(Etat e :p.getEtat()){
			EtatSat elt= (EtatSat) e;
			ArrayList<Element> l=elt.getListe();
			for(int i=0;i<l.size();i++){
				if(((ElementSat) l.get(i)).getassignation()){
					redondances[((ElementSat) l.get(i)).getxi()]+=1;
				}
			}
		}
	}

	public int[] getTab(){
		return this.redondances;
	}

}
