package OptimalNodeCoverage;

import java.util.ArrayList;

public class mainNodeCoverage {

	public static void main(String[] args) {
		int idAnchor=0;
		int dimX=10;
		int dimY=9;
		ArrayList<Anchor> l=new ArrayList<Anchor>(0);
		for(int xx =0; xx<dimX;xx++){
			for(int yy =0; yy<dimY;yy++){
				boolean b=xx<3&&yy>2&&yy<6||xx>6&&xx<10&&yy>2&&yy<6;
				if(!b){
					Anchor a = new Anchor(idAnchor,5*xx,4*yy,2.83) ;
					idAnchor++;
					l.add(a);
				}

			}
		}
		int n =l.size();


		boolean[][] los =new boolean[n][n];
		double[][] dist =new double[n][n];
		double dmax=12; //maximal distance for LOS Communiation
		boolean[][] valid =new boolean[n][n];
		for(int i=0;i<n-1;i++){
			Anchor ii=l.get(i);
			for(int j=i+1;j<n;j++){
				Anchor jj =l.get(j);
				boolean b = (ii.y)/4<=2&&(jj.y)/4<=2||(ii.y)/4>=6&&(jj.y)/4>=6||(ii.x)/5>=3&&(ii.x)/5<=6&&(jj.x)/5>=3&&(jj.x)/5<=6;
				if(b){
					los[i][j]=true;
					los[j][i]=true;
				}else{
					los[i][j]=false;
					los[j][i]=false;
				}
				double dista=Set.distance(ii.map2D(),jj.map2D());
				dist[i][j]=dista;
				dist[j][i]=dista;
				valid[i][j]=los[i][j]&&(dista<dmax);
				valid[j][i]=los[i][j]&&(dista<dmax);

			}
		}
		int tot=0;
		int[] freedom= new int[n];
		ArrayList<Integer> explore=new ArrayList<Integer>();
		for(int i=0;i<n;i++){
			int cpt=-4;
			for(int j=0;j<n;j++){
				if(valid[i][j]){
					cpt++;
				}
			}
			freedom[i]=cpt;
			explore.add(i);
			tot+=cpt;

		}
		CompareInt c =new CompareInt(freedom);
		explore.sort(c);
		double maxarea=000000;
		ArrayList<Set> globalsets=new ArrayList<Set>();
		for(int expl =0;expl<1;expl++){
			int compteurEx=expl;
			ArrayList<Set> sets= new ArrayList<Set>(0);
			int maxi=-1;
			for(int i=0;i<n;i++){
				maxi++;
				Anchor a= l.get(i);
				Set ss=a.getSet();
				if(ss==null){ //If the node is not in a Set
					Set s = new Set(20);			
					l.get(i).setSet(s);

					s.add(l.get(i));
					int cpt=0;
					while(s.size()<4&&cpt<n){
						//find valid combination
						if(valid[i][cpt]){
								
							//if not in any other Set
							if(l.get(cpt).getSet()==null){
								if(compteurEx!=0){
									l.get(cpt).setSet(s);
									s.add(l.get(cpt));
								}
								compteurEx--;
							}

						}

						cpt++;
					}

					//not a valid global combination (cant find 4 anchors to associate)
					if(s.size()<4){
						break;
					}

					sets.add(s);
				}
			}
			
	
			
			//adds remanant nodes in the closest set
			for(int i = maxi; i<n;i++){
				Anchor a= l.get(i);
				Set ss=a.getSet();
				if(ss==null){ //If the node is not in a Set
					for(int ii=0;ii<n;ii++){
						Anchor aa = l.get(ii);
						Set s = aa.getSet();
						if(s!=null){
							if(s.dist(a)<dmax){

								ArrayList<Anchor> aaa=s.getAnchor();
								boolean test=true;
								for(int iii =0; iii<aaa.size();iii++){
									if(!valid[a.identifier][aaa.get(iii).identifier]){
										test=false;break;
									}
								}						
								if(test){
									s.add(l.get(i));
									l.get(i).setSet(s); break;
								}
							}

						}

					}
				}
			}
			
			
			double area=0;
			boolean test=true;
			for(int i = maxi; i<n;i++){
				test=test&(l.get(i)!=null);
				if(!test){break;}
			}

			if(test){
				for(int i =0;i<sets.size();i++){
					Set s= sets.get(i);
					area+=s.getHull().area();
				}
			}


			if(maxarea<area){
				maxarea=area;
				globalsets=sets;
				System.out.println(area);

				
			}

			//reset the anchor allocation	
			for(int i=0;i<n;i++){l.get(i).setSet(null);}

	}

		
		for(int i =0;i<globalsets.size();i++){
			System.out.print("Set: ");System.out.print(i);System.out.print(" ");
			Set s= globalsets.get(i);System.out.println(s);
			System.out.println(s.getHull());
		}
		
	}
}



