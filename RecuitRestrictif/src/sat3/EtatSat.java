package sat3;

import java.util.ArrayList;

import modele.Element;
import modele.Etat;

public class EtatSat extends Etat {
	
	private int nbxi;
	private Instancesat instance;
	
	/**
	 * Cree un etat random utile pour l'initialisation
	 * @param p
	 * La Particulesat que va chercher a résoudre l'etat
	 */
	public EtatSat(Particulesat p){
		int n = p.getnombreXi();
		this.nbxi=n;
		for(int i=1;i<=n;i++){
			boolean b;
			b= Math.random()>0.5;
			ElementSat elemi= new ElementSat(i,b);
			this.getListe().add(elemi);
		}
		
		this.instance=p.getInstance();
	}
	
	public EtatSat(){
		
	}
	
	
	
	
	
	
	@Override
	public Etat clone() {
		EtatSat e = new EtatSat();
		ArrayList<Element> l =this.getListe();
		int n = this.nbxi;
		for(int i=0;i<n;i++){
			boolean b;
			ElementSat c= ((ElementSat)l.get(i));
			b=c.getassignation();
			int j = c.getxi();
			ElementSat elemi= new ElementSat(j,b);
			e.getListe().add(elemi);
		}		return e;
	}

	@Override
	public double getEnergie() {
		double cpt=0;
		int[][]t=instance.getSat();
		int n1=t.length;
		boolean[][] t2=new boolean[n1][3];
		for(int i=0;i<this.nbxi;i++){
			ElementSat ei=(ElementSat) (this.getListe().get(i));
			int xi = ei.getxi();
			boolean b=ei.getassignation();
			for(int j=0;j<n1;j++){
				for(int l =0;l<3;l++){
					if(t[j][l]==xi){
						t2[j][l]=b;
					}if((t[j][l]==-xi)){
						t2[j][l]=not(b);
					}
				}
			}
		}
		for(int j=0;j<n1;j++){
			if(not(t2[j][1]&&t2[j][0]&&t2[j][2])){
				cpt++;
			}
		}
		
		
		return cpt;
	}

	private boolean not(boolean b) {
		if(b){
		return false;}
		else{
			return true;}
	}

	@Override
	public int distanceIsing(Etat e) {
		// TODO Auto-generated method stub
		return 0;
	}

}
