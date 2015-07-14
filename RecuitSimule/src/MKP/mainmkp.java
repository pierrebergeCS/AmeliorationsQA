package MKP;

import java.io.IOException;
import java.io.PrintWriter;

import parametres.ParametreurT;
import parametres.Temperature;
import parametres.TemperatureLin;
import recuit.Recuit;
import tsp.parser.Writer;

//----------------------RECUIT-SIMULE--------------------------//

public class mainmkp {

	public static void main(String[] args) throws IOException {
		Instance ins = TransposeLator.traduit("C:/Users/Pierre/Desktop/benchmark/kp/OR30x500-0.75_1.dat");
		int n = ins.getNombreObjets();
		int nombreIterations = 100*n*n;
		
		
		 //       Test Recuit
		
		PrintWriter sortie = new PrintWriter("test.txt");
		int cpt = 0;
		
		try {
	
			for (int i = 0; i < 100; i++){
				Remplissage r = new Remplissage(ins);
				MutationMKP m = new MutationMKP(r);
				double tempDepart = 100.0;
				Temperature temp = new TemperatureLin(tempDepart,0.0);
				Writer.ecriture(0,Recuit.solution(r,m,nombreIterations,temp),sortie);
			}
			sortie.close();
			System.out.println(cpt);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
