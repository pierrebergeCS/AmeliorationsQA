package LHD;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import parametres.ParametreurT;
import parametres.Temperature;
import recuit.Recuit;
import tsp.parser.Writer;

public class MainSALH {

	public static void main(String[] args) throws FileNotFoundException {
		
		int n = 10;
		Phi f = new Phi(5);
		int dim = 9;
		int nbIterations = 100000*dim;
		
		PrintWriter sortie = new PrintWriter("LHD_9_10.txt");
		
		try {
			
			for(int i = 0; i < 100; i++){
				Grille g = new Grille(f,n,dim);
				MutationLH m = new MutationLH(g);
				Temperature temp = new Temperature(ParametreurT.parametreurRecuit(g, m).get(10),0.0);
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
