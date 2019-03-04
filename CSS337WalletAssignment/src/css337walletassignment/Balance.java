package css337walletassignment;

import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.*;

public class Balance {
	
    private static int currentBalance = -1;
    private static final String FILE_NAME = "Balance.json";

    public static void initializeBalance() {
        try {
            File balanceFile = new File(FILE_NAME);
            if(balanceFile.exists()) {
                /*BufferedReader readFile = new BufferedReader(new FileReader(balanceFile));
                
                JSONParser parser = new JSONParser();

                balanceFile.createNewFile();
                retrievedBalance = readFile.readLine();
                readFile.close();
                
                currentBalance = Integer.parseInt(retrievedBalance);*/
                
                String jsonString = new String(Files.readAllBytes(Paths.get(FILE_NAME)));
                
                JSONObject jsonObject = new JSONObject(jsonString);
                
                currentBalance = jsonObject.getInt("currentBalance");
            }
            else {
                currentBalance = 0;
                updateFile();
            }
        }
        catch (IOException ioe) {
            
        }
    }
    
    public static void set(int input) {
        currentBalance = input;
    }

    public static int get() {
        return currentBalance;
    }

    public static int getBalance() {
        return (int) currentBalance;
    }

    public static void add(int input) {
        currentBalance+= input;
            updateFile();
    }

    public static void remove(int input) {
        currentBalance-= input;
            updateFile();
    }

    public static void add(Long input) {
        currentBalance+= input;
            updateFile();
    }

    public static void remove(Long input) {
        currentBalance-= input;
            updateFile();
    }

    public String toString() {
        return String.valueOf(get());
    }

    private static void updateFile() {
        /*try {
            File Data = new File(FILE_NAME);
            BufferedWriter writeFile = new BufferedWriter(new FileWriter(Data, true));

            Data.createNewFile();
            writeFile.write(getBalance());
            writeFile.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        
        JSONObject balanceJson = new JSONObject();
        balanceJson.put("currentBalance", currentBalance);
        
        try (PrintWriter out = new PrintWriter(FILE_NAME)) {
            out.print(balanceJson.toString());
        }
        catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getStackTrace());
        }
    }
	
/*
	public static void main(String args[]){
		Balance b = new Balance();
		System.out.println(b.getBalance());
		b.setBalance(25);
		System.out.println(b.getBalance());
		b.addBalance(11);
		System.out.println(b.getBalance());
		b.subtractBalance(9);
		System.out.println(b.getBalance());
		
	}
*/
}
