package sat3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import parametres.Temperature;
import recuit.Recuit;
import tsp.parser.TSPParser;
import tsp.parser.Writer;

public class MainSat {

	public static void main(String[] args) throws FileNotFoundException{
		
		int cpt=0;
		double rescum=0;
		Instancesat ins = null;
		PrintWriter sortie = null;
		for(int inst=0;inst<1;inst++){
			int cptlocal=0;
			System.out.println("Instance n° "+ inst);
		try {
			ins = Translator.donneInstance("C:/Users/Pierre/Desktop/benchmark/sat/uf200-010.cnf");
			sortie = new PrintWriter("test.txt");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int n = ins.getNbvar() ;
		System.out.println(n);
		int nombreIterations = 100*n*n;
		Temperature temp = new Temperature(1.0,0.0);
		 //       Test Recuit

		try {
			for (int i = 0; i < 1; i++){
				EtatSat esat = new EtatSat(ins);
				MutationSat m = new MutationSat(new EtatSat(ins));
				double s=Recuit.solution(esat,m,nombreIterations,temp);
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
