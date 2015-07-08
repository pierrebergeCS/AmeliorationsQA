package LHDVectors;

public class MainQALHVectors {

	public static void main(String[] args) {
			int n = 5;
			int d = 2;
			Phi f = new Phi(5);
			
			Grille g = new Grille(f,n,d);
			g.afficheGrille();
			Grille g2 = (Grille) g.clone();
			g2.afficheGrille();
			g.findCriticalPoints();
			System.out.println(g.getCriticalPoints().get(0));
			System.out.println(g.getCriticalPoints().get(1));
	}

}
