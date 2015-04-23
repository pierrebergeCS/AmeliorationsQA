package tsp;

import java.util.ArrayList;

import modele.Etat;
import modele.Probleme;
import modele.RedondancesParticuleGeneral;
import mutation.MutationElementaire;

public class TwoOptMoveElementaire extends MutationElementaire {
	
	public TwoOptMoveElementaire(Arete a, int indice){
		super(a,indice);
	}
	
	@Override
	public boolean estAutorisee(Probleme p,Etat e, RedondancesParticuleGeneral red){
		RedondancesParticuleTSP r = (RedondancesParticuleTSP) red;
		Routage route = (Routage) e;
		int indice = this.getIndice();
		ArrayList<Arete> liste = r.getElementsFrequents((int) p.getFreq()*p.nombreEtat());
		int cpt = 0;
		boolean estAutorisee = true;
		while ((cpt < liste.size()) && estAutorisee){
			estAutorisee = !route.getListe().get(indice).equals(liste.get(cpt));
			cpt++;
		}
		return estAutorisee;
	}
	
	@Override
	public void majRedondance(Probleme p, RedondancesParticuleGeneral red, Etat e){
		Routage route = (Routage) e;
		RedondancesParticuleTSP r = (RedondancesParticuleTSP) red;
		Arete old = (Arete) route.getListe().get(this.getIndice());
		Arete next = (Arete) this.getElement();
		int old1 = old.getNoeud1();
		int old2 = old.getNoeud2();
		int next1 = next.getNoeud1();
		int next2 = next.getNoeud2();
		if (old1 < old2) r.getTab()[old1][old2]--;
		if (old2 < old1) r.getTab()[old2][old1]--;
		if (next1 < next2) r.getTab()[next1][next2]++;
		if (next2 < next1) r.getTab()[next2][next1]++;
	}
}
