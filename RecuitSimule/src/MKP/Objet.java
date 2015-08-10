package MKP;

public class Objet {
int numeroObjet;
int utility;
int[] weight;


public Objet(int numero,int utility,int m){
	this.numeroObjet=numero;
	this.utility=utility;
	this.weight=new int[m];
}

public Objet(int numero, int utility, int[] weight){
	this.numeroObjet=numero;
	this.utility=utility;
	this.weight=weight;
}

public void assign(int i,int valeur){
	this.weight[i]=valeur;
}
public void setWeight(int[] weight){
	this.weight=weight;
}

public int getNumero(){
	return this.numeroObjet;
}
public int getUtility(){
	return this.utility;
}
public int[] getWeight(){
	return this.weight;
}
}
