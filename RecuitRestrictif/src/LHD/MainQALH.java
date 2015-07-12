package LHD;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import modele.Element;
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
		FonctionEval f = new Phi(5);
		int nombreEtat = 10;
		int nombreIterations = 10000;
		EcVector ec = new EcVector();
		
		// Tests Ec
		
		/*int[] t1 = {0,3};
		int[] t2 = {1,6};
		int[] t3 = {2,1};
		int[] t4 = {3,4};
		int[] t5 = {4,7};
		int[] t6 = {5,0};
		int[] t7 = {6,5};
		int[] t8 = {7,2};
		int[] tt1 = {0,5};
		int[] tt2 = {1,2};
		int[] tt3 = {2,7};
		int[] tt4 = {3,0};
		int[] tt5 = {4,3};
		int[] tt6 = {5,6};
		int[] tt7 = {6,1};
		int[] tt8 = {7,4};
		ArrayList<Element> lC1 = new ArrayList<Element>();
		lC1.add(new Croix(t1));
		lC1.add(new Croix(t2));
		lC1.add(new Croix(t3));
		lC1.add(new Croix(t4));
		lC1.add(new Croix(t5));
		lC1.add(new Croix(t6));
		lC1.add(new Croix(t7));
		lC1.add(new Croix(t8));
		ArrayList<Element> lC2 = new ArrayList<Element>();
		lC2.add(new Croix(tt1));
		lC2.add(new Croix(tt2));
		lC2.add(new Croix(tt3));
		lC2.add(new Croix(tt4));
		lC2.add(new Croix(tt5));
		lC2.add(new Croix(tt6));
		lC2.add(new Croix(tt7));
		lC2.add(new Croix(tt8));
		Grille g1 = new Grille(f,lC1,8);
		Grille g2 = new Grille(f,lC2,8);
		System.out.println("egal :" + ec.distanceIsing(g1,g1));
		System.out.println("similar :" + ec.distanceIsing(g1,g2));
		System.out.println("random :" + ec.distanceIsing(new Grille(f,n,d),new Grille(f,n,d)));*/
		 //       Test Recuit
		
		PrintWriter sortie = new PrintWriter("test.txt");
		double sum = 0;
		double var = 0;
		
		System.out.println("taille " + n + " dim " + d);
		
		try {
			for (int i = 0; i < 10; i++){
				ParticuleLH p = ParticuleLH.initialise(n,d,f,ec,nombreEtat,1.2);
				ImprovedMutationLH m = new ImprovedMutationLH(new Grille(f,n,d));
				RedondancesParticuleLH red = new RedondancesParticuleLH();
				List<Double> listeDelta = ParametreurT.parametreurRecuit(p,m,10000);
				double temp = listeDelta.get(20);
				double Gammafin = listeDelta.get(200)*0.01;
				double GammaDep = listeDelta.get(200);
				double facteur = Math.pow(Gammafin/GammaDep,1.0/nombreIterations);
				ParametreGamma gamma = new ParametreGammaExp(GammaDep,facteur,Gammafin);
				double result = Recuit.solution(p,m,red,nombreIterations,1,1,temp,gamma);
				sum +=result;
				var += result*result;
				Writer.ecriture(i,result,sortie);
			}
			sortie.close();
			//System.out.println("result :" + (sum/200.0));
			//System.out.println("var :" + ((var/200.0)-(sum/200.0)*(sum/200.0)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
