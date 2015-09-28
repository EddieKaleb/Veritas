

package Cryptography;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

import Convert.Convert;
import Keys.Keys;

/**
 * This class has methods to encrypt and decrypt.
 * @author thayanneLuiza
 *
 */
public class Crypto {
	private final String RSA = "RSA/ECB/PKCS1Padding";
	private final String BC = "BC";
	private Keys keys;
	
	

	private SecureRandom random ;
	private Cipher cipher ;

	public Crypto() throws Exception {
	    Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	    cipher = Cipher.getInstance(RSA, BC);
	    Keys keys = new Keys();
	    random = keys.getRandom();
	    setKeys(keys);
	    
	}
	

	public Crypto(Keys keys) throws Exception {
	    Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	    cipher = Cipher.getInstance(RSA, BC);
	    random = keys.getRandom();
	    setKeys(keys);
	    
	}
	
	
	/**
	 * Encrypts a text with the Private Key. Using RSA algorithm,PKCS1 as padding and Bouncy Castle(BC) provider. 
	 * 
	 * @param text  the source that will be cipher
	 * @return encrypt  encrypted in string
	 * @throws Exception
	 */
	
	public final String encrypt(String text) throws Exception{
		return  Convert.byte2hex(cipher(text));
	}
	
	/**
	 * Decrypts a cipher Text using the Public Key. Using RSA algorithm and Bouncy Castle(BC) provider. 
	 * @param cipherText
	 * @return decoded text in string
	 * @throws Exception
	 */
	public final String decrypt(String text) throws Exception{
		return new String(decoded(Convert.hex2byte(text.getBytes()), keys.getPU()));
	}
	
	
	
	
	/**
	 * Encrypts a text with the Private Key. Using RSA algorithm,PKCS1 as padding and Bouncy Castle(BC) provider. 
	 * 
	 * @param text  the source that will be cipher
	 * @return encrypt  encrypted in bytes
	 * @throws Exception
	 */
	
	private final byte[] cipher(String text) throws Exception{
		 

		 cipher.init(Cipher.ENCRYPT_MODE, keys.getPK(), random);
		 byte[] encrypt = cipher.doFinal(text.getBytes());
		 return encrypt;	 
	}
	
	/**
	 * Decrypts a cipher Text using the Public Key. Using RSA algorithm and Bouncy Castle(BC) provider. 
	 * @param cipherText
	 * @return decoded text in bytes
	 * @throws Exception
	 */
	private final byte[] decoded(byte[] encrypt, Key PU) throws Exception{
		cipher.init(Cipher.DECRYPT_MODE, PU);
		byte[] decrypted = cipher.doFinal(encrypt);
		return decrypted;
	}
	
	
	
	
    
    public Keys getKeys() {
		return keys;
	}
	public void setKeys(Keys keys) {
		this.keys = keys;
	}
	
}
