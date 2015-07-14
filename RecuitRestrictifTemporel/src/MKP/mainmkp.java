package MKP;

import java.io.IOException;
import java.io.PrintWriter;

import parametres.ParametreGammaLin;
import parametres.ParametreurT;
import parametres.Temperature;
import recuit.Recuit;
import tsp.parser.Writer;

//--------------------RECUIT-QUANTIQUE-------------------------//

public class mainmkp {

	public static void main(String[] args) throws IOException {
		Instance ins = TransposeLator.traduit("C:/Users/Pierre/Desktop/benchmark/kp/weish21.dat");
		int n = ins.getNombreObjets();
		int nombreEtat = 10;
		int nombreIterations = n*n;
		
		
		 //       Test Recuit
		
		PrintWriter sortie = new PrintWriter("test.txt");
		int cpt = 0;
		int sum = 0;
		
		try {
	
			for (int i = 0; i < 10; i++){
				ParticuleMKP p = ParticuleMKP.initialise(nombreEtat,ins,0.99);
				MutationMKP m = new MutationMKP(new Remplissage(ins));
				RedondancesParticuleMKP red = new RedondancesParticuleMKP(p);
				double temp = ParametreurT.parametreurRecuit(p,m,1000).get(50);
				System.out.println("temperature :" + temp);
				ParametreGammaLin gamma = new ParametreGammaLin(1.0,1.0/nombreIterations,0.0);
				double result = Recuit.solution(p,m,red,nombreIterations,1,1,10,temp,gamma);
				sum += result;
				Writer.ecriture(0,result,sortie);
			}
			sortie.close();
			System.out.println("sum :" + sum/10);
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
