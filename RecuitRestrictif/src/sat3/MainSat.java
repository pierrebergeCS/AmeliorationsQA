package sat3;

import java.io.IOException;

import recuit.Recuit;
import tsp.Graphe;
import tsp.ParticuleTSP;
import tsp.RedondancesParticuleTSP;
import tsp.Routage;
import tsp.TwoOptMove;
import tsp.parser.TSPParser;

public class MainSat {

	public static void main(String[] args){
		
		int nombreEtat = 10;
		Instancesat ins = null;
		try {
			ins = Translator.donneInstance("C:/Users/Baptiste/Desktop/RecuitQuantique/uf20-91/uf20-013.cnf");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int n = ins.getNombreClauses() ;
		System.out.println(n);
		int nombreIterations = 10*n;//on est des fous pas tarés non plus
		
		
		 //       Test Recuit
		
		
		int cpt = 0;
		
		try {
			for (int i = 0; i < 10; i++){
				Particulesat p = Particulesat.initialise(nombreEtat,ins,1.0);
				MutationSat m = new MutationSat(new EtatSat(ins));
				RedondancesParticuleSAT red = new RedondancesParticuleSAT(p);
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
