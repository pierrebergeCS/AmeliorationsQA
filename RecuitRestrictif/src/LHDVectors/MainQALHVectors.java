package LHDVectors;

public class MainQALHVectors {

	public static void main(String[] args) {
			int n = 7;
			int d = 3;
			Phi f = new Phi(5);
			
			Grille g = new Grille(f,n,d);
			g.afficheGrille();
			MutationLH m = new MutationLH(g);
			m.faire(g);
			g.afficheGrille();
	}

}
