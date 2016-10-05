package OptimalNodeCoverage;

public class Anchor {
	public int identifier;
	public double x;
	public double y;
	public double z;


	public Anchor(int id, double d,double e,double z){
		this.identifier=id;
		this.x=d;
		this.y=e;
		this.z=z;	
	}

	public Point2D map2D(){
		return new Point2D(x,y);
	}
	public boolean equals(Anchor a){
		return this.identifier==a.identifier&&this.x==a.x&&this.y==a.y&&this.z==a.z;
		
	}
	public Anchor clone(){
		return new Anchor(identifier,x,y,z);
	}

}
