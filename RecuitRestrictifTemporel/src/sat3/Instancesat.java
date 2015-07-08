package sat3;

public class Instancesat {
private int nbvar;
private int nbclauses;
private int[][] tab;

public Instancesat(int var,int clauses){
	this.nbvar=var;
	this.nbclauses=clauses;
	this.tab=new int[clauses][3];
}


public int[][] getSat(){
	return this.tab;
}
public int getNombreClauses(){
	return this.nbclauses;
}
public int getNbvar(){
	return this.nbvar;
}
public String toString(){
	String s= new String();
	s=s+"[ ";
	for(int i =0;i<nbclauses;i++){
		s+="[ ";
		for(int j =0;j<2;j++){
			s+=tab[i][j]+"; ";
		}
		
		s+=tab[i][2] +" ]";
	}
	return s+" ]";
	
}
}
