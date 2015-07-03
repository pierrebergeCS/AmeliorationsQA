package LHD;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import parametres.ParametreurT;
import recuit.Recuit;
import tsp.Graphe;
import tsp.ParticuleTSP;
import tsp.RedondancesParticuleTSP;
import tsp.Routage;
import tsp.TwoOptMove;
import tsp.parser.TSPParser;
import tsp.parser.Writer;

public class MainQALH {

	public static void main(String[] args) throws FileNotFoundException {
		
		int n = 10;
		int d = 9;
		Phi f = new Phi(5);
		int nombreEtat = 10;
		int nombreIterations = 10000*d;
		EcDists ec = new EcDists();
		
		double GammaDep = 2.0;
		
		 //       Test Recuit
		
		PrintWriter sortie = new PrintWriter("9_10_QA_Ecdists_10P_phi5.txt");
		double sum = 0;
		double var = 0;
		
		System.out.println("taille " + n + " dim " + d);
		
		try {
			for (int i = 0; i < 200; i++){
				ParticuleLH p = ParticuleLH.initialise(n,d,f,ec,nombreEtat,1.2);
				MutationLH m = new MutationLH(new Grille(f,n,d));
				RedondancesParticuleLH red = new RedondancesParticuleLH();
				double temp = ParametreurT.parametreurRecuit(p,m,10000).get(20);
				double result = Recuit.solution(p,m,red,nombreIterations,1,1,temp,GammaDep);
				sum +=result;
				var += result*result;
				Writer.ecriture(i,result,sortie);
			}
			System.out.println("result :" + (sum/200.0));
			System.out.println("var :" + ((var/200.0)-(sum/200.0)*(sum/200.0)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
