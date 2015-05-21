package sat3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import recuit.Recuit;
import tsp.Graphe;
import tsp.ParticuleTSP;
import tsp.RedondancesParticuleTSP;
import tsp.Routage;
import tsp.TwoOptMove;
import tsp.parser.TSPParser;
import tsp.parser.Writer;

public class MainSat {

	public static void main(String[] args) throws FileNotFoundException{
		
		int cpt=0;
		double rescum=0;
		int nombreEtat =40;
		Instancesat ins = null;
		PrintWriter sortie = null;
		for(int inst=0;inst<1;inst++){
			int cptlocal=0;
			System.out.println("Instance n° "+ inst);
		try {
			ins = Translator.donneInstance("C:/Users/ameliorationqa/Desktop/benchmark/SAT/f2000.cnf");
			sortie = new PrintWriter("2000var_1000nn_f1_blocageAfter5.txt");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int n = ins.getNbvar() ;
		System.out.println(n);
		int nombreIterations = 25*n*n;
		 //       Test Recuit

		try {
			for (int i = 0; i < 200; i++){
				Particulesat p = Particulesat.initialise(nombreEtat,ins,1.0); 
				MutationSat m = new MutationSat(new EtatSat(ins));
				RedondancesParticuleSAT red = new RedondancesParticuleSAT(p);
				double s=Recuit.solution(p,m,red,nombreIterations,1,1,0.5,0.01);
				Writer.ecriture(i,s,sortie);
				if(s!=0){
					cptlocal++;
				cpt++;	
				rescum+=s;
				}
				
			}
			sortie.close();
			System.out.println("taux d'erreur local :"+((double) cptlocal)/100);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		System.out.println("taux d'erreur :"+((double) cpt)/100/1000);
		System.out.println("erreurs moyenne :"+rescum/100/1000);	
	
	
	}

}
