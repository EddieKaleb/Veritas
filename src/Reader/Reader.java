package Reader;

import Cryptography.Crypto;



/**
 * 
 * @author thayanneLuiza
 *
 */

public class Reader  {
	public static String readQr() throws Exception{
		Crypto c = new Crypto();
		String data = WebCam.init();
		return(c.decrypt(data));	
	}
	
	
}
