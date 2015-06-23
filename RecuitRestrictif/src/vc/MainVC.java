package vc;

import java.io.IOException;

import recuit.Recuit;

public class MainVC {
	
public static void main(String[] args){
		
		int nombreEtat = 20;
		Graphe g = null;
		try {
			g = Traducteur.traduire("C:/Users/Pierre/Desktop/benchmark/vc/dsjc250.5.col");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int n = g.getConnexions().length; ;
		System.out.println(n);
		int nombreIterations = 10*n*n;//on est des fous pas tarés non plus
		int nbColors = 28;

		//       Test Recuit
		
		
		int cpt = 0;
		
		try {
			for (int i = 0; i < 1; i++){
				ParticuleVC p = ParticuleVC.initialise(nombreEtat,0.8,g,nbColors);
				MutationVC m = new MutationVC(new Coloriage(g,nbColors));
				RedondancesParticuleVC red = new RedondancesParticuleVC(p);
				Recuit.solution(p,m,red,nombreIterations,1,1);
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
