package parametres;

public class TemperatureExp extends Temperature {
	double start;
	double end;
	double value;
	
	public TemperatureExp(double start, double end){
		this.start = start;
		this.end = end;
		this.value = start;
	}

	@Override
	public void maj(int currentIter, int nbIterations) {
		double r = Math.pow(this.end/this.start,1.0/nbIterations);
		this.value = this.start*Math.pow(r,currentIter);		
	}

	@Override
	public double getValue(){
		return this.value;
	}
	
	public void setValue(double value){
		this.value = value;
	}

}
