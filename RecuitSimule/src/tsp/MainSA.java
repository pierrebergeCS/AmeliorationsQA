package tsp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import parametres.ParametreurT;
import parametres.Temperature;
import tsp.parser.TSPParser;
import tsp.parser.Writer;
import recuit.Recuit;
import modele.Etat;

public class MainSA {
	
	public static void main(String[] args) throws FileNotFoundException{
		
		Graphe g = new Graphe(TSPParser.donneMatrice("C:/Users/Pierre/Desktop/benchmark/brazil58.tsp"));
		int n = g.nombreDeNoeuds();
		int nombreIterations = 100*n*n;
		
		
		 //       Test Recuit
		
		PrintWriter sortie = new PrintWriter("test.txt");
		int cpt = 0;
		
		try {
	
			for (int i = 0; i < 10; i++){
				Routage r = new Routage(g);
				TwoOptMove m = new TwoOptMove(new Routage(g));
				double tempDepart = ParametreurT.parametreurRecuit(r,m).get(100);
				Temperature temp = new Temperature(tempDepart,0.0);
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
