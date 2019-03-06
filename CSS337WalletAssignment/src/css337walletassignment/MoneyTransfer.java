package css337walletassignment;

import java.awt.Component;

import javax.swing.JOptionPane;
import Cryptography.AES256Encrypter;
import Utilities.Conversions;

public class MoneyTransfer {
    private static String Bank_ID = "F25D58A0E3E4436EC646B58B1C194C6B505AB1CB6B9DE66C894599222F07B893";

    public MoneyTransfer() {
    }

    public static String sync(String ID){
        if(WalletInteractionMap.getCounter(ID) == -1){
            String cipherText = send(ID, 0, 0);
            
            if (cipherText.compareTo("") != 0) {
                WalletInteractionMap.updateEntry(ID);
            }
            
            return cipherText;
        }
        else{
            JOptionPane.showMessageDialog(null, "You're already synced with this wallet.","Sync Error", JOptionPane.INFORMATION_MESSAGE);
            return "";
        }
    }

    public static String transferFunds(String Other_WALLET_ID, int value) {
        String transactionCipherText = send(Other_WALLET_ID, value, WalletInteractionMap.getCounter(Other_WALLET_ID));
        
        if (transactionCipherText.compareTo("") != 0) {
            WalletInteractionMap.updateEntry(Other_WALLET_ID);
        }
        
        return transactionCipherText;
    }
    
    private static String send(String Other_WALLET_ID, int value, int counter){
        if (counter == -1) {
            JOptionPane.showMessageDialog(null, "You haven't synced your wallet with their's yet.", "Receiver's wallet ID error", JOptionPane.WARNING_MESSAGE);
            return "";
        }
        
        if (value > Balance.getBalance()) {
            JOptionPane.showMessageDialog(null, "You don't have enough money to transfer that amount.", "Amount to send error", JOptionPane.WARNING_MESSAGE);
            return "";
        }
        
        if (value < 0) {
            JOptionPane.showMessageDialog(null, "That isn't a valid amount to send.", "Amount to send error", JOptionPane.WARNING_MESSAGE);
            return "";
        }
        
        String personalIdHex = Integer.toHexString(Integer.parseInt(User.PERSONAL_WALLET_ID));
        personalIdHex = frontPadHexStringTo4Bytes(personalIdHex);
        String otherIdHex = Integer.toHexString(Integer.parseInt(Other_WALLET_ID));
        otherIdHex = frontPadHexStringTo4Bytes(otherIdHex);
        String valueHex = Integer.toHexString(value);
        valueHex = frontPadHexStringTo4Bytes(valueHex);
        String counterHex = String.valueOf(counter);
        counterHex = frontPadHexStringTo4Bytes(counterHex);
        
        String token = personalIdHex + otherIdHex + valueHex + counterHex;
        
        //Encrypt
        byte[] cipher = AES256Encrypter.encrypt(token, Bank_ID);
        String cipherHex = Conversions.bytesToHex(cipher);
        Balance.remove(value);
        return cipherHex;
    }

    public static String recieve(String cipher){
        byte[] plain;
        
        //Decrypt
        try {
            plain = AES256Encrypter.decrypt(cipher, Bank_ID);
        
            //split
            String message = Conversions.bytesToHex(plain);
            String sendersWalletId = Conversions.hexStringToIntString(message.substring(0, 8));
            int sentCounter = Integer.parseInt(Conversions.hexStringToIntString(message.substring(24)));

            // If the receiver's ID doesn't match this application's ID, then:
            if (Conversions.hexStringToIntString(message.substring(8, 16)).compareTo(User.PERSONAL_WALLET_ID) != 0) {
                JOptionPane.showMessageDialog(null, "This transfer isn't meant for you.", "Receiver's wallet ID error", JOptionPane.ERROR_MESSAGE);
                return "";
            }

            // If the count value is 0, then:
            if (sentCounter == 0) {
                String output = "";

                if (WalletInteractionMap.getCounter(sendersWalletId) == -1) {
                    WalletInteractionMap.updateEntry(sendersWalletId);

                    output = send(sendersWalletId, 0, 0);   
                }            

                WalletInteractionMap.updateEntry(sendersWalletId);
                return output;
            }

            if (WalletInteractionMap.getCounter(Conversions.
                    hexStringToIntString(message.substring(0, 8))) != 
                    Integer.parseInt(Conversions.hexStringToIntString(message.substring(24)))) {
                JOptionPane.showMessageDialog(null, "You haven't synced your wallet with the sender's yet.", "Sync error", JOptionPane.ERROR_MESSAGE);
                return "";
            }

            int amount = Integer.parseInt(message.substring(16, 24), 16);
            Balance.add(amount);
            WalletInteractionMap.updateEntry(sendersWalletId);
            JOptionPane.showMessageDialog(null,"Recieved $" + amount + " from WalletID = " + Conversions.hexStringToIntString(message.substring(0, 8)),"Transaction Value", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, "The Transaction Token isn't valid.", "Invalid TransactionToken", JOptionPane.ERROR_MESSAGE);
            return "";
        }
        
        return "";
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
