package LHD;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import parametres.ParametreurT;
import parametres.Temperature;
import parametres.TemperatureExp;
import parametres.TemperatureLin;
import recuit.Recuit;
import tsp.parser.Writer;

public class MainSALH {

	public static void main(String[] args) throws FileNotFoundException {
		
		int n = 20;
		Phi f = new Phi(5);
		int dim = 8;
		int nbIterations = 100000;
		
		
		//Test Recuit
		
		
		PrintWriter sortie = new PrintWriter("test.txt");
		
		try {
			
			for(int i = 0; i < 10; i++){
				Grille g = new Grille(f,n,dim);
				ImprovedMutationLH m = new ImprovedMutationLH(g);
				List<Double> l = ParametreurT.parametreurRecuit(g, m, 10000);
				double dep = l.get(150);
				double stop = l.get(5);
				Temperature temp = new TemperatureExp(dep,stop);
				Writer.ecriture(i,Recuit.solution(g,m,nbIterations,temp),sortie);
			}
			sortie.close();
			System.out.println("taille " + n + " dim " + dim);
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
