package OptimalNodeCoverage;
import java.util.ArrayList;
import java.util.Comparator;
/**
 * All the algorithms for polygons are extracted from http://geomalgorithms.com/
 * All polygons must be generated without self intersection
 *
 **/
public class Polygon {
	static final Comparator<? super Point2D> c = new ComparePoint();
	
	ArrayList<Point2D> sommets;

	public Polygon(){
	
		this.sommets= new ArrayList<Point2D>();
	}

	public boolean isIn(Point2D p){ //uses the winding number method
		if(this.sommets.isEmpty()){
			return false;
		}else{
			int    wn = 0;
			int 	n=this.sommets.size()-1;
			for (int i=0; i<n; i++) {   // edge from V[i] to  V[i+1]
				Point2D p1=this.sommets.get(i);
				Point2D p2= this.sommets.get(i+1);
				if (p1.getY() <= p.getY()) {          // start y <= P.y
					if (p2.getY()  >p.getY())      // an upward crossing
						if (isLeft(p1, p2, p) > 0)  // P left of  edge
							++wn;            // have  a valid up intersect
				}
				else {                        // start y > P.y (no test needed)
					if (p2.getY()  <= p.getY())     // a downward crossing
						if (isLeft( p1, p2, p) < 0)  // P right of  edge
							--wn;            // have  a valid down intersect
				}
			}




			return wn!=0;	
		}
	}

	public double area(){
		double area = 0;
		int n=this.sommets.size()-1;
		int  i, j, k;   // indices
		if(n>3){
			for (i=1, j=2, k=0; j<=n; i++, j++, k++) {
				area += this.sommets.get(i).getX() * (this.sommets.get(j).getY() - this.sommets.get(k).getY());
			}
			area += this.sommets.get(n).getX() * (this.sommets.get(1).getY() - this.sommets.get(n-1).getY());  // wrap-around term
		}

		return area/2;
	}



	public double isLeft(Point2D p0,Point2D p1,Point2D p2){

		//	    Return: >0 for P2 left of the line through P0 and P1
		//      =0 for P2  on the line
		//      <0 for P2  right of the line


		return ( (p1.getX() - p0.getX()) * (p2.getY() - p0.getY())
				- (p2.getX() - p0.getX()) * (p1.getY() - p0.getY())); 
	}

	public void add(Point2D p){
		this.sommets.add(p);
	}

	
	/**
	 * Order lexicographically (x then y) the list of points l complexity at worst O(nlogn)
	 * @param l
	 * 	The list of points
	 * 
	 * @return  order the list of points
	 */
	public static ArrayList<Point2D>  order(ArrayList<Point2D> l) {
		ArrayList<Point2D> h=(ArrayList<Point2D>) l.clone();
		h.sort(c);
		return h;
	}




}
