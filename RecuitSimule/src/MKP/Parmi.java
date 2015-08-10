package MKP;

public class Parmi {
	
	public static int calculer(int n, int k){
		double cpt = 1.0;
		int k1 = Math.min(k,n-k);
		int k2 = Math.max(k,n-k);
		for (int i = 1; i <= n; i++){
			if (i <= k1 && i <= k2) cpt = cpt/i;
			if (i > k2) cpt=cpt*i;
		}
		return ((int) cpt);
	}

}
