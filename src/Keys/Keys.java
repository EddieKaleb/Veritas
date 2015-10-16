package Keys;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

import org.bouncycastle.crypto.encodings.PKCS1Encoding;

import sun.misc.IOUtils;





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
	
	public Keys(PrivateKey pk, PublicKey pu){
		setPK(pk);
		setPU(pu);
		
	}
	
	/**
	 * Method that store the keys.
	 * @throws IOException
	 */
	public void storeKeys() throws IOException{
		//PU
		X509EncodedKeySpec x509ks = new X509EncodedKeySpec(
	            PU.getEncoded());
	    FileOutputStream fos = new FileOutputStream(path + "/PU/");
	    fos.write(x509ks.getEncoded());
	    //PK
	    PKCS8EncodedKeySpec pkcsKeySpec = new PKCS8EncodedKeySpec(
	            PK.getEncoded());
	    FileOutputStream fosK = new FileOutputStream(path + "/PK/");
	    fos.write(pkcsKeySpec.getEncoded());
	}
	
	/**
	 * Method that load keys
	 * @return Keys
	 */
	
	public Keys loadKeys(){
		//PU
		byte[] encodedKey = IOUtils.toByteArray(new FileInputStream(path + "/PU/"));
	    KeyFactory keyFactory = KeyFactory.getInstance(RSA, BC);
	    X509EncodedKeySpec pkSpec = new X509EncodedKeySpec(
	            encodedKey);
	    PublicKey publicKey = keyFactory.generatePublic(pkSpec);
		//PK
	    byte[] encodedKeyPK = IOUtils.toByteArray(new FileInputStream(path + "/PK/"));
	    KeyFactory keyFactoryP = KeyFactory.getInstance(RSA, BC);
	    PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(
	            encodedKeyPK);
	    PrivateKey privateKey = keyFactoryP.generatePrivate(privKeySpec);
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
