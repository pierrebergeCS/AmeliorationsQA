package modele;
import java.util.ArrayList;

import parametres.ParametreGamma;
import parametres.Temperature;
/**
 * 
 * 
 * 
 * Cette classe est clef dans la conception du recuit
 * C'est une particule a laquelle est li�e la totalit� du probl�me, energie cin�tique et Etats sp�cifi�s sur le probl�me.
 * 
 * Elle d�pend aussi d'une seed qui va par la suite permettre de g�n�rer des nombres al�atoires 
 * 
 * 
 * 
 * 
 * 
 * 
 * @author BaptCav
 * 
 * 
 * 
 * 
 *
 */
public abstract class Probleme extends Particule{

	// On rajoute de plus une seed de g�n�ration de nombres al�atoires
	private int seed;
	// Fonctions élémentaires de calcul de l'energie de la particule
	private ParametreGamma gamma;
	double freq;
	

	
	public Probleme(){
		
	}
	
	public Probleme(ArrayList<Etat> etat,Temperature T,int seed,ParametreGamma gamma,double freq){
		super(etat, T);
		this.seed=seed;
		this.gamma=gamma;
		this.freq = freq;
	}


	public abstract Probleme clone();
	/**
	 * 
	 * @return
	 * L'energie totale de la particule
	 */
	public double calculerEnergie(){
		return this.calculerEnergieCinetique()+this.calculerEnergiePotentielle();
	}
	
	/**
	 * L'utilisateur devra impl�menter cette m�thode afin de renvoyer l'�nergie cin�tique de la particule
	 * @return
	 *L'energie cin�tique associ�e au probl�me
	 */
	public abstract double calculerEnergieCinetique();
	
	
	/**
	 * 
	 * @return
	 *L'energie potentielle totale de la particule
	 */
	public double calculerEnergiePotentielle(){
		double compteur=0;
		for (Etat i:this.etat){
			compteur += i.getEnergie();	
		}
		return compteur/this.etat.size();
	}


	public void modifElem(){
	}
	public void annulerModif(){
	}
	public void modifEtat(Etat e){
	}
	public void annulerModifEtat(Etat e){
	}

	public int getSeed(){
		return this.seed;
	}
	public void setSeed(int seed){
		this.seed=seed;
	}
/**
 * 
 * @return
 * la meilleure instance Etatique
 */
	public Etat getBest() {
		Etat best = this.etat.get(0);
		double min = best.getEnergie();
		for(Etat i : this.etat){
			double j = i.getEnergie();
			if(j<min){
				best= i;
				min =j;
			}
		}
		return best;

	}


	public ParametreGamma getGamma(){
		return this.gamma;
	}
	public void majgamma(){
		this.gamma.refroidissementLin();
	}
	public void setGamma(ParametreGamma gamma){
		this.gamma=gamma;
	}
	public double getFreq(){
		return this.freq;
	}
	public void setFreq(double f){
		this.freq = f;
	}
	

	/**
	 * L'utilisateur devra impl�menter cett methode pour creer un Etat adapte a son probleme
	 *@return un etat cr�� al�atoirement
	 */
	public abstract Etat creeEtatAleatoire();
	
	//--------------------------------RESTRICTIONS DES ELEMENTS------------------------//
	
	public abstract ArrayList<Element> elementsFrequents();
	
	
	
}

