package LHD;

public class ChemindeVecteurs {
	Vecteur[] listeV;//vecteurs du chemin
	Angle[][] angles;//Matrice d'angles 
	//angles[i][j] donne l'angle entre le vecteur i et i+1 projetés sur les dimensions j et j+1
	
	public ChemindeVecteurs(Vecteur[] v, Angle[][] a){
		this.listeV = v;
		this.angles = a;
	}
	
	public ChemindeVecteurs(Vecteur[] l){
		this.listeV = l;
		//angles
		int k = l[0].getCoord().length;
		this.angles = new Angle[l.length-1][l[0].getCoord().length-1];
		for (int i = 0; i < l.length-1; i++){
			for (int j = 0; j < l[0].getCoord().length-1;j++){
				this.angles[i][j] = this.listeV[i].calculeAngle(this.listeV[i+1],j,j+1);
			}
		}
	}
	
	public Vecteur[] getChemin(){
		return this.listeV;
	}
	
	public Angle[][] getAngles(){
		return this.angles;
	}
	
	public void afficheChemin(){
		//affiche vecteurs
		System.out.println("vecteurs");
		String s1 = "";
		for (int i = 0; i < this.listeV.length-1; i++){
			s1 += this.listeV[i].toString() + "->";
		}
		s1 += this.listeV[this.listeV.length-1];
		System.out.println(s1);
		//affiche angles
		System.out.println("angles");
		for (int k = 0; k < this.angles.length; k++){
			String s2 = "";
			for (int m = 0; m < this.angles[0].length; m++){
				s2 += this.angles[k][m].toString() + " dim" + m + (m+1) + "  ";
			}
			System.out.println(s2);
		}
		System.out.println("---------END----------");
	}
	
}
