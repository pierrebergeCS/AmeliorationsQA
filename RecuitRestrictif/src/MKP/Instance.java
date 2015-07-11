package MKP;

public class Instance {
	int nombreObjets;
	int nombreSacs;
	int[] capacite;
	Objet[] obj;
	
	
	
	public Instance(int nbobj,int nbsac,int[] capa,Objet[] obj){
		this.nombreObjets=nbobj;
		this.nombreSacs=nbsac;
		this.capacite=capa;
		this.obj=obj;
	}
	
	
	public int getNombreObjets() {
		return nombreObjets;
	}
	public void setNombreObjets(int nombreObjets) {
		this.nombreObjets = nombreObjets;
	}
	public int getNombreSacs() {
		return nombreSacs;
	}
	public void setNombreSacs(int nombreSacs) {
		this.nombreSacs = nombreSacs;
	}
	public int[] getCapacite() {
		return capacite;
	}
	public void setCapacite(int[] capacite) {
		this.capacite = capacite;
	}
	public Objet[] getObj() {
		return obj;
	}
	public void setObj(Objet[] obj) {
		this.obj = obj;
	}
	

}
