package tsp;

import java.io.IOException;
import java.util.ArrayList;

import parametres.ParametreGammaLin;
import parametres.ParametreurT;
import tsp.parser.TSPParser;
import recuit.Recuit;
import modele.Etat;
import modele.Probleme;

public class Main {
	
	public static void main(String[] args){
		
		int nombreEtat = 10;
		Graphe g = new Graphe(TSPParser.donneMatrice("C:/Users/Baptiste/Desktop/brazil58.tsp"));
		int n = g.nombreDeNoeuds();
		int nombreIterations = 10*n*n;
		
		 //       Test Recuit
		
		
		int cpt = 0;
		ParametreGammaLin gamma = new ParametreGammaLin(10.0,10.0/(nombreIterations+1),0.0);
		try {
			for (int i = 0; i < 1000; i++){
				ParticuleTSP p = ParticuleTSP.initialise(nombreEtat,g,0.8);
				TwoOptMove m = new TwoOptMove(new Routage(g));
				RedondancesParticuleTSP red = new RedondancesParticuleTSP(p);
				double temp = ParametreurT.parametreurRecuit(p,m,10000).get(500);
				cpt+=Recuit.solution(p,m,red,nombreIterations,1,1,10,temp,gamma);
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
