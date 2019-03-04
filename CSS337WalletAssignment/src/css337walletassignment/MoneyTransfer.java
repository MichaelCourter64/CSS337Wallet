import java.awt.Component;

import javax.swing.JOptionPane;

public class MoneyTransfer {
	private static String Bank_ID = "F25D58A0E3E4436EC646B58B1C194C6B505AB1CB6B9DE66C894599222F07B893";

	public MoneyTransfer() {
	}
		
	public static void sync(String ID, int ctr, long value){
		if(WalletIntractionMap.getCounter(ID) == -1){
			WalletIntractionMap.updateEntry(ID);
		}else{
			JOptionPane.showMessageDialog(null, "This user already exist in the table","Sync Error", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public static void send(String Other_WALLET_ID, long value){
		String token ="00000"+User.PERSONAL_WALLET_ID+"00000"+Other_WALLET_ID+Normalize(value)+Normalize(WalletIntractionMap.getCounter(Other_WALLET_ID));
		//Encrypt
		static byte[] cipher = AES256Encrypter.encrypt(token, Bank_ID);
		//show as dialaog box
		JOptionPane.showMessageDialog(null, StringConversions.bytesToHex(cipher),"Transaction Value", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void recieve(String cipher){
		//Decrypt
		static byte[] plain = AES256Encrypter.decrypt(cipher, Bank_ID);
		//split
		String message = StringConversions.bytesToHex(plain);
		Balance.add(Long.parseLong(message.substring(17, 24)));
		JOptionPane.showMessageDialog(null,"Recieved "+message.substring(17, 24)+" from WalletID = "+message.substring(0, 8),"Transaction Value", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private static String Normalize(long value) {
		// TODO Auto-generated method stub
		String output = String.valueOf(value);
		if(output.length()<8){
			for(int i = 8-output.length(); i>=0;i--){
				output="0"+output;
			}
		}
		return output;
	}
	
	//helper for split class
	 private static int hex2decimal(String s) {
         String digits = "0123456789ABCDEF";
         s = s.toUpperCase();
         int val = 0;
         for (int i = 0; i < s.length(); i++) {
             char c = s.charAt(i);
             int d = digits.indexOf(c);
             val = 16*val + d;
         }
         return val;
     }
}
