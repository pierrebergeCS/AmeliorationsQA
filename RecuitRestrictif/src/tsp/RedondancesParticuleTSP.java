package tsp;

import java.util.ArrayList;

import modele.RedondancesParticuleGeneral;

public class RedondancesParticuleTSP extends RedondancesParticuleGeneral {
	
	int[][] m;
	
	public RedondancesParticuleTSP(int[][] tab){
		super(null,null);
		this.m = tab;
	}
	
	public int[][] getTab(){
		return this.m;
	}
	
	public ArrayList<Arete> getElementsFrequents(int nbApparitions){
		ArrayList<Arete> eltsFrequents =new ArrayList<Arete>();
		int n = this.m.length;
		for (int i = 0; i < (n-1); i++){
			for (int j = (i+1); j < n; j++){
				if (m[i][j] > nbApparitions) eltsFrequents.add(new Arete(i,j));
			}
		}
		return eltsFrequents;
	}

}
