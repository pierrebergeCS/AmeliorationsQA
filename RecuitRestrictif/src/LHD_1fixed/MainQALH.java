package LHD_1fixed;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import parametres.ParametreGamma;
import parametres.ParametreGammaExp;
import parametres.ParametreurGamma;
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
		int nombreIterations = 10000;
		EcDists ec = new EcDists();
		
		 //       Test Recuit
		
		PrintWriter sortie = new PrintWriter("test.txt");
		PrintWriter Ec = new PrintWriter("EcDists_1fixed_9_10_10P");
		double sum = 0;
		double var = 0;
		
		int[] tab = {0,2,6,5,5,3,3,9,0};
		Croix c = new Croix(tab);
		System.out.println("taille " + n + " dim " + d);
		
		try {
			for (int i = 0; i < 1; i++){
				ParticuleLH p = ParticuleLH.initialise(n,d,f,ec,nombreEtat,c,1.2);
				ImprovedMutationLH m = new ImprovedMutationLH(new Grille(f,n,d,c));
				RedondancesParticuleLH red = new RedondancesParticuleLH();
				List<Double> listeDelta = ParametreurT.parametreurRecuit(p,m,10000);
				double temp = listeDelta.get(20);
				double Gammafin = listeDelta.get(200)/0.01;
				double GammaDep = listeDelta.get(1000);
				double facteur = Math.pow(Gammafin/GammaDep,1.0/nombreIterations);
				ParametreGamma gamma = new ParametreGammaExp(GammaDep,facteur,Gammafin);
				double result = Recuit.solution(p,m,red,nombreIterations,1,1,temp,gamma,Ec);
				sum +=result;
				var += result*result;
				Writer.ecriture(i,result,sortie);
			}
			Ec.close();
			sortie.close();
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
