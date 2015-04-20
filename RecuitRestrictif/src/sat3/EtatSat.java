package sat3;

import modele.Etat;

public class EtatSat extends Etat {
	/**
	 * Cree un etat random utile pour l'initialisation
	 * @param p
	 * La Particulesat que va chercher a résoudre l'etat
	 */
	public EtatSat(Particulesat p){
		int n = p.getnombreXi();
		for(int i=0;i<n;i++){
			boolean b;
			b= Math.random()>0.5;
			ElementSat elemi= new ElementSat(i,b);
			this.getListe().add(elemi);
		}
	}
	
	
	
	@Override
	public Etat clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getEnergie() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int distanceIsing(Etat e) {
		// TODO Auto-generated method stub
		return 0;
	}

}
