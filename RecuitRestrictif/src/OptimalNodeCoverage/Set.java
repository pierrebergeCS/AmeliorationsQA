package OptimalNodeCoverage;
import java.util.ArrayList;


public class Set {
	private ArrayList<Anchor> anchors;
	private Hull hull;
	private  ArrayList<Integer> tableau;


	public Set(int size){
		this.anchors= new ArrayList<Anchor>(size) ;
		this.tableau=new ArrayList<Integer>(20);
	}

	public Set(ArrayList<Anchor> a) {
		this.anchors=a;
	}

	public void add(Anchor i){

		this.anchors.add(i);
		this.tableau.add(i.identifier);

	}
	public void remove(int i){
		if(this.contains(i)){
			this.anchors.remove(this.get(i));
		}
	}
	public int size(){
		return anchors.size();
	};

	public double dist(Anchor a){
		double max=0;
		for(int i =0;i< this.anchors.size();i++){
			double d=distance(a.map2D(), this.anchors.get(i).map2D());
			if(d>max){
				max=d;
			}
		}

		return max;
	}


	public Anchor get(int i) {
		Anchor ii = null;
		int j =0;
		int l=this.anchors.size();
		while(j<l){
			Anchor a = this.anchors.get(j);
			if(a.identifier==i){
				ii=a;
				j=l;
			}
			j++;

		}

		return ii;
	}

	public boolean contains(int i) {
		Anchor ii = null;
		int j =0;
		int l=this.anchors.size();
		while(j<l){
			Anchor a = this.anchors.get(j);
			if(a.identifier==i){
				ii=a;
				j=l;
			}
			j++;

		}
		return(ii!=null);
	}


	public Hull getHull(){
		if(this.hull == null){ //the hull isnt initialized
			ArrayList<Point2D> p = new ArrayList<Point2D>(0);

			for(int i =0;i<anchors.size();i++){
				p.add(anchors.get(i).map2D());
			}

			Hull h = new Hull(p);
			this.hull=h;
		}

		return this.hull;
	}


	public void updateHull(){
		ArrayList<Point2D> p = new ArrayList<Point2D>(0);
		for(int i =0;i<anchors.size();i++){
			p.add(anchors.get(i).map2D());
		}

		Hull h = new Hull(p);
		this.hull=h;
	}



	public boolean changeHullAdd(Anchor a){
		Point2D p = a.map2D();
		if(this.hull.isIn(p)){
			return false;
		}		
		return true;

	}


	public boolean changeHullRem(Anchor a){
		Point2D p = a.map2D();
		if(this.hull.isEdge(p)){
			return true;
		}		
		return false;

	}

	public ArrayList<Anchor> getAnchor() {
		// TODO Auto-generated method stub
		return this.anchors;
	}

	public String toString(){
		String s="";

		for(int i  =0;i<tableau.size();i++){
			s=s.concat("Anchor ");s=s.concat(tableau.get(i).toString());s=s.concat("; ");
		}
		return s;

	}


	public static double distance(Point2D i, Point2D j){		
		return Math.sqrt(Math.pow((i.getX()-j.getX()),2)+ (Math.pow((i.getY()-j.getY()),2)));	
	}



	public boolean equals(Set s){
		boolean b=true;
		ArrayList<Anchor> a=s.getAnchor();
		int n=a.size();
		if(n!=this.size()){return false;}
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(anchors.get(i).equals(a.get(j))){
					break;
				}
				if(j==n-1){
					b=false;
				}
			}
			if(!b){
				break;
			}
		}
		return b;


	}

	public Set clone(){
		ArrayList<Anchor> a=new ArrayList<Anchor>()  ;
		int n= this.size();
		for(int i=0;i<n;i++){
			a.add(this.anchors.get(i).clone());
		}
		return new Set(a);
	}






}
