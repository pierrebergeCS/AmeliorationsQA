package MKP;

import java.io.IOException;

public class mainmkp {

	public static void main(String[] args) throws IOException {
		Instance i= Translator.traduit("C:/Users/Baptiste/Desktop/RecuitQuantique/sac94/weish/weish01.dat");
		System.out.println(i.nombreObjets);
	}

}
