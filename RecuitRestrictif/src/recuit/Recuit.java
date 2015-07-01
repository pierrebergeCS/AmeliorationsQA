
package recuit;
import modele.*;
import parametres.*;
import sat3.*;
import mutation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import LHD.Grille;


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
	public static double probaAcceptation(double deltaE, double deltaEpot, Temperature temperature) throws IOException 
	{	
		if(deltaE <= 0 || deltaEpot < 0)
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
	public static double solution(Probleme p,IMutation m,RedondancesParticuleGeneral red,int nombreIterations, int seed, int M, double temp, double GammaDep) throws IOException, InterruptedException
	{	
		int nombreEtat=p.nombreEtat();
		
		//List<Double> listeDelta = ParametreurT.parametreurRecuit(p,m, nombreIterations);
		Temperature temperatureDepart = new Temperature(temp/nombreEtat);
	
		//ParametreGamma gamma = ParametreurGamma.parametrageGamma(nombreIterations,nombreEtat,temperatureDepart,listeDelta.get(200));// Rappel : 1000 echantillons
		ParametreGamma gamma = new ParametreGamma(GammaDep,GammaDep/(nombreIterations+1),0.01);
		p.setT(temperatureDepart.getValue());
		p.setGamma(gamma);
		
		p.setT(temperatureDepart);
		ArrayList<Etat> e = p.getEtat();
		Ponderation J = new Ponderation(p.getGamma());
		double Epot = p.calculerEnergiePotentielle();
		
		double compteurSpinique = p.calculerEnergieCinetique();
		double E = Epot-J.calcul(p.getT(), nombreEtat)*compteurSpinique;
		double deltapot  = 0;
		double energie = (e.get(0)).getEnergie();
		double energieBest = energie;
		double valueJ = 0;
		
		Etat etatBest = e.get(0).clone();
		
		
		int MutationsRefusees = 0;
		
		for(int i =0; i<nombreIterations;i++){
			
			 valueJ = J.calcul(p.getT(), nombreEtat);
			 E = Epot-valueJ*compteurSpinique;
			 
			 
			for(int j=0;j<nombreEtat;j++){// on effectue M  fois la mutation sur chaque particule avant de descendre gamma
				
				Etat r2 = e.get(j);
				
				for(int k=0; k<M; k++){
					//Mise � jour de la mutation. Tant qu'elle n'est pas autoris�e, on recommence.
					int nbTentatives = 0;
					m.maj(p,r2);
					while (!m.estAutorisee(p,r2, red) && nbTentatives < 10){
						m.maj(p,r2);
						MutationsRefusees++;
						nbTentatives++;
					}
					
					deltapot =  m.calculerdeltaEp(p,r2);
					
					double deltaEp = deltapot/nombreEtat;
					//System.out.println("deP " + deltaEp);
					double deltaEc = -valueJ*m.calculerdeltaSpins(p,r2);
					double delta = deltaEp + deltaEc;
					double pr=probaAcceptation(delta,deltapot,p.getT());
					
					if(pr>Math.random()){
						m.majRedondance(p,red,r2);
						energie = r2.getEnergie();
						
						m.faire(p,r2);
						
			
						compteurSpinique += m.calculerdeltaSpins(p,r2);
						
						e.set(j, r2);
						p.setEtat(e);
						
						Epot += deltapot/nombreEtat;
						E += delta;// L'energie courante est modifi�e
						energie += deltapot;
						
						}
					
						if (energie < energieBest){
						etatBest = p.getEtat().get(j).clone();
						energieBest = energie;
						if(energie==0){
							//System.out.println("result :" + energieBest);
							//System.out.println("refus mutations :"+MutationsRefusees);
							return 0;
						}
						
						}
					
				}
				
				
			}
			//UNE FOIS EFFECTUEE SUR tout les etat de la particule on descend gamma
			p.majgamma();
			J.setGamma(p.getGamma());
			Collections.shuffle(p.getEtat());
			
		}
		//Writer.ecriture(compteurpourlasortie,energieBest, sortie);
		System.out.println("result :" + energieBest);
		System.out.println("D :" + ((Grille)etatBest).getdmin());
		System.out.println("refus mutations :"+MutationsRefusees);
		
		return energieBest;

	}
	
	



}
