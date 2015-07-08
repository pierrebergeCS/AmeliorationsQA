package sat3;

import java.util.ArrayList;
import modele.Etat;

public class EtatSat extends Etat {
	
	private int nbxi;
	private Instancesat instance;
	ArrayList<Minterme> clauses;
	ArrayList<ElementSat> liste;
	
	/**
	 * Cree un etat random utile pour l'initialisation
	 * @param p
	 * La Particulesat que va chercher a résoudre l'etat
	 */
	public EtatSat(Instancesat ins){
		this.nbxi=ins.getNbvar();
		ArrayList<ElementSat> l=new ArrayList<ElementSat>();
		this.clauses=new ArrayList<Minterme>();
		for(int i=1;i<=this.nbxi;i++){
			boolean b;
			b= Math.random()>0.5;
			ElementSat elemi= new ElementSat(i,b);
			l.add(elemi);
			this.majEnergie();
		}
		
		this.setListe(l);
		this.instance=ins;
		int[][] representation=this.instance.getSat();
		for(int i=0;i<this.instance.getNombreClauses();i++){
			Minterme m = new Minterme(i);
			for(int j=0;j<3;j++){
				int xi=Math.abs(representation[i][j]);
				int rienornot=(int) Math.signum(representation[i][j]);
				ElementSat e= (ElementSat) l.get(xi-1);
				m.addElem(e, j, rienornot);
				e.ajouteClause(m);
				
			}
			this.clauses.add(m);
				
		}
	}
	
	public EtatSat(){
		this.setListe(new ArrayList<ElementSat>());
		this.clauses=new ArrayList<Minterme>();
		this.majEnergie();
	}
	

	public void setListe(ArrayList<ElementSat> l){
		this.liste = l;
	}
	
	public ArrayList<ElementSat> getListe(){
		return this.liste;
	}
	
	
	public int getNbxi(){
		return this.nbxi;
	}
	
	public Instancesat getInstance(){
		return this.instance;
	}
	
	@Override
	public Etat clone() {
		EtatSat e = new EtatSat();
		ArrayList<ElementSat> l =this.getListe();
		int n = this.nbxi;
		for(int i=0;i<n;i++){
			boolean b;
			ElementSat c= ((ElementSat)l.get(i));
			b=c.getassignation();
			int j = c.getxi();
			ElementSat elemi= new ElementSat(j,b);
			e.getListe().add(elemi);
		}	
		e.setEnergie(this.energie);
		return e;
	}

	@Override
	public double majEnergie() {
		double cpt=0;
		for(Minterme m :this.clauses){
			if(!m.is()){
				cpt++;
			}
			
		}
		this.energie = cpt;
		return cpt;
	}

	private boolean not(boolean b) {
		if(b){
		return false;}
		else{
			return true;}
	}
	
	public void maj(){
		Instancesat ins = this.getInstance();
		this.nbxi=ins.getNbvar();
		ArrayList<ElementSat> l=new ArrayList<ElementSat>();
		this.clauses=new ArrayList<Minterme>();
		for(int i=1;i<=this.nbxi;i++){
			boolean b;
			b= Math.random()>0.5;
			ElementSat elemi= new ElementSat(i,b);
			l.add(elemi);
		}
		
		this.setListe(l);
		this.instance=ins;
		int[][] representation=this.instance.getSat();
		for(int i=0;i<this.instance.getNombreClauses();i++){
			Minterme m = new Minterme(i);
			for(int j=0;j<3;j++){
				int xi=Math.abs(representation[i][j]);
				int rienornot=(int) Math.signum(representation[i][j]);
				ElementSat e= (ElementSat) l.get(xi-1);
				m.addElem(e, j, rienornot);
				e.ajouteClause(m);
				
			}
			this.clauses.add(m);
			this.majEnergie();	
		}
	}

	@Override
	public double getResultat() {
		return this.getEnergie();
	}

}
