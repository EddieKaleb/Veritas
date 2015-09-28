package ExampleMain;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import Cryptography.Crypto;
import Keys.Keys;
import QRCode.QRCode;

public class Main {

	public static void main(String[] args) {
		try {
			Keys keys = new Keys();
			keys.storeKeys();
			Crypto c = new Crypto(keys);
			Map hintMap = new HashMap();
			String encrypt = c.encrypt(JOptionPane.showInputDialog("Input your text: "));
			
			QRCode qr = new QRCode(encrypt);
			String decrypt = c.decrypt(qr.read("UTF-8", hintMap));
			System.out.println(decrypt);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}