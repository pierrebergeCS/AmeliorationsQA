package MKP;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import parametres.ParametreurT;
import parametres.Temperature;
import parametres.TemperatureLin;
import recuit.Recuit;
import tsp.parser.Writer;

//----------------------RECUIT-SIMULE--------------------------//

public class mainmkp {

	public static void main(String[] args) throws IOException {
		Instance ins = TransposeLator.traduit("C:/Users/stagiaire/Desktop/benchmark/kp/chubeas/OR30x500/OR30x500-0.75_1.dat");
		int n = ins.getNombreObjets();
		int nombreIterations = 100*n*n;
		
		
		//Check probas
		int nbObjets = 20;
		int[] refPoids = {20,10,10,20,20};
		int refCapa = 30;
		int nbSacs = 5;
		Objet[] o = new Objet[nbObjets];
		for (int i = 0; i < nbObjets; i++){
			int[] w = new int[nbSacs];
			for (int j = 0; j < nbSacs; j++){
				w[j] = (int) (Math.random()*refPoids[j]);
			}
			o[i] = new Objet(i,1,w);
		}
		int[] capa = {refCapa,refCapa,refCapa*2,refCapa*2,refCapa*5};
		Instance insTest = new Instance(nbObjets,nbSacs,capa,o);
		int cpt = 0;
		int th = 0;
		int moy = 0;
		for (int k = 0; k < nbObjets; k++){
			moy += insTest.getObj()[k].getWeight()[1];
		}
		int k = Math.min((int) ((insTest.getCapacite()[1]*nbObjets)/((double)moy)),nbObjets);
		double th1 = 0;
		double th2 = 0;
		for (int i = 0; i <= k-1; i++){
			th1 += Parmi.calculer(nbObjets-1,i);
			th2 += Parmi.calculer(nbObjets,i);
		}
		th2 += Parmi.calculer(nbObjets,k);
		System.out.println("nb objets :" + nbObjets);
		System.out.println("contrainte 1 :" + insTest.getCapacite()[0]);
		System.out.println("moy : " + moy/((double)nbObjets));
		System.out.println("k :" + k);
		System.out.println("approx :" + th1/th2);
		for (int i = 0; i < 1000; i++){
			int objet = (int) (Math.random()*insTest.getNombreObjets());
			ArrayList<ElementMKP> liste = Remplissage.random(insTest);
			if (liste.get(objet).getAppartenance()) cpt++;
		}
		System.out.println("alpha :" + cpt/1000.0);
		
		
		 //       Test Recuit
		
		PrintWriter sortie = new PrintWriter("test.txt");
		
		try {
	
			for (int i = 0; i < 0; i++){
				Remplissage r = new Remplissage(ins);
				MutationMKP m = new MutationMKP(r);
				double tempDepart = 100.0;
				Temperature temp = new TemperatureLin(tempDepart,0.0);
				Recuit.solution(r,m,nombreIterations,temp,sortie);
			}
			sortie.close();
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
