package vc;

public class MutationVCElementaire {
	Couleur elt;
	int indice;
	int nodeIndex;//Le noeud qu'on enleve ou qu'on ajoute � une couleur.
	//On pourrait le d�duire de element et indice, mais c'est p�nible.
	
	public MutationVCElementaire(Couleur elt, int indice) {
		this.elt = elt;
		this.indice = indice;
	}

	public void setNodeIndex(int k){
		this.nodeIndex = k;
	}
	
	public void faire(Coloriage e){
		e.getListe().set(this.indice,this.elt);
	}

}
