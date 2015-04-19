package modele;
import java.util.ArrayList;

import parametres.ParametreGamma;
import parametres.Temperature;
/**
 * 
 * 
 * 
 * Cette classe est clef dans la conception du recuit
 * C'est une particule a laquelle est liée la totalité du problème, energie cinétique et Etats spécifiés sur le problème.
 * 
 * Elle dépend aussi d'une seed qui va par la suite permettre de générer des nombres aléatoires 
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

	// On rajoute de plus une seed de génération de nombres aléatoires
	private int seed;
	// Fonctions Ã©lÃ©mentaires de calcul de l'energie de la particule
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
	 * L'utilisateur devra implémenter cette méthode afin de renvoyer l'énergie cinétique de la particule
	 * @return
	 *L'energie cinétique associée au problème
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
	 * L'utilisateur devra implémenter cett methode pour creer un Etat adapte a son probleme
	 *@return un etat créé aléatoirement
	 */
	public abstract Etat creeEtatAleatoire();
	
	//--------------------------------RESTRICTIONS DES ELEMENTS------------------------//
	
	/**
	 * Methode qui renvoie un objet de type RedondancesParticules, relatif au probleme traité.
	 * 
	 * Attention : nous conseillons fortement à l'utilisateur d'overrider cette méthode, surtout si le cardinal de l'univers n'est pas très grand (en n² pour TSP par exemple)
	 * 
	 * Cette méthode est universelle mais assez coûteuse, même si elle ne sera effectué qu'au début du recuit.
	 * On est obligé de regarder tous les éléments de la particule et (surtout) de vérifier s'ils se répètent.
	 * Lorsque la fréquence d'apparition de l'élément est supérieure à f0, on le met de côté.
	 * De plus, elle retourne un objet de type Redondances
	 * @return
	 * Les éléments de la particule qui reviennent à une fréquence supérieure à freq (f0)
	 */
	public RedondancesParticuleGeneral elementsFrequents(){
		int p = this.nombreEtat();
		
		//Contient tous les éléments de la particule
		//L'argument nbApparitions, fournit, pour ces éléments, le nombre d'éléments dans la particule
		ArrayList<Element> elementsParticule = new ArrayList<Element>();
		
		
		//Retient les éléments fréquents de la particule si f0*P > 1. Sinon, elle ne retient rien.
		//Si cette condition n'est pas vérifiée (f0*P < 1 ou f0 < 1/P), il suffit de renvoyer elementsParticule.
		//C'est le cas extrême où tous les éléments sont jugés fréquents. Jamais utilisé en pratique bien sûr.
		ArrayList<Element> elementsFrequents =  new ArrayList<Element>();
		
		//On inspecte maintenant chaque élément.
		for (int replique = 0; replique < p; replique++){
			Etat e = this.getEtat().get(replique);
			for (int elt = 0; elt < e.getListe().size(); elt++){
				
				//On va vérifier si l'élément traité a déjà été repéré dans les éléments  fréquents.
				int cpt = 0;
				boolean estFrequent = false;
				while ((cpt < elementsFrequents.size()) && !estFrequent){
					estFrequent = e.getListe().get(elt).equals(elementsFrequents.get(cpt));
					cpt++;
				}
				//Si l'élément est déjà présent dans la liste des éléments fréquents, on ajoute une apparition.
				if (cpt < elementsFrequents.size() || estFrequent) elementsFrequents.get(cpt-1).addApparition();
				
				
				//On va vérifier si l'élément traité a déjà été repéré dans la particule.
				cpt = 0;
				boolean existeDeja = false;
				while ((cpt < elementsParticule.size()) && !existeDeja){
					existeDeja = e.getListe().get(elt).equals(elementsParticule.get(cpt));
					cpt++;
				}
				
				if ((cpt == elementsParticule.size()) && !existeDeja){
					//Cas où l'élément n'a jamais été vu jusqu'alors. On l'ajoute à elementsParticule.
					elementsParticule.add(e.getListe().get(elt));
					elementsParticule.get(elementsParticule.size()-1).setNbApparitions(1);
				} else {
					//Cas où l'élément a déjà été observé. On l'ajoute à elementsFrequents dans le cas où on dépasse f0
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

