package sat3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import parametres.Temperature;
import parametres.TemperatureLin;
import recuit.Recuit;
import tsp.parser.Writer;

public class MainSat {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException{
		
		Instancesat ins = null;
		PrintWriter sortie = null;
		for(int inst=0;inst<1;inst++){
			int cptlocal=0;
			System.out.println("Instance n° "+ inst);
		try {
			ins = Translator.donneInstance("C:/Users/Baptiste/Desktop/uf200-010.cnf");
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
<<<<<<< HEAD
		int n = ins.getNombreClauses() ;
		System.out.println(n);
		int nombreIterations = 500000*n;
		for(int i =0;i<1;i++){
		
		
		
		Temperature temp = new Temperature(0.50,0.0);
=======
		int n = ins.getNbvar();
		System.out.println(n);
		int nombreIterations = 100*n*n;
		Temperature temp = new TemperatureLin(1.0,0.0);
>>>>>>> origin/master
		 //       Test Recuit

		try {
				EtatSat esat = new EtatSat(ins);
				MutationSat m = new MutationSat(new EtatSat(ins));
				double s=Recuit.solution(esat,m,nombreIterations,temp);
				if(s!=0){
					cptlocal++;
			
				}
				
			
			sortie.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		}
	
	
	}

}
