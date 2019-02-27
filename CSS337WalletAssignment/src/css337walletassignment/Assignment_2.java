package css337walletassignment;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;


public class Assignment_2 {
	
	public Assignment_2() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		Balance Balance = new Balance();
		Balance.add(9);
		//int Balance = 0;
		String WID = "637";
		String KBank;
		
		String Student_ID = "56789859";
                String amount = "00000000000000000000000000000123";
		//String KWallet = SHA256Hasher.sha256(Student_ID);
		//System.out.println("KWallet = " + KWallet);
		
                //byte[] cipherText = AES256Encrypter.encrypt(amount, KWallet);
                //System.out.println("EMD = " + StringConversions.bytesToHex(cipherText));
                
		String inputStr = "00000000000000000000000000000123";
		String keyStr = "CF959C7BFC4FB5792AA25457578EF9E8B78E3558A8B7BF6A92338397B5F4639D";
		
		byte[] output = AES256Encrypter.encrypt(inputStr, keyStr);
		System.out.println(StringConversions.bytesToHex(output));
		
		String EMD ="";
		Scanner s = new Scanner(System.in);
		System.out.println("Enter your EMD");
		EMD = s.next();
		
		byte[] decryptedOutput = AES256Encrypter.decrypt(EMD, keyStr);
		String EEMD = StringConversions.bytesToHex(decryptedOutput);
		long emdOut = Long.parseLong(normalize(EEMD),16);
		
		//System.out.println(emdOut);
		System.out.println(Balance);

	}

	private static String normalize(String emd) {
		String result="";
		for(int i=0; i<emd.length();i++)
			if(emd.charAt(i) != '0')
//				System.out.println((emd.charAt(i));
				result+=emd.charAt(i);
		return result;
	}

}
