package vc;

import java.io.IOException;

import parametres.Temperature;
import recuit.Recuit;

public class MainSAVC {
	
public static void main(String[] args){

		Graphe g = null;
		try {
			g = Traducteur.traduire("C:/Users/Pierre/Desktop/benchmark/vc/dsjc250.5.col");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int n = g.getConnexions().length; ;
		System.out.println(n);
		int nombreIterations = 100*n*n;//on est des fous pas tarés non plus
		int nbColors = 28;
		Temperature temp = new Temperature(0.1,0.0);

		//       Test Recuit
		
		
		int cpt = 0;
		
		try {
			for (int i = 0; i < 10; i++){
				Coloriage p = new Coloriage(g,nbColors);
				MutationVC m = new MutationVC(new Coloriage(g,nbColors));
				Recuit.solution(p,m,nombreIterations,temp);
			}
			System.out.println(cpt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
