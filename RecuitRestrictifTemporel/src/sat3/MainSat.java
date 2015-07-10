package sat3;

import java.io.IOException;
import java.io.PrintWriter;

import parametres.ParametreGammaLin;
import recuit.Recuit;
import tsp.Graphe;
import tsp.ParticuleTSP;
import tsp.RedondancesParticuleTSP;
import tsp.Routage;
import tsp.TwoOptMove;
import tsp.parser.TSPParser;

public class MainSat {

	public static void main(String[] args){
		
		int nombreEtat = 20;
		Instancesat ins = null;
		try {
			ins = Translator.donneInstance("C:/Users/ameliorationqa/Desktop/benchmark/SAT/uf200-860/uf200-010.cnf");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("TEST DU RestrictiveQA avec blocage 1,5 uf200-010");
		
		int n = ins.getNbvar() ;
		System.out.println(n);
		int nombreIterations = 250*n;//on est des fous pas tarés non plus
		
		double temperature = 0.1;
		
		
		
		 //       Test Recuit
		
		
		int cpt = 0;
		
		try {
			for (int i = 0; i < 250; i++){
				ParametreGammaLin gamma = new ParametreGammaLin(10.0,10.0/(nombreIterations+1),0.0);
				PrintWriter sortie = new PrintWriter("QA_uf200-010_"+(500+i)+".txt");
				Particulesat p = Particulesat.initialise(nombreEtat,ins,2.0);
				MutationSat m = new MutationSat(new EtatSat(ins));
				RedondancesParticuleSAT red = new RedondancesParticuleSAT(p);
				Recuit.solution(p,m,red,nombreIterations,1,1,0,temperature,gamma,sortie);
				sortie.close();
			}
			System.out.println(cpt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
