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
			ins = Translator.donneInstance("C:/Users/Baptiste/Desktop/RecuitQuantique/RTI_k3_n100_m429/RTI_k3_n100_m429_12.cnf");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int n = ins.getNombreClauses() ;
		int nombreIterations = 10*n*n;
		
		
		 //       Test Recuit
		
		
		int cpt = 0;
		
		try {
			for (int i = 0; i < 10; i++){
				Particulesat p = Particulesat.initialise(nombreIterations,ins,0.8);
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
