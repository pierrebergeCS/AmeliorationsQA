package LHD;

import java.io.IOException;

import parametres.ParametreurT;
import recuit.Recuit;
import tsp.Graphe;
import tsp.ParticuleTSP;
import tsp.RedondancesParticuleTSP;
import tsp.Routage;
import tsp.TwoOptMove;
import tsp.parser.TSPParser;

public class MainQALH {

	public static void main(String[] args) {
		
		int n = 10;
		int d = 9;
		DMin f = new DMin();
		int nombreEtat = 10;
		int nombreIterations = 10000*d;
		
		ParticuleLH p2 = ParticuleLH.initialise(n,d,f,nombreEtat,1.2);
		MutationLH m2 = new MutationLH(new Grille(f,n,d));
		double GammaDep = 2.0;
		
		// Tests mutations, deltas,...
		
		 //       Test Recuit
		
		System.out.println("taille " + n + " dim " + d);
		
		int cpt = 0;
		
		try {
			for (int i = 0; i < 10; i++){
				ParticuleLH p = ParticuleLH.initialise(n,d,f,nombreEtat,1.2);
				MutationLH m = new MutationLH(new Grille(f,n,d));
				RedondancesParticuleLH red = new RedondancesParticuleLH();
				double temp = ParametreurT.parametreurRecuit(p2,m2).get(5);
				Recuit.solution(p,m,red,nombreIterations,1,1,temp,GammaDep);
			}
			System.out.println(cpt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
