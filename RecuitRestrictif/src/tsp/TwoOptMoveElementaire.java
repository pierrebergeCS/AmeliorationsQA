package tsp;

import java.util.ArrayList;

import modele.Element;
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
		Arete a = (Arete) e.getListe().get(this.getIndice());
		RedondancesParticuleTSP r = (RedondancesParticuleTSP) red;
		int i = Math.min(a.getNoeud1(),a.getNoeud2());
		int j = Math.max(a.getNoeud1(),a.getNoeud2());
		return (r.getTab()[i][j] <= p.getFreq()*p.nombreEtat());
	}
	
	@Override
	public void majRedondance(Probleme p, RedondancesParticuleGeneral red, Etat e){
		Routage route = (Routage) e;
		RedondancesParticuleTSP r = (RedondancesParticuleTSP) red;
		Arete old = (Arete) route.getListe().get(this.getIndice());
		Arete next = (Arete) this.getElement();
		int old1 = Math.min(old.getNoeud1(),old.getNoeud2());
		int old2 = Math.max(old.getNoeud1(),old.getNoeud2());
		int next1 = Math.min(next.getNoeud1(),next.getNoeud2());
		int next2 = Math.max(next.getNoeud1(),next.getNoeud2());
		r.getTab()[old1][old2]--;
		r.getTab()[next1][next2]++;
	}
}
