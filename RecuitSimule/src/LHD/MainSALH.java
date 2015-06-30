package LHD;

import java.io.FileNotFoundException;
import java.io.IOException;

import parametres.Temperature;
import recuit.Recuit;

public class MainSALH {

	public static void main(String[] args) throws FileNotFoundException {
		
		int n = 25;
		int dim = 4;
		Phi f = new Phi(5);
		int nbIterations = 100000*dim;
		
		double tempDepart = 0.000000000001;
		Temperature temp = new Temperature(tempDepart,0.0);
		
		try {
			
			for(int i = 0; i < 1; i++){
				Grille g = new Grille(f,n,dim);
				MutationLH m = new MutationLH(g);
				Recuit.solution(g,m,nbIterations,temp);
			}
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
