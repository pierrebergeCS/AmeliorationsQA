package tsp;
import java.util.ArrayList;








import parametres.ParametreGamma;
import parametres.Ponderation;
import parametres.Temperature;
import modele.Etat;
import modele.Probleme;
import mutation.IMutation;



public class ParticuleTSP extends Probleme {
	Graphe g;
	
	public ParticuleTSP(Graphe g, ArrayList<Etat> etat){
		/**
		 * le constructeur initial les autres valeurs sont "setables"
		 **/
	this.g=g;
	this.etat=etat;
	}
	
	public ParticuleTSP(Graphe g, ArrayList<Etat> etat,Temperature T,int seed,ParametreGamma gamma, double freq){
		super(etat,T,seed,gamma, freq);
		this.g=g;
	}
	
	public Graphe getGraphe(){
		return this.g;
	}
	
	
	public double calculerEnergiePotentielle(){
		double compteur=0;
		for (Etat i:this.etat){
			compteur +=i.getEnergie();	
		}
		return compteur/this.etat.size();
	}
	
	
	//Calcul Ecin sans J
	public double calculerEnergieCinetique(){
		ArrayList<Etat> r = this.getEtat();
		int n = r.size();
		double compteurspinique=0;
		for(int i =0; i<n;i++){
			//// IL reste a coder le calcul particulier
			Etat e = r.get(i);
			Routage r1=(Routage) e;
			Routage r2=(Routage) e.getNext();
			
			//System.out.println(r1);
			int[][] Mi = r1.getIsing();
			int[][] Mj = r2.getIsing();
			for(int k =0;k<Mi.length-1;k++){
				for(int l =k+1;l<Mi.length;l++){
					compteurspinique+=Mi[k][l]*Mj[k][l];
				}
			}	
		
		}
		return compteurspinique;
	}
	
	
	
	
	public Temperature getTemperature() {
		// TODO Auto-generated method stub
		return this.getT();
	}
	
	
	public ParticuleTSP clone(){
		int n = this.etat.size();
		ArrayList<Etat> r = new ArrayList<Etat>(n);
		for(int i=0; i<n; i++){
			r.add(((Routage) this.etat.get(i)).clone());
		}
		 ParticuleTSP p = new ParticuleTSP(this.g,r,this.getT(), this.getSeed(),this.getGamma(), this.getFreq()) ;
		return p;
		
	}
	public void setRoutage(ArrayList<Etat> e) {
		// TODO Auto-generated method stub
		this.setEtat(e);
		
	}
	public Etat creeEtatAleatoire(){
		return new Routage(this.g);
	}
}
