package MKP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TransposeLator {
	
	
	/**
	 * Traduit un fichier au format 
	 * #Variables (n), #Constraints (m), Optimal value (0 if unavailable)
	 *	Profit P(j) for each n
	 * mx n matrix of constraints
	 *	Capacity b(i) for each m
	 * @param stringFichier
	 * @return
	 * @throws IOException
	 */
	public static Instance traduit(String stringFichier) throws IOException{
		BufferedReader br = null;
		Scanner scanner;
		// Le String thisLine stocke la ligne lue par le BufferReader
		String thisLine;
		
		br = new BufferedReader(new InputStreamReader(new FileInputStream(stringFichier)));
		
		int nbobj=0;
		int nbcontraintes=0;		
		String chaine ="";
		thisLine = br.readLine();
		Scanner s = new Scanner(thisLine);
		if(s.hasNextInt()){
		nbobj=s.nextInt();
		nbcontraintes=s.nextInt();
		}

		
		while ((thisLine = br.readLine()) != null) {
		chaine = chaine + " " +thisLine;
		}
		
		s= new Scanner(chaine);
		Objet[] obj=new Objet[nbobj];
		for(int i=0;i<nbobj;i++){
			obj[i]=new Objet(i,s.nextInt(),nbcontraintes);
		}
		for(int j=0;j<nbcontraintes;j++){
			for(int i=0;i<nbobj;i++){
				obj[i].assign(j, s.nextInt());
			}
		}
		
		int[] taille = new int[nbcontraintes];
		
		for(int j=0;j<nbcontraintes;j++){
			taille[j]=s.nextInt();
		}
		
		
		Instance i= new Instance(nbobj,nbcontraintes,taille,obj);
		
		return i;
		
		
		
	}

}
