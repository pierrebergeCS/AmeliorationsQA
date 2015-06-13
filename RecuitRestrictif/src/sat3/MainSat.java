package sat3;

import java.io.IOException;

import recuit.Recuit;
import recuit.RecuitA;
import tsp.Graphe;
import tsp.ParticuleTSP;
import tsp.RedondancesParticuleTSP;
import tsp.Routage;
import tsp.TwoOptMove;
import tsp.parser.TSPParser;

public class MainSat {

	public static void main(String[] args){
		
		int cpt=0;
		double rescum=0;
		int nombreEtat = 20;
		Instancesat ins = null;
		
		for(int inst=127;inst<128;inst++){
			int cptlocal=0;
			System.out.println("Instance n° "+ inst);
		try {
			ins = Translator.donneInstance("C:/Users/Baptiste/Desktop/RecuitQuantique/CBS_k3_n100_m449_b90/CBS_k3_n100_m449_b90_"+inst+".cnf");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int n = ins.getNbvar() ;
		System.out.println(n);
		int nombreIterations = 10*n*n;
		
		
		 //       Test Recuit

		try {
			for (int i = 0; i < 100; i++){
				Particulesat p = Particulesat.initialise(nombreEtat,ins,2.0);
				MutationSat m = new MutationSat(new EtatSat(ins));
				RedondancesParticuleSAT red = new RedondancesParticuleSAT(p);
				double s=Recuit.solution(p,m,red,nombreIterations,1,1);
				if(s!=0){
				cpt++;	
				rescum+=s;
				}
				
			}
			System.out.println("taux d'erreur local :"+((double) cptlocal)/100);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		System.out.println("taux d'erreur :"+((double) cpt)/100);
		System.out.println("erreurs moyenne :"+rescum/100/1000);	
	
	
	}

}
