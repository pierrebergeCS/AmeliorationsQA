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
	
	/**
	 * Methode qui renvoie un objet de type RedondancesParticules, relatif au probleme trait�.
	 * 
	 * Attention : nous conseillons fortement � l'utilisateur d'overrider cette m�thode, surtout si le cardinal de l'univers n'est pas tr�s grand (en n� pour TSP par exemple)
	 * 
	 * Cette m�thode est universelle mais assez co�teuse, m�me si elle ne sera effectu� qu'au d�but du recuit.
	 * On est oblig� de regarder tous les �l�ments de la particule et (surtout) de v�rifier s'ils se r�p�tent.
	 * Lorsque la fr�quence d'apparition de l'�l�ment est sup�rieure � f0, on le met de c�t�.
	 * De plus, elle retourne un objet de type Redondances
	 * @return
	 * Les �l�ments de la particule qui reviennent � une fr�quence sup�rieure � freq (f0)
	 */
	public RedondancesParticuleGeneral elementsFrequents(){
		int p = this.nombreEtat();
		
		//Contient tous les �l�ments de la particule
		//L'argument nbApparitions, fournit, pour ces �l�ments, le nombre d'�l�ments dans la particule
		ArrayList<Element> elementsParticule = new ArrayList<Element>();
		
		
		//Retient les �l�ments fr�quents de la particule si f0*P > 1. Sinon, elle ne retient rien.
		//Si cette condition n'est pas v�rifi�e (f0*P < 1 ou f0 < 1/P), il suffit de renvoyer elementsParticule.
		//C'est le cas extr�me o� tous les �l�ments sont jug�s fr�quents. Jamais utilis� en pratique bien s�r.
		ArrayList<Element> elementsFrequents =  new ArrayList<Element>();
		
		//On inspecte maintenant chaque �l�ment.
		for (int replique = 0; replique < p; replique++){
			Etat e = this.getEtat().get(replique);
			for (int elt = 0; elt < e.getListe().size(); elt++){
				
				//On va v�rifier si l'�l�ment trait� a d�j� �t� rep�r� dans les �l�ments  fr�quents.
				int cpt = 0;
				boolean estFrequent = false;
				while ((cpt < elementsFrequents.size()) && !estFrequent){
					estFrequent = e.getListe().get(elt).equals(elementsFrequents.get(cpt));
					cpt++;
				}
				//Si l'�l�ment est d�j� pr�sent dans la liste des �l�ments fr�quents, on ajoute une apparition.
				if (cpt < elementsFrequents.size() || estFrequent) elementsFrequents.get(cpt-1).addApparition();
				
				
				//On va v�rifier si l'�l�ment trait� a d�j� �t� rep�r� dans la particule.
				cpt = 0;
				boolean existeDeja = false;
				while ((cpt < elementsParticule.size()) && !existeDeja){
					existeDeja = e.getListe().get(elt).equals(elementsParticule.get(cpt));
					cpt++;
				}
				
				if ((cpt == elementsParticule.size()) && !existeDeja){
					//Cas o� l'�l�ment n'a jamais �t� vu jusqu'alors. On l'ajoute � elementsParticule.
					elementsParticule.add(e.getListe().get(elt));
					elementsParticule.get(elementsParticule.size()-1).setNbApparitions(1);
				} else {
					//Cas o� l'�l�ment a d�j� �t� observ�. On l'ajoute � elementsFrequents dans le cas o� on d�passe f0
					int previous = elementsParticule.get(cpt-1).getNbApparitions();
					elementsParticule.get(cpt-1).addApparition();
					if ((previous <= this.freq*p) && ((previous+1) > this.freq*p)){
						elementsFrequents.add(e.getListe().get(elt));
						elementsFrequents.get(elementsFrequents.size()-1).addApparition();
					}
				}
			}
		}
		if (this.freq*p <= 1.0) return new RedondancesParticuleGeneral(elementsParticule,elementsParticule);
		return new RedondancesParticuleGeneral(elementsParticule,elementsFrequents);
	}
	
	
	
	
	
	
	
}

