package sat3;

import java.util.ArrayList;

import modele.Element;
import modele.RedondancesParticuleGeneral;

public class RedondancesParticuleSAT extends RedondancesParticuleGeneral {
	public int[] redondances;
	//TRES IMPORTANT : La ligne i correspond à la variable xi, denombrant le nombre d'assignations true dans la particule
	
	public RedondancesParticuleSAT(ArrayList<Element> elementsParticule,
			ArrayList<Element> elementsFrequents) {
		super(elementsParticule, elementsFrequents);
		// TODO Auto-generated constructor stub
	}
	
	public int[] getTab(){
		return this.redondances;
	}

}
