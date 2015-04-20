package sat3;

import java.util.ArrayList;

import modele.Element;
import modele.RedondancesParticuleGeneral;

public class RedondancesParticuleSAT extends RedondancesParticuleGeneral {
	public int[][] redondances;
	
	public RedondancesParticuleSAT(ArrayList<Element> elementsParticule,
			ArrayList<Element> elementsFrequents) {
		super(elementsParticule, elementsFrequents);
		// TODO Auto-generated constructor stub
	}

}
