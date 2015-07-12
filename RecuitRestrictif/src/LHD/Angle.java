package LHD;

public class Angle {
	double cos;
	double sin;
	
	public Angle(double cos, double sin){
		this.cos = cos;
		this.sin = sin;
	}
	
	public Angle(double valeur){
		this.cos = Math.cos(valeur);
		this.sin = Math.sin(valeur);
	}
	
	public double getCos(){
		return this.cos;
	}
	
	public double getSin(){
		return this.sin;
	}
	
	//Entre -180° et +180°
	public double valeurAngle(){
		if (this.sin > 0){
			return Math.acos(this.cos);
		} else {
			return -Math.acos(this.cos);
		}
	}
	
	//Determinant : a1a4-a2a3
	public static double determinant(Angle a1, Angle a2, Angle a3, Angle a4){
		double cos = a1.getCos()*a4.getCos()-a2.getCos()*a3.getCos();
		double sin = a1.getSin()*a4.getSin()-a2.getSin()*a3.getSin();
		return cos*cos+sin*sin;
	}
	
	public String toString(){
		String s = "(";
		return (s + this.cos + "," + this.sin + ")");
	}

}
