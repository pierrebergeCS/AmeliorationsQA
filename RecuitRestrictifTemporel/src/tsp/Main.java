package tsp;

import java.io.IOException;
import java.io.PrintWriter;
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
		Graphe g = new Graphe(TSPParser.donneMatrice("C:/Users/Baptiste/Desktop/a280.tsp"));
		int n = g.nombreDeNoeuds();
		int nombreIterations = 10*n*n;
		
		 //       Test Recuit
		
		
		int cpt = 0;
		
		try {
			for (int i = 0; i < 1000; i++){
				PrintWriter sortie = new PrintWriter("C:/Users/Baptiste/Desktop/ResultatsVM/QA_a280_"+(i)+".txt");
				ParametreGammaLin gamma = new ParametreGammaLin(10.0,10.0/(nombreIterations+1),0.0);
				ParticuleTSP p = ParticuleTSP.initialise(nombreEtat,g,2.80);
				TwoOptMove m = new TwoOptMove(new Routage(g));
				RedondancesParticuleTSP red = new RedondancesParticuleTSP(p);
				double temp = 0.25;
				cpt+=Recuit.solution(p,m,red,nombreIterations,1,1,0,temp,gamma,sortie);
				
				sortie.close();
			}
			System.out.println(cpt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
