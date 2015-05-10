package sat3;
/**
 * 
 * @author Baptiste
 *un minterme est un tableau d'élementsat, et un tableau de +1,-1 pour savoir si on considère Xi ou not Xi
 */
public class Minterme {
private int number;
	int[] calque;
	ElementSat[] tab;
	/**
	 * Constructeur du minterme intialise tout a 0 et a null;
	 */
	public Minterme(int nombre){
		this.calque=new int[3];
		this.tab=new ElementSat[3];
		this.number=nombre;
	}
	/**
	 *Ajoute un élément et sa valeur au minterme 
	 * @param e
	 * Element a ajouter	
	 * @param index
	 * place dans le minterme
	 * @param rienornot
	 * +ou-1 en fonction de si c'est xi ou -xi
	 */
	public void addElem(ElementSat e,int index,int rienornot){
		this.calque[index]=rienornot;
		this.tab[index]=e;	
	}
	
	/**
	 * 
	 * @return la valeur boolean du minterme
	 */
	public boolean is(){
		return((this.calque[0]==1&&this.tab[0].getassignation() )||
				(this.calque[0]==-1&& not(this.tab[0].getassignation() ))||
				(this.calque[1]==1&&this.tab[1].getassignation() )||
				(this.calque[1]==-1&& not(this.tab[1].getassignation() ))|| 
				(this.calque[2]==1&&this.tab[2].getassignation() )||
				(this.calque[2]==-1&& not(this.tab[2].getassignation() )));
	}

	private boolean not(boolean b) {
		if(b){
			return false;}
		else{
			return true;}
	}

	public int getIndex(ElementSat e){
		if (this.tab[0].getxi()==e.getxi()) return 0;
		if (this.tab[1].getxi()==e.getxi()) return 1;
		return 2;
	}
	public int getNumber() {

		return this.number;
	}

}
