package sat3;

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

	public static void main(String[] args) throws IOException{
		
		int nombreEtat = 40;
		Instancesat ins = null;
		PrintWriter sortie = null;
		PrintWriter pc = null;
		ins = Translator.donneInstance("C:/Users/Baptiste/Desktop/f2000.cnf");
		int n = ins.getNombreClauses() ;
		System.out.println(n);
		int nombreIterations = 125*n;//on est des fous pas tarés non plus
		
		
		 //       Test Recuit
		
		
		int cpt = 0;
		
		try {
			for (int i = 0; i < 250; i++){
				try {
					
					sortie = new PrintWriter("40Bloc_2K"+(i)+".txt");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Particulesat p = Particulesat.initialise(nombreEtat,ins,1.0);
				MutationSat m = new MutationSat(new EtatSat(ins));
				RedondancesParticuleSAT red = new RedondancesParticuleSAT(p);
				System.out.println(Recuit.solution(p,m,red,nombreIterations,1,1,sortie));
				
				sortie.close();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
