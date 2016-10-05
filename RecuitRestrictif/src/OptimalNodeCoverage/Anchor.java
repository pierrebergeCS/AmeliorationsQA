package OptimalNodeCoverage;

public class Anchor {
	public int identifier;
	public double x;
	public double y;
	public double z;
	private Set set;
	
	
	public void setSet(Set s){
		this.set=s;
	}
	
	
	public void changeSet(Set d){
		this.set.remove(this.identifier);
		d.add(this);
		this.set=d;
		
	}
	
	public Set getSet(){
		return this.set;
	}

	public Anchor(int id, double d,double e,double z){
		this.identifier=id;
		this.x=d;
		this.y=e;
		this.z=z;	
	}

	public Point2D map2D(){
		return new Point2D(x,y);
	}


}
