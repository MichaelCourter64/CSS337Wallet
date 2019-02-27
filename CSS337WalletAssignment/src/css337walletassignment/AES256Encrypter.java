package css337walletassignment;
        
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.*;

public class AES256Encrypter {
    private static String secretKey = "boooooooooom!!!!";
    private static String salt = "ssshhhhhhhhhhh!!!!";
    
    public static byte[] encrypt(String stringToEncrypt, String key) {
        byte[] input = StringConversions.hexStringToByteArray(stringToEncrypt);
        byte[] keyArray = StringConversions.hexStringToByteArray(key);
        byte[] output = null;
        SecretKeySpec keySpec = null;
        keySpec = new SecretKeySpec(keyArray, "AES");
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES_256/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            output = cipher.doFinal(input);
        } 
        catch (NoSuchAlgorithmException | 
            InvalidKeyException | 
            NoSuchPaddingException | 
            BadPaddingException |
            IllegalBlockSizeException nsaeIkeNspeBpeIbse) 
        {
            nsaeIkeNspeBpeIbse.printStackTrace();
        }

        return output;
    }
	
    public static byte[] decrypt(String hexToDecrypt, String hexKey) {
        try
        {
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            byte[] input = StringConversions.hexStringToByteArray(hexToDecrypt);
            byte[] key = StringConversions.hexStringToByteArray(hexKey);

            SecretKeySpec secretKeyGen = new SecretKeySpec(key, "AES");
            
            Cipher cipher = Cipher.getInstance("AES_256/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeyGen);
            return cipher.doFinal(input);
        }
        catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}
