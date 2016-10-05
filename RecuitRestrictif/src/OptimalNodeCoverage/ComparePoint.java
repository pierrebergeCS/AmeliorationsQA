package OptimalNodeCoverage;

import java.util.Comparator;

public class ComparePoint implements Comparator<Point2D> {

	@Override
	public int compare(Point2D arg0, Point2D arg1) {
		if(arg0.getX()>arg1.getX()){
		return 1;
		}
		if(arg0.getX()<arg1.getX()){
			return -1;
			}
		if(arg0.getY()>arg1.getY()){
			return 1;
			}
		if(arg0.getY()<arg1.getY()){
			return -1;
			}
		return 0;
	}

}
