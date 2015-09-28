package Keys;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

import org.bouncycastle.crypto.encodings.PKCS1Encoding;





public class Keys {
	private final String path = "/users/thayanneLuiza/Documents/CertifQ/";
	private final String RSA = "RSA/ECB/PKCS1Padding";
	private final String BC = "BC";
	
	private PrivateKey PK;
	private PublicKey PU;
	
	private SecureRandom random = new SecureRandom();

	
	/**
	 * Builder default. Using algorithm RSA and PKCS1 padding for generate keys.
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 */
	public Keys() throws NoSuchAlgorithmException, NoSuchProviderException{
	    Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", BC);

	    generator.initialize(1028, random);

	    KeyPair pair = generator.generateKeyPair();
	    setPU(pair.getPublic());
	    setPK(pair.getPrivate());
	}
	
	/**
	 * Method that store the keys.
	 * @throws IOException
	 */
	public void storeKeys() throws IOException{
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec( getPU().getEncoded() );
		FileOutputStream fos = new FileOutputStream(path + "/public.key");
		fos.write(x509EncodedKeySpec.getEncoded());
		fos.close();
		
		PKCS8EncodedKeySpec pkcs1EncodedKeySpec = new PKCS8EncodedKeySpec(getPK().getEncoded());
		fos = new FileOutputStream(path + "/private.key");
		fos.write(pkcs1EncodedKeySpec.getEncoded());
		fos.close();
	}
	
	/**
	 * Incomplete
	 * @return
	 */
	
	public Keys loadKeys(){
		File filePublicKey = new File(path + "./public.Key");
		FileInputStream fis = new FileInputStream(path + "./public.Key");
		byte[] encodedPublicKey = new byte[(int) filePublicKey.length()];
		
	}
	
	/*--------------------------------------------------------------------*/
	public SecureRandom getRandom() {
		return random;
	}


	public void setRandom(SecureRandom random) {
		this.random = random;
	}
	/*--------------------------------------------------------------------*/

	public PrivateKey getPK() {
		return PK;
	}


	public void setPK(PrivateKey pK) {
		PK = pK;
	}

	/*--------------------------------------------------------------------*/

	public PublicKey getPU() {
		return PU;
	}


	public void setPU(PublicKey pU) {
		PU = pU;
	}
}
