package keys;

import java.io.*;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;






public class KeysPersistence {
	private static final String path = "/users/thayanneLuiza/Documents/Veritas/";
	private final String RSA = "RSA/ECB/PKCS1Padding";
	private final String BC = "BC";
	

	
	

	
	/**
	 * Builder default. Using algorithm RSA and PKCS1 padding for generate keys.
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 */
	public static KeyPair createKeys() throws NoSuchAlgorithmException, NoSuchProviderException{
		SecureRandom random = new SecureRandom();
	    Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");

	    generator.initialize(1028, random);

	    KeyPair pair = generator.generateKeyPair();
	   
	    return pair;
	}
	
	
	
	/**
	 * Method that store the keys.
	 * @throws IOException
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 */
	public static void storeKeys() throws IOException, NoSuchAlgorithmException, NoSuchProviderException{
		KeyPair keys = createKeys();
		PrivateKey PK = keys.getPrivate();
		PublicKey PU = keys.getPublic();
		
		
 
		// Store Public Key.
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(
				PU.getEncoded());
		FileOutputStream fos = new FileOutputStream(path + "/public.key");
		fos.write(x509EncodedKeySpec.getEncoded());
		fos.close();
 
		// Store Private Key.
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
				PK.getEncoded());
		fos = new FileOutputStream(path + "/private.key");
		fos.write(pkcs8EncodedKeySpec.getEncoded());
		fos.close();
	}
	
	/**
	 * Method that load keys
	 * @return Keys
	 * @throws Exception 
	 */
	
	public static KeyPair loadKeys() throws Exception{
		// Read Public Key.
		File filePublicKey = new File(path + "/public.key");
		FileInputStream fis = new FileInputStream(path + "/public.key");
		byte[] encodedPublicKey = new byte[(int) filePublicKey.length()];
		fis.read(encodedPublicKey);
		fis.close();
 
		// Read Private Key.
		File filePrivateKey = new File(path + "/private.key");
		fis = new FileInputStream(path + "/private.key");
		byte[] encodedPrivateKey = new byte[(int) filePrivateKey.length()];
		fis.read(encodedPrivateKey);
		fis.close();
 
		// Generate KeyPair.
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(
				encodedPublicKey);
		PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
 
		PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
				encodedPrivateKey);
		PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
 
		return new KeyPair(publicKey, privateKey);
		
	}
	
	
	/*--------------------------------------------------------------------*/

}
