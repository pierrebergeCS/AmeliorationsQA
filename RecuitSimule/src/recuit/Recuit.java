
package recuit;
import modele.*;
import parametres.*;
import sat3.*;
import tsp.Routage;
import tsp.TwoOptMove;
import tsp.parser.Writer;
import mutation.*;

import java.io.IOException;
import java.io.PrintWriter;

import LHD_1fixed.Grille;



// Cette classe definit le probleme du recuit. Il se charge d'effectuer les mutations elementaires, de calculer l'energie et de diminuer T...

/**
 * 
 * @author Pierre
 *
 */
public class Recuit 
{
	public double solutionNumerique;

	static int nbTours = 1;
	static ParametreK K = new ParametreK(1);

	/**
	 * Methode qui calcule la probabilit� d'acceptation d'un �tat mut�. 
	 * Elle est utilis�e dans la methode solution qui effectue le recuit.
	 * @param deltaE
	 * Variation de H
	 * @param deltaEpot
	 * Variation de H potentiel
	 * @param temperature
	 * Temp�rature du recuit
	 * @return
	 * Probabilit� (entre 0 et 1) d'effectuer la mutation
	 * @throws IOException
	 */
	public static double probaAcceptation(double deltaE, Temperature temperature) throws IOException 
	{	
		if(deltaE <= 0)
		{
			return 1;
		}
		return Math.exp( (-deltaE) / (K.getK()*temperature.getValue()));
	}	
	
	
	/**
	 * C'est la m�thode qui effectue le recuit quantique. 
	 * @param p
	 * Probl�me construit par l'utilisateur
	 * @param m
	 * Une mutation al�atoire correspondant au probl�me trait�.
	 * Les g�n�rations de mutation al�atoires au fil du recuit s'effectueront avec la m�thode maj que l'utilisateur aura impl�ment� dans sa classe (voir IMutation)
	 * @param nombreIterations
	 * Nombre d'iterations pour chaque r�plique. Le nombre de r�pliques, on le rappelle, est d�fini dans Probl�me.
	 * @param seed
	 * Rentrer 1 en argument
	 * @param M
	 * Nombre de fois cons�cutives que l'on traite une r�plique. Rentrer 1 pour une utilisation normale
	 * @param sortie
	 * Fichier .txt dans lequel on stocke le r�sultat final du recuit.
	 * @return
	 * Solution du recuit : la meilleure �nergie rencontr�e par la particule
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static double solution(Etat e,IMutation m,int nombreIterations,Temperature temp) throws IOException, InterruptedException
	{	
		
		double E = e.getEnergie();
		
		double deltapot  = 0;
		double energieBest = E;
		double bestResult = e.getResultat();
		Etat etatBest = e.clone();
		
		for(int i =0; i<nombreIterations;i++){
	
					//Mise � jour de la mutation. 
					m.maj(e);
					
					deltapot =  m.calculerdeltaEp(e);
					
					double pr=probaAcceptation(deltapot,temp);
					
					if(pr>Math.random()){
						m.faire(e);
						e.setDeltapot(deltapot);
						}
					
					if (E < energieBest){
						energieBest = E;
					}
					
					if (e.getResultat()<bestResult){
						etatBest = e.clone();
						bestResult = e.getResultat();
					}
					if(E==0){
							System.out.println("result :" + energieBest);
							return 0;
					}		
			temp.maj(i,nombreIterations);
		}
		//Writer.ecriture(compteurpourlasortie,energieBest, sortie);
		//System.out.println("result :" + energieBest);
		System.out.println("Dbest :" + (-etatBest.getResultat()));  //Pour LatinHypercube
		
		return (-etatBest.getResultat());

	}


	public static double solution(Etat e,IMutation m,int nombreIterations,Temperature temp, PrintWriter sortie) throws IOException {
		double E = e.getEnergie();
		
		double deltapot  = 0;
		double energieBest = E;
		double bestResult = e.getResultat();
		Etat etatBest = e.clone();
		
		for(int i =0; i<nombreIterations;i++){
	
					//Mise � jour de la mutation. 
					m.maj(e);
					E = e.getEnergie();
					deltapot =  m.calculerdeltaEp(e);
					
					double pr=probaAcceptation(deltapot,temp);
					
					if(pr>Math.random()){
						m.faire(e);
						e.setDeltapot(deltapot);
						}
					
					if (E < energieBest){
						energieBest = E;
					}
					
					if (e.getResultat()<bestResult){
						etatBest = e.clone();
						bestResult = e.getResultat();
					}
					if(E==0){
						Writer.ecriture(0,energieBest,sortie);	
						System.out.println("result :" + energieBest);
							return 0;
					}		
			temp.maj(i,nombreIterations);
			if(i%1000==0){
				Writer.ecriture(0,energieBest,sortie);
			}
		}
		//Writer.ecriture(compteurpourlasortie,energieBest, sortie);
		//System.out.println("result :" + energieBest);
		System.out.println("Dbest :" + (-etatBest.getResultat()));  //Pour LatinHypercube
		
		return (-etatBest.getResultat());

		
	}
	
	



}
