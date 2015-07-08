package tsp;

import java.io.IOException;
import java.util.ArrayList;

import tsp.parser.TSPParser;
import recuit.Recuit;
import modele.Etat;
import modele.Probleme;

public class Main {
	
	public static void main(String[] args){
		
		int nombreEtat = 10;
		Graphe g = new Graphe(TSPParser.donneMatrice("C:/Users/Baptiste/Desktop/RecuitQuantique/benchmark/brazil58.tsp"));
		int n = g.nombreDeNoeuds();
		int nombreIterations = 10*n*n;
		
		
		 //       Test Recuit
		
		
		int cpt = 0;
		long cpte=System.currentTimeMillis();
		try {
			
			double freq=0.8;
			int dureeBlock=(int) (100*1/freq);
			
			for (int i = 0; i < 10; i++){
				ParticuleTSP p = ParticuleTSP.initialise(nombreEtat,g,0.8);
				TwoOptMove m = new TwoOptMove(new Routage(g));
				RedondancesParticuleTSP red = new RedondancesParticuleTSP(p);
				Recuit.solution(p,m,red,nombreIterations,1,1,5,10,dureeBlock);
			}
			System.out.println(cpte-System.currentTimeMillis());
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
