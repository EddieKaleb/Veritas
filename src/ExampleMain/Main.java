package ExampleMain;

import Cryptography.Crypto;
import Keys.Keys;


public class Main {

	public static void main(String[] args) {
		try {
			Crypto crypto = new Crypto();
			 String encrypted = crypto.encrypt("oi tudo bom? como vai vc");
			 System.out.println(encrypted);
			 String decrypted = crypto.decrypt(encrypted);
			 System.out.println(decrypted);
			 
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}