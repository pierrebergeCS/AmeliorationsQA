package OptimalNodeCoverage;

import java.util.ArrayList;

import modele.Element;
import modele.Etat;

public class Coverage extends Etat {
	boolean[][] valid;
	private ArrayList<Set> sets;
	private ArrayList<Anchor> l;
	private InstanceNodeCoverage ins;

	public Coverage(InstanceNodeCoverage ins){
		int n = ins.anchors.size();
		ArrayList<Element> l = new ArrayList<Element>();
		for (int i = 0; i < n; i++){
			l.add(new ElementNodeCoverage(ins.anchors.get(i).clone(),null));
		}
		this.ins = ins;
		this.valid=ins.valid;
		this.sets=new ArrayList<Set>(0);
		this.setListe(l);
	}

	public Coverage(ParticuleNodeCoverage particule) {
		this.ins=particule.getIns();
		int n = ins.anchors.size();;
		ArrayList<Element> l = new ArrayList<Element>();

		for (int i = 0; i < n; i++){
			l.add(new ElementNodeCoverage(ins.anchors.get(i).clone(),null));
		}
		this.setListe(l);

	}

	public InstanceNodeCoverage getInstance(){
		return this.ins;
	}
	

	@Override
	public Etat clone() {
		Coverage r = new Coverage(this.ins);
		r.setEnergie(this.getEnergie());
		return r;
	}
	
	@Override
	public double majEnergie() {
		Trouver la metrique
	}

	@Override
	public int distanceIsing(Etat e) {
		Hashin
	}

	@Override
	public double getResultat() {
		return this.getEnergie();
	}

	public boolean test(Anchor anchor, Set s) {
		ArrayList<Anchor> aaa=s.getAnchor();
		boolean test=true;
		for(int iii =0; iii<aaa.size();iii++){
			if(!valid[anchor.identifier][aaa.get(iii).identifier]){
				test=false;break;
			}
		}
		return test;
	}

	public ArrayList<Set> getSets() {
		// TODO Auto-generated method stub
		return this.sets;
	}

}
