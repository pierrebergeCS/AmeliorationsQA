package sat3;

import java.util.ArrayList;

import modele.Element;
import modele.Etat;

public class EtatSat extends Etat {
	
	private int nbxi;
	private Instancesat instance;
	ArrayList<Minterme> clauses;
	
	/**
	 * Cree un etat random utile pour l'initialisation
	 * @param p
	 * La Particulesat que va chercher a résoudre l'etat
	 */
	public EtatSat(Particulesat p){
		int n = p.getnombreXi();
		this.nbxi=n;
		ArrayList<Element> l=new ArrayList<Element>();
		this.clauses=new ArrayList<Minterme>();
		for(int i=1;i<=n;i++){
			boolean b;
			b= Math.random()>0.5;
			ElementSat elemi= new ElementSat(i,b);
			l.add(elemi);
		}
		
		this.instance=p.getInstance();
		int[][] representation=this.instance.getSat();
		this.setListe(l);
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
		this.majEnergie();
	}
	
	public EtatSat(){
		this.setListe(new ArrayList<Element>());
		this.clauses=new ArrayList<Minterme>();
		this.majEnergie();
	}
	
	public EtatSat(Instancesat ins) {
		this.clauses=new ArrayList<Minterme>();
		int n = ins.getNbvar();
		this.nbxi=n;
		ArrayList<Element> l=new ArrayList<Element>();
		for(int i=1;i<=n;i++){
			boolean b;
			b= Math.random()>0.5;
			ElementSat elemi= new ElementSat(i,b);
			l.add(elemi);
		}
		
		this.instance=ins;
		int[][] representation=this.instance.getSat();
		this.setListe(l);
		l=this.getListe();
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
		this.majEnergie();
	}

	public int getNbxi(){
		return this.nbxi;
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
		}
		this.majEnergie();
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
		this.setEnergie(cpt);
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
		int cpt=0;
		ArrayList<Element> l1=this.getListe();
		ArrayList<Element> l2=e.getListe();
		for(Element i :l1){
			int j =0;
			int xi1=((ElementSat) i).getxi();
			boolean b1=((ElementSat) i).getassignation();
			
			while(((ElementSat) l2.get(j)).getxi()!=xi1){
				j++;
			}
			if(((ElementSat) l2.get(j)).getassignation()!=b1){
				cpt++;
			}
		}
		
		
		
		
		return cpt;
	}
	
	@Override
	public double getResultat() {
		this.majEnergie();
		return this.getEnergie();
	}

}
