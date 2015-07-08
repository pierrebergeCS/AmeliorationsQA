package LHD;

import modele.Etat;
import modele.Probleme;
import modele.RedondancesParticuleGeneral;
import mutation.MutationElementaire;

public class MutationLHElementaire extends MutationElementaire {

	public MutationLHElementaire(Croix c, int indice){
		super(c,indice);
	}
	
	@Override
	public boolean estAutorisee(Probleme p,Etat e, RedondancesParticuleGeneral red,int dureeBlock){
		if (p.getFreq() > 1.0){
			return true;
		}
		int cpt = 0;
		Croix c = (Croix) e.getListe().get(this.getIndice());
		if(c.lives()){
			if(c.iterate()){
				return false;
			}
			else{ return true;}
		}
		
		
		for (int k = 0; k < p.nombreEtat(); k++){
			if (((Grille)p.getEtat().get(k)).estCochee(c)) cpt++;
		}
		boolean estAutorisee=!(cpt > p.getFreq()*p.nombreEtat());
		if(!estAutorisee){
			c.resetBlock(dureeBlock);
		}
		return estAutorisee;
		
	}
	
	@Override
	public void majRedondance(Probleme p, RedondancesParticuleGeneral red, Etat e){
		//ras puisque redondancesparticulelh ne sert à rien
	}
	
}
