package OptimalNodeCoverage;



import java.util.ArrayList;
import modele.Etat;
import modele.Probleme;
import mutation.IMutation;
import mutation.MutationElementaire;

public class MutationNodeCoverage extends IMutation {
	int indice; // indice de'ancre malheureuse 
	Set s;	// set cible
	int typeOfMut; //+1 if steal, 0 si on échange
	int indice2;
	private Set sPrime;

	public MutationNodeCoverage(Coverage c){
		ArrayList<MutationElementaire> liste = new ArrayList<MutationElementaire>();
		int n = c.getListe().size();//nombre d'anchors
		boolean b = false;

		//On essaie d'abord d'ajouter
		int k = -1;
		ArrayList<Set> laListe=c.getSets();
		this.s=laListe.get((int)(Math.random()*laListe.size()));
		while ( k < 0 || c.test(((ElementNodeCoverage) c.getListe().get(k)).getAnchor(),s)){
			k = (int) (Math.random()*n);
		}
		//Verifie que le noeud puisse etre volé
		ElementNodeCoverage e=(ElementNodeCoverage) c.getListe().get(k);
		if(e.getSet().size()!=4){//Can steal
			this.indice = k;
			this.typeOfMut = 1;
			liste.add(new MutationElementaireNodeCoverage(new ElementNodeCoverage(e.getAnchor(), s),k));
		}else{//Unfortunately this coverage is poor try exchange
			ArrayList<Anchor> ancresS = s.getAnchor();
			ArrayList<Anchor> valid=new ArrayList<Anchor>(0);
			sPrime=e.getSet();
			for(int i =0;i<ancresS.size();i++){
				Anchor a=ancresS.get(i);
				if(c.test(a, sPrime)){
					valid.add(a);
				}
			}
			if(!valid.isEmpty()){//if we can do a swipe
				Anchor cible=valid.get((int)(Math.random()*valid.size()));
				this.indice = k;
				this.indice2 = cible.identifier;
				this.typeOfMut = 0;
				liste.add(new MutationElementaireNodeCoverage(new ElementNodeCoverage(e.getAnchor(), s),k));
				liste.add(new MutationElementaireNodeCoverage(new ElementNodeCoverage(cible, sPrime),cible.identifier));
			}
		}
		
		this.listeMutations = liste;
		
		


	}

	public int getType(){
		return this.typeOfMut;
	}
	
	public int getIndice(){
		return this.indice;
	}
	
	public int getIndice2(){
		return this.indice2;
	}

	@Override
	public void faire(Probleme p,Etat e) {
		Coverage r = (Coverage) e;
		
		if (this.typeOfMut == 1){
			ElementNodeCoverage elt =(ElementNodeCoverage) r.getListe().get(this.indice);
			elt.changeAppartenance(s);
		}
		
		if (this.typeOfMut == 0){
			ElementNodeCoverage elt = (ElementNodeCoverage) r.getListe().get(this.indice);
			ElementNodeCoverage elt2 =(ElementNodeCoverage)  r.getListe().get(this.indice2);
			elt.changeAppartenance(s);
			elt2.changeAppartenance(sPrime);
		}
		
		
	}
	
	

	public double calculerdeltaEp(Probleme p,Etat e) {
		//va dependre de la metrique
		
		
		Coverage r = (Coverage) e;
		if (this.typeOfMut == 1){
			ElementNodeCoverage elt =(ElementNodeCoverage) r.getListe().get(this.indice);
			return -elt.getObjet().getUtility();
		} 
		if (this.typeOfMut == -1){
			ElementNodeCoverage elt2 =(ElementNodeCoverage) r.getListe().get(this.indice2);
			return elt2.getObjet().getUtility();
		}
		if (this.typeOfMut == 0){
			ElementNodeCoverage elt =(ElementNodeCoverage) r.getListe().get(this.indice);
			ElementNodeCoverage elt2 =(ElementNodeCoverage) r.getListe().get(this.indice2);
			return elt2.getObjet().getUtility()-elt.getObjet().getUtility();
		} 
		return 0;
	}


	@Override
	public double calculerdeltaSpins(Probleme p, Etat e) {
		//systeme de Hashcode via les noeuds dans un set
		
		
				A CODER
		return 0;
	}

	@Override
	public void maj(Probleme p, Etat e) {
		Coverage c=(Coverage) e;
		this.typeOfMut = -1;
		while(typeOfMut==-1){
			ArrayList<MutationElementaire> liste = new ArrayList<MutationElementaire>();
			int n = c.getListe().size();//nombre d'anchors
			boolean b = false;

			//On essaie d'abord d'ajouter
			int k = -1;
			ArrayList<Set> laListe=c.getSets();
			this.s=laListe.get((int)(Math.random()*laListe.size()));
			while ( k < 0 || c.test(((ElementNodeCoverage) c.getListe().get(k)).getAnchor(),s)){
				k = (int) (Math.random()*n);
			}
			//Verifie que le noeud puisse etre volé
			ElementNodeCoverage elt=(ElementNodeCoverage) c.getListe().get(k);
			if(elt.getSet().size()!=4){//Can steal
				this.indice = k;
				this.typeOfMut = 1;
				liste.add(new MutationElementaireNodeCoverage(new ElementNodeCoverage(elt.getAnchor(), s),k));
			}else{//Unfortunately this coverage is poor try exchange
				ArrayList<Anchor> ancresS = s.getAnchor();
				ArrayList<Anchor> valid=new ArrayList<Anchor>(0);
				sPrime=elt.getSet();
				for(int i =0;i<ancresS.size();i++){
					Anchor a=ancresS.get(i);
					if(c.test(a, sPrime)){
						valid.add(a);
					}
				}
				if(!valid.isEmpty()){//if we can do a swipe
					Anchor cible=valid.get((int)(Math.random()*valid.size()));
					this.indice = k;
					this.indice2 = cible.identifier;
					this.typeOfMut = 0;
					liste.add(new MutationElementaireNodeCoverage(new ElementNodeCoverage(elt.getAnchor(), s),k));
					liste.add(new MutationElementaireNodeCoverage(new ElementNodeCoverage(cible, sPrime),cible.identifier));
				}
			}
			
			this.listeMutations = liste;
		}
	}

}
