package vc;

import java.io.IOException;

import recuit.Recuit;

public class MainVC {
	
public static void main(String[] args){
		
		int nombreEtat = 10;
		Graphe g = null;
		try {
			g = Traducteur.traduire("C:/Users/Pierre/Desktop/benchmark/vc/dsjc250.5.col");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int n = g.getConnexions().length; ;
		System.out.println(n);
		int nombreIterations = n*n;//on est des fous pas tarés non plus
		int nbColors = 28;
		
		double temperature = 0.1/nombreEtat ; 

		//       Test Spins
		
		/*Coloriage c1 = new Coloriage(g,nbColors);
		System.out.println(c1.toString());
		c1.afficheNoeuds();
		//Coloriage c2 = new Coloriage(g,nbColors);
		//System.out.println(c1.distanceIsing(c2));
		MutationVC mut = new MutationVC(c1);
		//System.out.println("diff " + mut.calculerdeltaSpins(c1,c2));
		System.out.println("node " + mut.getNodeIndex());
		System.out.println("newcolor " + mut.getColorIndex());
		mut.faire(c1);
		c1.afficheNoeuds();
		//System.out.println(c1.distanceIsing(c2));*/
		
		
		//       Test Recuit
		
		
		int cpt = 0;
		
		try {
			for (int i = 0; i < 1; i++){
				ParticuleVC p = ParticuleVC.initialise(nombreEtat,0.5,g,nbColors);
				MutationVC m = new MutationVC((Coloriage)p.getEtat().get(0));
				RedondancesParticuleVC red = new RedondancesParticuleVC(p);
				Recuit.solution(p,m,red,nombreIterations,1,1,temperature,10.0);
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
