package tsp;

import java.util.ArrayList;

import modele.Element;
import modele.RedondancesParticuleGeneral;

public class RedondancesParticuleTSP extends RedondancesParticuleGeneral {
	
	int[][] m;
	
	public RedondancesParticuleTSP(int[][] tab){
		super(null,null);
		this.m = tab;
	}
	
	public RedondancesParticuleTSP(ParticuleTSP p){
		super(null,null);
		int n = p.getGraphe().nombreDeNoeuds();
		int[][] m = new int[n][n];
		int nombreEtat = p.nombreEtat();
		for (int k = 0; k < nombreEtat; k++){
			ArrayList<Element> l = p.getEtat().get(k).getListe();
			for (int i = 0; i < l.size(); i++){
				Arete a = (Arete) l.get(i);
				if (a.getNoeud1() < a.getNoeud2()) m[a.getNoeud1()][a.getNoeud2()]++;
				if (a.getNoeud2() < a.getNoeud1()) m[a.getNoeud2()][a.getNoeud1()]++;
			}
		}
		this.m = m;
	}
	
	public int[][] getTab(){
		return this.m;
	}
	
	public void afficheTab(){
		int[][] M = this.m;
		for(int k =0;k<M.length;k++){
			for(int l =0;l<M.length;l++){
				System.out.print(M[k][l] + " , ");
			}
			System.out.println("");
		}
	}

}
