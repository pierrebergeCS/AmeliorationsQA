package MKP;

import java.io.IOException;
import java.io.PrintWriter;

import parametres.ParametreGammaLin;
import parametres.Temperature;
import recuit.Recuit;
import tsp.parser.Writer;

//--------------------RECUIT-QUANTIQUE-------------------------//

public class mainmkp {

	public static void main(String[] args) throws IOException {
		Instance ins = TransposeLator.traduit("C:/Users/Pierre/Desktop/benchmark/kp/weish01.dat");
		int n = ins.getNombreObjets();
		int nombreEtat = 10;
		int nombreIterations = n*n;
		
		
		 //       Test Recuit
		
		PrintWriter sortie = new PrintWriter("test.txt");
		int cpt = 0;
		
		try {
	
			for (int i = 0; i < 1; i++){
				ParticuleMKP p = ParticuleMKP.initialise(nombreEtat,ins,1.2);
				MutationMKP m = new MutationMKP(new Remplissage(ins));
				RedondancesParticuleMKP red = new RedondancesParticuleMKP(p);
				double temp = 10.0;
				ParametreGammaLin gamma = new ParametreGammaLin(10.0,10.0/nombreIterations,0.0);
				Writer.ecriture(0,Recuit.solution(p,m,red,nombreIterations,1,1,temp,gamma),sortie);
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
