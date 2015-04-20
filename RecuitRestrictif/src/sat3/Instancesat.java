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
public int getNbvar(){
	return this.nbvar;
}

}
