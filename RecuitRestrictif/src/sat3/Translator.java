package sat3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * 
 * 
 * Cette classe est charg�e de l'interpr�tation d'un probl�me de type sat mis sous la forme DIMACS celle des benchmarks de comp�tition de sat
 * pour le transcrire en chose compr�hensible pour notre ami le QA
 * 
 * @author La groupie du pianiste
 *
 */
public class Translator {
	public static Instancesat donneInstance(String stringFichier) throws IOException{
		BufferedReader br = null;
		Scanner scanner;

		// Le String thisLine stocke la ligne lue par le BufferReader
		String thisLine;
		


			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(stringFichier)));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// On stocke les données utiles (coefficients de matrice ou coordonnées dans "chaine")
			String chaine ="";
			int nbvar = 0;
			int nbclauses = 0;



			while ((thisLine = br.readLine()) != null) {
				if(thisLine.startsWith("c")){
				}else if(thisLine.startsWith("p")){
					Scanner s = new Scanner(thisLine);
					nbvar=s.nextInt();
					nbclauses=s.nextInt();
					s.close();
				}
				else{
					chaine = chaine + " " +thisLine.substring(0, thisLine.length()-2);
				}
			}
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
				scanner = new Scanner(chaine);
				Instancesat ins =new Instancesat(nbvar,nbclauses);
				int[][] tab = ins.getSat();
				for(int i=0;i<nbclauses;i++){
					for(int j=0;j<3;j++){
						tab[i][j]=scanner.nextInt();
					}
				}	
				scanner.close();
				return ins;
		
		}
	}


