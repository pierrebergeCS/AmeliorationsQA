package tsp;

import java.io.IOException;
import java.util.ArrayList;

import tsp.parser.TSPParser;
import recuit.Recuit;
import modele.Etat;

public class Main {
	
	public static void main(String[] args){
		
		int nombreEtat = 10;
		Graphe g = new Graphe(TSPParser.donneMatrice("C:/Users/Pierre/Desktop/benchmark/brazil58.tsp"));
		int n = g.nombreDeNoeuds();
		int nombreIterations = 10*n*n;
		
		ParticuleTSP p = ParticuleTSP.initialise(nombreEtat,g);
		TwoOptMove m = new TwoOptMove(new Routage(g));
		p.setFreq(0.8);
		try {
			Recuit.solution(p,m,nombreIterations,1,1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
