package reader;

import cryptography.Crypto;



/**
 * 
 * @author thayanneLuiza
 *
 */

public class Reader  {
	public static String readQr() throws Exception{
		new WebcamQRCode();
		String data = WebCam.init();
		
		return(data);	
	}
	
	
}
