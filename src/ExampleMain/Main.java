package ExampleMain;

import javax.swing.JOptionPane;

import qrCode.QRCode;
import reader.Reader;
import cryptography.Crypto;


public class Main {

	public static void main(String[] args) {
		try {
			Crypto crypto = new Crypto();
			 String encrypted = crypto.encrypt("oi tudo bom? como vai vc");
			 System.out.println(encrypted);
			 String decrypted = crypto.decrypt(encrypted);
			 System.out.println(decrypted);
			 
			 
			 JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Lerigou");
			 QRCode qr = new QRCode(encrypted);
			 System.out.println(Reader.readQr());
			 
			 
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}