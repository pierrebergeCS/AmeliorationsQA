package LHD;

import java.util.ArrayList;

import modele.Etat;

public class EcVector extends EnergieCinetiqueLH{

	// Génère les chemin de vecteurs à partir de la grille
	//Les croix de départ sont au bord de la grille.
	public ArrayList<ChemindeVecteurs> generatePathVectors(Grille g){
		ArrayList<ChemindeVecteurs> liste = new ArrayList<ChemindeVecteurs>();
		int n = g.getTaille();
		int dim = ((Croix)g.getListe().get(0)).getDimension();
		
		for (int k = 0; k < n; k++){
			Croix c = (Croix) g.getListe().get(k);
			boolean b = false;
			for(int d = 0; d < dim; d++){
				b = (c.getCoord()[d] == 0) || (c.getCoord()[d] == n-1);
				if (b){
					Vecteur[] chemin = new Vecteur[n-1];
					if (c.getCoord()[d] == 0){
						Croix prev = c.clone();
						Croix next = c.clone();
						for (int i = 1; i < n; i++){
							prev = next.clone();
							next = ((Croix) g.getListe().get(0)).clone();
							for (int j = 0; j < n; j++){
								if (((Croix)g.getListe().get(j)).getCoord()[d] == i){
									next = ((Croix) g.getListe().get(j)).clone();
								}
							}
							Vecteur v = new Vecteur(prev,next);
							chemin[i-1] = v;
						}
					}
					if (c.getCoord()[d] == n-1){
						Croix prev = c.clone();
						Croix next = c.clone();
						for (int i = n-2; i >= 0; i--){
							prev = next.clone();
							next = ((Croix) g.getListe().get(0)).clone();
							for (int j = 0; j < n; j++){
								if (((Croix)g.getListe().get(j)).getCoord()[d] == i){
									next = ((Croix) g.getListe().get(j)).clone();
								}
							}
							Vecteur v = new Vecteur(prev,next);
							chemin[n-2-i] = v;
						}	
					}
					liste.add(new ChemindeVecteurs(chemin));
				}
			}
		}
		return liste;
	}
	
	public double compare(ChemindeVecteurs c1, ChemindeVecteurs c2){
		int n = c1.getChemin().length + 1;
		int dim = c1.getChemin()[0].getDimension();
		double cpt = Math.pow(10,30);
		Angle[][] a1 = c1.getAngles();
		Angle[][] a2 = c2.getAngles();
		for (int delta = 0; delta < dim-1; delta++){
			double temp = 0;
			for(int d = 0; d < dim-1; d++){
				for (int i = 0; i < n-2; i++){
					temp += Angle.determinant(a1[i][d],a2[i][(d+delta)%(dim-1)],a1[(i+1)%(n-2)][d],a2[(i+1)%(n-2)][(d+delta)%(dim-1)]);
				}
			}
			if (temp == 0) return 0;
			if (temp < cpt) cpt=temp;
		}
		for (int delta = 0; delta < dim-1; delta++){
			double temp = 0;
			for(int d = 0; d < dim-1; d++){
				for (int i = 0; i < n-2; i++){
					temp += Angle.determinant(a1[i][d],a2[i][(dim-1-d+delta)%(dim-1)],a1[(i+1)%(n-2)][d],a2[(i+1)%(n-2)][(dim-1-d+delta)%(dim-1)]);
				}
			}
			if (temp == 0) return 0;
			if (temp < cpt) cpt=temp;
		}
		return cpt;
	}
	
	@Override
	public double distanceIsing(Grille g1, Grille g2) {
		double cpt = 1;
		ArrayList<ChemindeVecteurs> l1 = generatePathVectors(g1);
		ArrayList<ChemindeVecteurs> l2 = generatePathVectors(g2);
		double minima = compare(l1.get(0),l2.get(0));
		
		for (int j = 0; j < l2.size(); j++){
			cpt = compare(l1.get(0),l2.get(j));
			if (cpt < minima) minima = cpt;
		}
		
		return -minima;
	}

	@Override
	public double calculerEc(ParticuleLH p) {
		//C'est la somme des distances d'Ising entre les répliques couplées.
			int nombreEtat = p.nombreEtat();
			double cpt =0;
			for (int k = 0; k < nombreEtat;k++){
					Etat e1=p.getEtat().get(k);// Etat courant
					Etat e2=e1.getNext();// Etat suivant dans la chaîne
					cpt+=distanceIsing((Grille)e1,(Grille)e2);//On calcule la distance d'Ising
			}
			return cpt;
	}

	@Override
	public double deltaSpins(ParticuleLH p, Grille e, MutationLH m) {
		ParticuleLH pclone = (ParticuleLH) p.clone();
		for (int i = 0; i < p.nombreEtat(); i++){
			if (pclone.getEtat().get(i).equals(e)){
				m.faire(pclone,pclone.getEtat().get(i));
			}
		}
		return (p.getEc().calculerEc(pclone) - p.getEc().calculerEc(p));
	}

}
