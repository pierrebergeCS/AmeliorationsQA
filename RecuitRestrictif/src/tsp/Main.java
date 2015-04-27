package tsp;

import java.io.IOException;
import java.util.ArrayList;

import tsp.parser.TSPParser;
import recuit.Recuit;
import modele.Etat;
import modele.Probleme;

public class Main {
	
	public static void main(String[] args){
		
		int nombreEtat = 20;
		Graphe g = new Graphe(TSPParser.donneMatrice("C:/Users/Pierre/Desktop/benchmark/a280.tsp"));
		int n = g.nombreDeNoeuds();
		int nombreIterations = 10*n*n;
		
		
		 //       Test Recuit
		
		
		int cpt = 0;
		
		try {
			for (int i = 0; i < 100; i++){
				ParticuleTSP p = ParticuleTSP.initialise(nombreEtat,g,0.80);
				TwoOptMove m = new TwoOptMove(new Routage(g));
				RedondancesParticuleTSP red = new RedondancesParticuleTSP(p);
				Recuit.solution(p,m,red,nombreIterations,1,1);
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
