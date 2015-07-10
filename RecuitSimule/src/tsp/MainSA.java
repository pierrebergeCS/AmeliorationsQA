package tsp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import parametres.ParametreurT;
import parametres.Temperature;
import parametres.TemperatureLin;
import tsp.parser.TSPParser;
import tsp.parser.Writer;
import recuit.Recuit;
import modele.Etat;

public class MainSA {
	
	public static void main(String[] args) throws FileNotFoundException{
		
		Graphe g = new Graphe(TSPParser.donneMatrice("C:/Users/Baptiste/Desktop/a280.tsp"));
		int n = g.nombreDeNoeuds();
		int nombreIterations = 100*n*n;
		
		
		 //       Test Recuit
		

		int cpt = 0;
		
		try {
	
			for (int i = 0; i < 1000; i++){
				PrintWriter sortie = new PrintWriter("C:/Users/Baptiste/Desktop/ResultatsVM/SA_a280_"+(i)+".txt");
				Routage r = new Routage(g);
				TwoOptMove m = new TwoOptMove(new Routage(g));
				double tempDepart = ParametreurT.parametreurRecuit(r,m,1000).get(100);
				Temperature temp = new TemperatureLin(tempDepart,0.0);
				Recuit.solution(r,m,nombreIterations,temp,sortie);
				sortie.close();
			}
			
			System.out.println(cpt);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
