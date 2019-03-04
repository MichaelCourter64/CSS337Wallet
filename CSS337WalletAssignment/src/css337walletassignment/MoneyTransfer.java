package css337walletassignment;

import java.awt.Component;

import javax.swing.JOptionPane;
import Cryptography.AES256Encrypter;
import Utilities.Conversions;

public class MoneyTransfer {
    private static String Bank_ID = "F25D58A0E3E4436EC646B58B1C194C6B505AB1CB6B9DE66C894599222F07B893";

    public MoneyTransfer() {
    }

    public static void sync(String ID){
        if(WalletInteractionMap.getCounter(ID) == -1){
            WalletInteractionMap.updateEntry(ID);
        }else{
            JOptionPane.showMessageDialog(null, "You're already synced with this wallet.","Sync Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void send(String Other_WALLET_ID, int value){
        //String token ="00000" + User.PERSONAL_WALLET_ID + "00000" + Other_WALLET_ID + Normalize(value) + Normalize(WalletInteractionMap.getCounter(Other_WALLET_ID));
        
        if (WalletInteractionMap.getCounter(Other_WALLET_ID) == -1) {
            JOptionPane.showMessageDialog(null, "You haven't synced your wallet with their's yet.", "Receiver's wallet ID error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (value > Balance.getBalance()) {
            JOptionPane.showMessageDialog(null, "You don't have enough money to transfer that amount.", "Amount to send error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (value <= 0) {
            JOptionPane.showMessageDialog(null, "That isn't a valid amount to send.", "Amount to send error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String personalIdHex = Integer.toHexString(Integer.parseInt(User.PERSONAL_WALLET_ID));
        personalIdHex = frontPadHexStringTo4Bytes(personalIdHex);
        String otherIdHex = Integer.toHexString(Integer.parseInt(Other_WALLET_ID));
        otherIdHex = frontPadHexStringTo4Bytes(otherIdHex);
        String valueHex = Integer.toHexString(value);
        valueHex = frontPadHexStringTo4Bytes(valueHex);
        String counterHex = Integer.toHexString(WalletInteractionMap.getCounter(Other_WALLET_ID));
        counterHex = frontPadHexStringTo4Bytes(counterHex);
        
        String token = personalIdHex + otherIdHex + valueHex + counterHex;
        
        //Encrypt
        byte[] cipher = AES256Encrypter.encrypt(token, Bank_ID);
        //show as dialaog box
        JOptionPane.showMessageDialog(null, Conversions.bytesToHex(cipher),"Transaction Value", JOptionPane.INFORMATION_MESSAGE);
        
        WalletInteractionMap.updateEntry(Other_WALLET_ID);
    }

    public static void recieve(String cipher){
        //Decrypt
        byte[] plain = AES256Encrypter.decrypt(cipher, Bank_ID);
        //split
        String message = Conversions.bytesToHex(plain);
        String sendersWalletId = Conversions.hexStringToIntString(message.substring(7, 16));
        
        // If the receiver's ID doesn't match this application's ID, then:
        if (sendersWalletId.compareTo(User.PERSONAL_WALLET_ID) != 0) {
            JOptionPane.showMessageDialog(null, "This transfer isn't meant for you.", "Receiver's wallet ID error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (WalletInteractionMap.getCounter(Conversions.
                hexStringToIntString(message.substring(0, 8))) == -1) {
            JOptionPane.showMessageDialog(null, "You haven't synced your wallet with the sender's yet.", "Sync error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int amount = Integer.parseInt(message.substring(15, 24), 16);
        Balance.add(amount);
        WalletInteractionMap.updateEntry(sendersWalletId);
        JOptionPane.showMessageDialog(null,"Recieved " + amount + " from WalletID = " + message.substring(0, 8),"Transaction Value", JOptionPane.INFORMATION_MESSAGE);
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

    private static String frontPadHexStringTo4Bytes(String hexToPad) {
        String paddedHex = "";
        
        for (int i = hexToPad.length(); i < 8; i++) {
            paddedHex += "0";
        }
        
        paddedHex += hexToPad;
        return paddedHex;
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
