package OptimalNodeCoverage;

import java.util.ArrayList;

/**
 * A convex hull is a convex polygon clockwise oriented in our implementation
 * The Hull is created following Andrew's Monotone Chain Algorithm O(nlogn)
 *
 */
public class Hull extends Polygon {

	public ArrayList<Point2D> findHull(ArrayList<Point2D> p ){


		ArrayList<Point2D> l= Polygon.order(p);
		ArrayList<Point2D> h=new ArrayList<Point2D>(0);

		int    bot=0, top=(-1);   // indices for bottom and top of the stack
		int    i;                 // array scan index
		int n = l.size();
		// Get the indices of points with min x-coord and min|max y-coord
		int minmin = 0, minmax;
		double xmin = l.get(0).getX();
		for (i=1; i<n; i++)
			if (l.get(i).getX() != xmin) break;
		minmax = i-1;
		if (minmax == n-1) {       // degenerate case: all x-coords == xmin
			h.add(++top, l.get(minmin));
			if (l.get(minmax).getY() != l.get(minmin).getY()) // a  nontrivial segment
				h.add(++top, l.get(minmax));
			h.add(++top, l.get(minmin));             // add polygon endpoint
			return h;
		}

		// Get the indices of points with max x-coord and min|max y-coord
		int maxmin, maxmax = n-1;
		double xmax = l.get(n-1).getX();
		for (i=n-2; i>=0; i--)
			if (l.get(i).getX() != xmax) break;
		maxmin = i+1;

		// Compute the lower hull on the stack H
		h.add(++top,l.get(minmin));
		// push  minmin point onto stack
		i = minmax;
		while (++i <= maxmin)
		{
			// the lower line joins l.get(minmin]  with l.get(maxmin]
			if (isLeft( l.get(minmin), l.get(maxmin), l.get(i))  >= 0 && i < maxmin)
				continue;           // ignore P[i] above or on the lower line

			while (top > 0)         // there are at least 2 points on the stack
			{
				// test if  P[i] is left of the line at the stack top
				if (isLeft(  h.get(top-1), h.get(top), l.get(i)) > 0)
					break;         // P[i] is a new hull  vertex
				else
					top--;         // pop top point off  stack
			}
			h.add(++top,l.get(i));
			// push P[i] onto stack
		}

		// Next, compute the upper hull on the stack H above  the bottom hull
		if (maxmax != maxmin)      // if  distinct xmax points
			h.add(++top,l.get(maxmax)); // push maxmax point onto stack
		bot = top;                  // the bottom point of the upper hull stack
		i = maxmin;
		while (--i >= minmax)
		{
			// the upper line joins l.get(maxmax]  with l.get(minmax]
			if (isLeft( l.get(maxmax), l.get(minmax), l.get(i))  >= 0 && i > minmax)
				continue;           // ignore P[i] below or on the upper line

			while (top > bot)     // at least 2 points on the upper stack
			{
				// test if  P[i] is left of the line at the stack top
				if (isLeft(  h.get(top-1), h.get(top), l.get(i)) > 0)
					break;         // P[i] is a new hull  vertex
				else
					top--;         // pop top point off  stack
			}
			h.add(++top,l.get(i));       // push P[i] onto stack
		}
		if (minmax != minmin)
			h.add(++top,l.get(minmin));  // push  joining endpoint onto stack

		return h;
	}

	public Hull(ArrayList<Point2D> p){
		super();
		this.sommets=findHull(p);
	}


	public boolean isEdge(Point2D p){
		for(int i=0;i<this.sommets.size();i++){
			if(c.compare(p,this.sommets.get(i))==0){
				return true;
			}
		}
		return false;
	}
	public String toString(){

		String s="";

		for(int i  =0;i<this.sommets.size();i++){
			s=s.concat("Anchor ");s=s.concat(((Double)this.sommets.get(i).getX()).toString());s=s.concat("; ");
			s=s.concat(((Double)this.sommets.get(i).getY()).toString());s=s.concat(".. ");
		}
		return s;

	}
}




