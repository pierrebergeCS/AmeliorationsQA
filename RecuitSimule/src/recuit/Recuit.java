
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
	 * Methode qui calcule la probabilité d'acceptation d'un état muté. 
	 * Elle est utilisée dans la methode solution qui effectue le recuit.
	 * @param deltaE
	 * Variation de H
	 * @param deltaEpot
	 * Variation de H potentiel
	 * @param temperature
	 * Température du recuit
	 * @return
	 * Probabilité (entre 0 et 1) d'effectuer la mutation
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
	 * C'est la méthode qui effectue le recuit quantique. 
	 * @param p
	 * Problème construit par l'utilisateur
	 * @param m
	 * Une mutation aléatoire correspondant au problème traité.
	 * Les générations de mutation aléatoires au fil du recuit s'effectueront avec la méthode maj que l'utilisateur aura implémenté dans sa classe (voir IMutation)
	 * @param nombreIterations
	 * Nombre d'iterations pour chaque réplique. Le nombre de répliques, on le rappelle, est défini dans Problème.
	 * @param seed
	 * Rentrer 1 en argument
	 * @param M
	 * Nombre de fois consécutives que l'on traite une réplique. Rentrer 1 pour une utilisation normale
	 * @param sortie
	 * Fichier .txt dans lequel on stocke le résultat final du recuit.
	 * @return
	 * Solution du recuit : la meilleure énergie rencontrée par la particule
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
	
					//Mise à jour de la mutation. 
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
	
					//Mise à jour de la mutation. 
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
