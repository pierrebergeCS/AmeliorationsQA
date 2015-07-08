package tsp;
import java.util.ArrayList;










import parametres.ParametreGamma;
import parametres.Temperature;
import modele.Etat;
import modele.RedondancesParticuleGeneral;
import tsp.ParticuleTSP;
import modele.Probleme;
import tsp.Routage;




public class ParticuleTSP extends Probleme {
	Graphe g;
	
	public ParticuleTSP(Graphe g, ArrayList<Etat> etat,double freq){
		/**
		 * le constructeur initial les autres valeurs sont "setables"
		 **/
	this.g=g;
	this.etat=etat;
	this.freq = freq;
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
	
	public static ParticuleTSP initialise(int nombreEtat, Graphe g, double freq){
		ArrayList<Etat> r = new ArrayList<Etat>(nombreEtat);
		for(int indice=0; indice<nombreEtat; indice++){
			r.add(new Routage(g));
		}
		for(int id=1; id<r.size()-1; id++){
			r.get(id).setprevious(r.get(id-1));
			r.get(id).setnext(r.get(id+1));
		}
		r.get(r.size()-1).setprevious( r.get(r.size()-2));
		r.get(r.size()-1).setnext( r.get(0));
		r.get(0).setprevious( r.get(r.size()-1));
		r.get(0).setnext(r.get(1));
		ParticuleTSP p=new ParticuleTSP(g,r,freq);
		return p;
	}
	
}
